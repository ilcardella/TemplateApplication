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
	private List<MissionWorker> missionsThreadList;
	private List<Mission> missions;
	private MonitorPageController controller;
	ExecutorService threadPool;

	public Engine(MonitorPageController controller) {
		this.controller = controller;
		this.evaluator = new Evaluator();
	}

	public void startMissionsExecution() {
		this.missionsThreadList = new ArrayList<MissionWorker>();
		threadPool = Executors.newFixedThreadPool(missions.size());
		for (int i = 0; i < missions.size(); i++) {
			missions.get(i).setEvaluator(this.evaluator);
			MissionWorker worker = new MissionWorker(missions.get(i),
					controller);
			missionsThreadList.add(worker);
			threadPool.submit(worker);
			// worker.execute();
		}

		// TODO Here wait, check and print the result of all the thread launched
	}

	public void stopMissionsExecution(String selection) {

		threadPool.shutdownNow();
		// for (MissionWorker t : getMissionsThreadList()) {
		// while (!t.isCancelled())
		// t.cancel(true);
		// }

		for (Mission m : getMissions()) {

			// log the status of all not-completed missions after the stop
			if (!(m.getStatus() == Mission.COMPLETED)) {
				m.setStatus(Mission.STOPPED);
				controller.log(m, "Mission " + m.getName() + " STOPPED");

				// TODO lanciare un nuovo Thread

				Trip next = m.getTrips().get(0);
				next.setStatus(Trip.WAITING);
				Drone drone = next.getDrone();
				next.setDrone(null);
				
				if (drone != null) {
					switch (selection) {
					case "RTL":
						drone.setStatus(Drone.BUSY);
						drone.flyTo(Drone.HOME_LOCATION);
						drone.setStatus(Drone.FREE);
						controller.log(m, "Drone " + drone.getId()
								+ " received RTL Command");
						break;
					case "Land":
						drone.setStatus(Drone.BUSY);
						drone.land();
						drone.setStatus(Drone.FREE);
						controller.log(m, "Drone " + drone.getId()
								+ " received Land Command");
						break;
					default:
						// do RTL as default
						drone.setStatus(Drone.BUSY);
						drone.flyTo(Drone.HOME_LOCATION);
						drone.setStatus(Drone.FREE);
						controller.log(m, "Drone " + drone.getId()
								+ " received RTL Command");
						break;
					}
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

	public List<MissionWorker> getMissionsThreadList() {
		return missionsThreadList;
	}

	public void setMissionsThreadList(List<MissionWorker> missionsThreadList) {
		this.missionsThreadList = missionsThreadList;
	}

}
