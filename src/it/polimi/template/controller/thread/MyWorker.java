package it.polimi.template.controller.thread;

import it.polimi.template.controller.MonitorPageController;
import it.polimi.template.controller.block.*;
import it.polimi.template.model.Mission;

import javax.swing.SwingWorker;

public class MyWorker extends SwingWorker<Integer, String> {

	private MonitorPageController controller;
	private Mission m;
	
	//<dec>
	MissionCreator missioncreator = new MissionCreator(this);
	DroneAllocator droneallocator = new DroneAllocator(this);
	TripLauncher triplauncher = new TripLauncher(this);
	TripMonitor tripmonitor = new TripMonitor(this);

	public MyWorker(Mission mission, MonitorPageController controller) {
		this.m = mission;
		this.controller = controller;
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

	public void log(Mission m, String s) {
		controller.log(m, s);
	}
	
}
