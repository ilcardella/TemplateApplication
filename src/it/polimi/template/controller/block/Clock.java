package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.*;

import java.util.Observable;
import java.util.Observer;

public class Clock extends Node implements Observer {

	MissionWorker w;

	public Clock(MissionWorker worker) {
		this.w = worker;
	}

	@Override
	public Mission run(Mission m) {

		// Here arrives only missions in standby or unexecuted
		if (m.getStatus() == Mission.STANDBY || m.getStatus() == Mission.UNEXECUTED) {

			// get the next trip
			Trip t = m.getTrips().get(0);

			// if delay > 0 change the status
			if (t.getDelay() > 0) {
				t.setStatus(Trip.DELAYED);
				w.log(m, "Trip "+t.getName()+" is DELAYED");
				try {
					Thread.sleep(t.getDelay());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				t.setStatus(Trip.WAITING);
				w.log(m, "Trip "+t.getName()+" is WAITING");
			}

			return m;
		}
		return null;
	}

//	@Override
//	public void update(Observable o, Object arg) {
//		Mission m = this.run((Mission) arg);
//		if (m != null) {
//			setChanged();
//			notifyObservers(m);
//		}
//	}

}
