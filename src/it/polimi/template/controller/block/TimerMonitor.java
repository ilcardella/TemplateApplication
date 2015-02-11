package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.controller.thread.TripWorker;
import it.polimi.template.model.*;

public class TimerMonitor extends Node implements Observer {

	MissionWorker missionThread;

	public TimerMonitor(MissionWorker m) {
		this.missionThread = m;
	}

	@Override
	public Mission run(Mission m) {

		// Getting the current trip instance
		Trip t = m.getTrips().get(0);

		// Getting the instance of the Thread that is running the Trip
		TripWorker tw = missionThread.getTripThread();

		boolean isExpired = false;

		while (!tw.isDone()) {

			if (System.currentTimeMillis() - Integer.parseInt(t.getStartTime()) > m
					.getSafeTimer()) {
				isExpired = true;
			}

			if (isExpired) {
				t.setStatus(Trip.EXPIRED);
				m.setStatus(Mission.FAILED);
				break;
			}
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
