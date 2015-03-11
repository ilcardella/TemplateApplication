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

public class Engine {
	
	private Evaluator evaluator;
	private List<MissionWorker> missionsThreadList;
	private List<Mission> missions;
	private MonitorPageController controller;
	ExecutorService threadPool;
	
	public Engine(MonitorPageController controller){
		this.controller = controller;
		this.evaluator = new Evaluator();
	}
	
	public void startMissionsExecution(){
		this.missionsThreadList = new ArrayList<MissionWorker>();
		threadPool = Executors.newFixedThreadPool(missions.size());
		for (int i = 0; i < missions.size(); i++) {
			missions.get(i).setEvaluator(this.evaluator);
			MissionWorker worker = new MissionWorker(missions.get(i), controller);
			missionsThreadList.add(worker);
			threadPool.submit(worker);
			//worker.execute();

		}
		
		// TODO Here wait, check and print the result of all the thread launched
	}
	
	public void stopMissionsExecution(String selection) {
		for (MissionWorker t : getMissionsThreadList()) {
			while (!t.isCancelled())
				t.cancel(true);
		}
		
		for(Mission m: getMissions()){
			// TODO lanciare un nuovo Thread
			
			Trip next = m.getTrips().get(0);
			Drone drone = next.getDrone();
			if(drone != null){
				switch (selection) {
				case "RTL":
					drone.setStatus(Drone.BUSY);
					drone.flyTo(Drone.HOME_LOCATION);
					drone.setStatus(Drone.FREE);
					controller.log(m, "Drone "+drone.getId()+" received RTL Command");
					break;
				case "Land":
					drone.setStatus(Drone.BUSY);
					drone.land();
					drone.setStatus(Drone.FREE);
					controller.log(m, "Drone "+drone.getId()+" received Land Command");
					break;
				default:
					// do RTL as default
					drone.setStatus(Drone.BUSY);
					drone.flyTo(Drone.HOME_LOCATION);
					drone.setStatus(Drone.FREE);
					controller.log(m, "Drone "+drone.getId()+" received RTL Command");
					break;
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
