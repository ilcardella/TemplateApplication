package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;
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

		// accept mission only if they are running
		if (m.getStatus() == Mission.RUNNING) {
			// Getting the current trip instance
			Trip t = m.getTrips().get(0);

			// Getting the instance of the Thread that is running the Trip
			TripWorker tw = missionThread.getTripThread();

			boolean isExpired = false;

			// Wait until the SafeTime is passed
			while (System.currentTimeMillis()
					- t.getStartTime() < m.getSafeTimer()) {
				// if the Trip finishes, exit from the loop before the SafeTime is passed
				if(tw.isDone() || m.getStatus() == Mission.COMPLETED || m.getStatus() == Mission.FAILED)
					break;
			}

			isExpired = !tw.isDone();

			if (isExpired) {
				t.setStatus(Trip.EXPIRED);
				m.setStatus(Mission.FAILED);
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
