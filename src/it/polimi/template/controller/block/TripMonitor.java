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
		if (m != null && m.getStatus() == Mission.RUNNING) {
			// Result of the current running trip of the mission
			int tripResult = 0;

			// Getting the current trip instance
			Trip t = m.getTrips().get(0);

			// If a TimerMonitor has been added to the diagram
			// we check if the current Trip is expired
			if (t.getStatus() == Trip.EXPIRED) {
				// If the Trip is Expired we set the result as FAILED
				tripResult = TripWorker.FAILED;
			} else {
				// If it was not expired we has to wait for the result

				// Getting the instance of the Thread that is running the Trip
				TripWorker tw = missionThread.getTripThread();

				while (!tw.isDone()) {
					// While the thread is running we need to wait for it to end
				}

				// get the result of the thread
				try {
					tripResult = tw.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}

			if (tripResult == TripWorker.COMPLETED) {
				t.setStatus(Trip.COMPLETED);
				m.setStatus(Mission.STANDBY);
				missionThread.log(m, "Trip " + t.getName() + " is COMPLETED");
				// add it to the list of the completed trips of the mission
				m.getCompletedTrips().add(t);
				// remove it from the list of trips
				m.getTrips().remove(0);
			} else {
				t.setStatus(Trip.FAILED);
				m.setStatus(Mission.FAILED);
				missionThread.log(m, "Trip " + t.getName() + " is FAILED");
			}

			t.getDrone().setStatus(Drone.FREE);
			missionThread.log(m, "Drone " + t.getDrone().getId() + " is FREE");

			if (m.getTrips().isEmpty()) {
				m.setStatus(Mission.COMPLETED);
				missionThread
						.log(m, "Mission " + m.getName() + " is COMPLETED");
			}

			// TODO Qui, se la missione è ripetibile "repeat == true" 
			// AND la lista dei Trips è vuota, cioè li abbiamo fatti tutti
			// Spostare tutti i Trip della lista CompletedTrips, nella lista dei Trips da fare
			// Mentre lo si fa gli si cambia lo stato in WAITING
			
			
			return m;
		}
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		setChanged();
		notifyObservers(m);
	}

}
