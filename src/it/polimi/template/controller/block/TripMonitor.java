package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.controller.thread.TripWorker;
import it.polimi.template.model.*;

public class TripMonitor extends Node implements Observer {

	MissionWorker missionThread;

	public TripMonitor(MissionWorker w) {
		this.missionThread = w;
	}

	@Override
	public Mission run(Mission m) {

		// Here mission is in Running status
		if (m.getStatus() == Mission.RUNNING) {
			// Result of the current running trip of the mission
			int tripResult = 0;

			// Getting the current trip instance
			Trip t = m.getTrips().get(0);

			// Getting the instance of the Thread that is running the Trip
			TripWorker tw = missionThread.getTripThread();

			// While the thread is executing we need to wait for it to end
			while (!tw.isDone()) {
				// if the status is Expired we need to break
				if(t.getStatus() == Trip.EXPIRED)
					// returning null will not notify the observers
					return null;
			}

			// getting the result of the thread
			try {
				tripResult = tw.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

			if (tripResult == TripWorker.COMPLETED) {
				t.setStatus(Trip.COMPLETED);
				m.setStatus(Mission.STANDBY);
				missionThread.log(m, "Trip " + t.getName() + " is COMPLETED");
				// add it to the list of the completed trips of the mission
				m.getCompletedTrips().add(t);
				// remove it from the list of trips
				m.getTrips().remove(0);
			} else if (tripResult == TripWorker.FAILED){
				t.setStatus(Trip.FAILED);
				m.setStatus(Mission.FAILED);
				missionThread.log(m, "Trip " + t.getName() + " is FAILED");
			}

			// let the drone to be free :)
			t.getDrone().setStatus(Drone.FREE);
			missionThread.log(m, "Drone " + t.getDrone().getId() + " is FREE");

			// when all trips are completed we set the mission as COMPLETED
			if (m.getTrips().isEmpty()) {
				m.setStatus(Mission.COMPLETED);
				missionThread
						.log(m, "Mission " + m.getName() + " is COMPLETED");
			}

			return m;
		}
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		if (m != null) {
			setChanged();
			notifyObservers(m);
		}
	}

}
