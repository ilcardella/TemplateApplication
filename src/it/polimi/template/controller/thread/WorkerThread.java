package it.polimi.template.controller.thread;

import it.polimi.template.controller.MonitorPageController;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.model.editor.*;

public class WorkerThread implements Runnable {

	private MonitorPageController parent;
	private Mission m;
	private MissionCreator mc = new MissionCreator();
	private DroneAllocator da = new DroneAllocator();
	private TripLauncher tl = new TripLauncher();
	private TripMonitor tm = new TripMonitor();

	public WorkerThread(Mission mission, MonitorPageController parent) {
		this.m = mission;
		this.parent = parent;
	}

	@Override
	public void run() {

		System.out.println("Mission " + m.getName() + " started");

		m = mc.run(m);

		parent.notifyUpdateOfStatus(m);
		
		while (m.getStatus() != Mission.COMPLETED) {

			m = da.run(m);

			parent.notifyUpdateOfStatus(m);
			
			m = tl.run(m);

			parent.notifyUpdateOfStatus(m);
			
			m = tm.run(m);

			parent.notifyUpdateOfStatus(m);
			
			if (m.getStatus() == Mission.FAILED)
				break;
		}
		
		parent.notifyUpdateOfStatus(m);

		System.out.println("Mission " + m.getName() + " ended with result: "
				+ m.getStatus());

	}
}
