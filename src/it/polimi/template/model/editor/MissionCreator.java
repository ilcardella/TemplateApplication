package it.polimi.template.model.editor;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class MissionCreator extends Node implements Observer {
	
	public Mission run(Mission m) {
		if (m.getTrips() != null && m.getStatus() == Mission.UNEXECUTED) {
			System.out.println("Mission " + m.getName()
					+ " created");
			return m;
			
		} else
			
			return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		notifyObservers(m);
	}
	

}
