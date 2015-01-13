package it.polimi.template.controller.thread;

import it.polimi.template.model.Mission;
import it.polimi.template.model.editor.*;
import javax.swing.SwingWorker;

public class MyWorker extends SwingWorker<Integer, String> {

	private Mission m;
	
	//<dec>
	MissionCreator missioncreator = new MissionCreator();
	DroneAllocator droneallocator = new DroneAllocator();
	TripLauncher triplauncher = new TripLauncher();
	TripMonitor tripmonitor = new TripMonitor();

	public MyWorker(Mission mission) {
		this.m = mission;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		//<exe>
		missioncreator.addObserver(droneallocator);
		droneallocator.addObserver(triplauncher);
		triplauncher.addObserver(tripmonitor);
		tripmonitor.addObserver(droneallocator);
		missioncreator.update(null, m);
		
		return 4;
	}
	
}
