package it.polimi.template.controller.thread;

import it.polimi.template.controller.MonitorPageController;
import it.polimi.template.controller.block.*;
import it.polimi.template.controller.engine.Evaluator;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import javax.swing.SwingWorker;

public class MissionWorker extends SwingWorker<Integer, String> {

	private MonitorPageController controller;
	private Mission m;
	private TripWorker tripThread;
	
	// TODO Tag
	<dec>
//	StartBlock startblock = new StartBlock(this);	
//	MissionCreator missioncreator = new MissionCreator(this);
//	DroneAllocator droneallocator = new DroneAllocator(this);
//	TripLauncher triplauncher = new TripLauncher(this);
//	TripMonitor tripmonitor = new TripMonitor(this);
//	EndBlock endblock = new EndBlock(this);

	 public MissionWorker(Mission mission, MonitorPageController controller) {
		this.m = mission;
		this.controller = controller;
	}

	@Override
	protected Integer doInBackground() throws Exception {

		// TODO Tag
		<exe>
//		startblock.addObserver(missioncreator);
//		missioncreator.addObserver(droneallocator);
//		droneallocator.addObserver(triplauncher);
//		triplauncher.addObserver(tripmonitor);
//		tripmonitor.addObserver(droneallocator);
//		tripmonitor.addObserver(endblock);
//		missioncreator.update(null, m);
		return 4;
	}

	// synchronized because in this way the ui is updated without problems
	public synchronized void log(Mission m, String s) {
		controller.log(m, s);
	}

	public TripWorker getTripThread() {
		return tripThread;
	}

	public void executeTrip(Trip t) {
		tripThread = new TripWorker(t);
		tripThread.execute();
	}
	
}
