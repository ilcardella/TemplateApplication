package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import java.util.Observable;
import java.util.Observer;

public class MissionRepeater extends Node implements Observer {

	MissionWorker mw;

	public MissionRepeater(MissionWorker mw) {
		this.mw = mw;
	}

//	@Override
//	public void update(Observable o, Object arg) {
//		Mission m = this.run((Mission) arg);
//		if (m != null) {
//			setChanged();
//			notifyObservers(m);
//		}
//	}

	@Override
	public Mission run(Mission m) {
		// if the mission must be repeated and is completed
		if (m.isRepeateable() && m.getStatus() == Mission.COMPLETED) {
			// the mission status is set to STANDBY
			m.setStatus(Mission.STANDBY);
			// all the trips are moved from completedTrips to trips
			// and their status is set to WAITING
			for (Trip z : m.getCompletedTrips()) {
				z.setStatus(Trip.WAITING);
				m.getTrips().add(z);
			}
			// the list of completedTrips is cleared
			m.getCompletedTrips().clear();
		}
		return m;
	}

}
