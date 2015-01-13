package it.polimi.template.model.editor;

import it.polimi.template.model.*;
import it.polimi.template.utils.Logger;

import java.util.Observable;
import java.util.Observer;

public class Clock extends Node implements Observer{
	
	@Override
	public Mission run(Mission m) {

		// get the next trip
		Trip t = m.getTrips().get(0);

		// if delay > 0 change the status
		if (t.getDelay() > 0) {
			t.setStatus(Trip.DELAYED);
			System.out.println("Trip " + t.getName() + " is delayed");
			try {
				Thread.sleep(t.getDelay()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t.setStatus(Trip.WAITING);
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
