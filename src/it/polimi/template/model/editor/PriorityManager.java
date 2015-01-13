package it.polimi.template.model.editor;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class PriorityManager extends Node implements Observer {

	@Override
	public Mission run(Mission m) {
		// increment the priority to HIGH level
		m.getTrips().get(0).setPriority(150);
		m.setStatus(Mission.STANDBY);
		log(m, "Mission " + m.getName() + " is standby");
		return m;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		setChanged();
		notifyObservers(m);
	}

}
