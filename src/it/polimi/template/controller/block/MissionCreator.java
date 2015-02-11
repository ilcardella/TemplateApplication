package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.*;

public class MissionCreator extends Node implements Observer {

	MissionWorker w;

	public MissionCreator(MissionWorker w) {
		this.w = w;
	}

	public Mission run(Mission m) {
		if (m.getTrips() != null && m.getStatus() == Mission.UNEXECUTED) {
			m.setStatus(Mission.UNEXECUTED);
			w.log(m, "Mission " + m.getName() + " is UNEXECUTED");
			return m;
		} else
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
