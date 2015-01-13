package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class MissionCreator extends Node implements Observer {

	
	public Mission run(Mission m) {
		if (m.getTrips() != null && m.getStatus() == Mission.UNEXECUTED) {
			m.setStatus(Mission.UNEXECUTED);
			return m;
		} else
			return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		setChanged();
		notifyObservers(m);
	}
	
	void log(String log) {
		System.out.println(log);
	}
	
}
