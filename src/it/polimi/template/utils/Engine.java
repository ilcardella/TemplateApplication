package it.polimi.template.utils;

import it.polimi.template.controller.MonitorPageController;
import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {
	
	private Evaluator evaluator;
	private List<MissionWorker> missionsThreadList;
	private List<Mission> missions;
	private MonitorPageController controller;
	
	public Engine(MonitorPageController controller){
		this.controller = controller;
		this.evaluator = new Evaluator();
	}
	
	public void startMissionsExecution(){
		this.missionsThreadList = new ArrayList<MissionWorker>();
		for (int i = 0; i < missions.size(); i++) {
			missions.get(i).setEvaluator(this.evaluator);
			MissionWorker worker = new MissionWorker(missions.get(i), controller);
			missionsThreadList.add(worker);

			worker.execute();

		}
	}
	
	public void stopMissionsExecution(){
		//TODO
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
