package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.controller.thread.TripWorker;
import it.polimi.template.model.*;

public class TripMonitor extends Node implements Observer {

	MissionWorker mw;
	TripWorker tw;

	public TripMonitor(MissionWorker w) {
		this.mw = w;
	}

	@Override
	public Mission run(Mission m) {

		int tripResult = 0;
		
		// Getting the current trip instance
		Trip t = m.getTrips().get(0);
		
		// Getting the instance of the Thread that is running the current Trip
		this.tw = this.mw.getTripThread();
		
		while (!tw.isDone()) {
			// While the thread is running we need to wait for it to end
		}
		
		// get the result of the thread
		try {
			tripResult = tw.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		if (tripResult == TripWorker.COMPLETED) {
			t.setStatus(Trip.COMPLETED);
			mw.log(m, "Trip " + t.getName() + " is COMPLETED");
		} else {
			t.setStatus(Trip.FAILED);
			mw.log(m, "Trip " + t.getName() + " is FAILED");
		}
		
		t.getDrone().setStatus(Drone.FREE);
		mw.log(m, "Drone " + t.getDrone().getId() + " is FREE");

		// in questo ciclo while non ci entriamo mai perchè il tripLauncher
		// prima di notificare aspetta che il drone abbia finito il trip
		// bisogna reimplementare il tutto senza ciclo ma con solo if
//		while (t.getStatus() != Trip.COMPLETED) {
//
//			// if the current trip is failed for an exception, the mission
//			// status is
//			// set to failed
//			if (t.getStatus() == Trip.FAILED) {
//				m.setStatus(Mission.FAILED);
//				mw.log(m, "Mission " + m.getName() + " is FAILED");
//				return m;
//			}
//
//			// if there are no more trips to perform, the mission is completed
//			if (m.getTrips().isEmpty()) {
//				m.setStatus(Mission.COMPLETED);
//				mw.log(m, "Mission " + m.getName() + " is COMPLETED");
//				return m;
//			}
//			// otherwise there is at least another trip in the mission and the
//			// mission status is set to standby
//			else {
//				m.setStatus(Mission.STANDBY);
//				mw.log(m, "Mission " + m.getName() + " is STANDBY");
//				return m;
//			}
//
//		}
		// if the current trip is completed, remove it from the list of trips
		// associated to the mission
		m.getTrips().remove(0);

		if (m.getTrips().isEmpty()) {
			m.setStatus(Mission.COMPLETED);
			mw.log(m, "Mission " + m.getName() + " is COMPLETED");
		}

		return m;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		setChanged();
		notifyObservers(m);
	}

}
