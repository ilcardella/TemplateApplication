package it.polimi.template.controller.engine;

import it.polimi.template.controller.MonitorPageController;
import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Drone;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Engine {

	private Evaluator evaluator;
//	private List<MissionWorker> missionsThreadList;
	private List<Mission> missions;
	private MonitorPageController controller;
	ExecutorService threadPool;
	private boolean isRunning;

	public Engine(MonitorPageController controller) {
		this.controller = controller;
		this.evaluator = new Evaluator();
	}

	public void startMissionsExecution() {
		isRunning = true;
//		this.missionsThreadList = new ArrayList<MissionWorker>();
		threadPool = Executors.newFixedThreadPool(missions.size());
		for (int i = 0; i < missions.size(); i++) {
			Mission m = missions.get(i);
			m.setEvaluator(this.evaluator);
			MissionWorker worker = new MissionWorker(m, controller);
//			missionsThreadList.add(worker);
			threadPool.submit(worker);
		}
		threadPool.shutdown();
		while( !threadPool.isTerminated() ){}
		isRunning = false;
	}

	public void stopMissionsExecution(String selection) {

		isRunning = false;
		
		threadPool.shutdownNow();

		for (Mission m : getMissions()) {

			// log the status of all not-completed missions after the stop
			if (!(m.getStatus() == Mission.COMPLETED)) {
				m.setStatus(Mission.STOPPED);
				controller.log(m, "Mission " + m.getName() + " STOPPED");

				Trip next = m.getTrips().get(0);
				Drone drone = next.getDrone();
				
				next.setStatus(Trip.WAITING);
				next.setDrone(null);
				
				if (drone != null) {
					
					drone.setStatus(Drone.BUSY);
					
					switch (selection) {
					case "RTL":
						
						drone.flyTo(Drone.HOME_LOCATION);
						controller.log(m, "Drone " + drone.getId()
								+ " received RTL Command");
						break;
					case "Land":
						
						drone.land();
						controller.log(m, "Drone " + drone.getId()
								+ " received Land Command");
						break;
					default:
						// do RTL as default
						drone.flyTo(Drone.HOME_LOCATION);
						controller.log(m, "Drone " + drone.getId()
								+ " received RTL Command");
						break;
					}
					
					drone.setStatus(Drone.FREE);
				}
			}
		}
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

//	public List<MissionWorker> getMissionsThreadList() {
//		return missionsThreadList;
//	}
//
//	public void setMissionsThreadList(List<MissionWorker> missionsThreadList) {
//		this.missionsThreadList = missionsThreadList;
//	}

	public synchronized boolean isRunning() {
		return isRunning;
	}

}
