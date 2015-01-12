package it.polimi.template.model.editor;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class ConditionBlock extends Node implements Observer {

	@Override
	public Mission run(Mission m) {

		// get the next trip
		Trip t = m.getTrips().get(0);
		
		// if delay > 0 change the status
		if (t.getDelay() > 0)
			t.setStatus(Trip.DELAYED);

		return m;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
