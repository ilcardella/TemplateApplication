package it.polimi.template.controller.thread;

import it.polimi.template.model.Mission;
import it.polimi.template.model.editor.*;

public class WorkerThread implements Runnable {

	private Mission m;
	private MissionCreator mc = new MissionCreator();
	private DroneAllocator da = new DroneAllocator();
	private TripLauncher tl = new TripLauncher();
	private TripMonitor tm = new TripMonitor();
	
	public WorkerThread(Mission mission) {
		this.m = mission;
	}
	
	@Override
	public void run() {
		
		m = mc.run(m);
		
		m = da.run(m); 

		m = tl.run(m); 
		
		m = tm.run(m);
	}

}
