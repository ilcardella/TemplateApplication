package it.polimi.template.controller.block;

import java.text.SimpleDateFormat;

import it.polimi.template.model.*;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class TripLauncher extends Node implements Observer {

	private Calendar cal = Calendar.getInstance();

	@Override
	public Mission run(Mission m) {

		Trip t = m.getTrips().get(0);

		// the mission status is set to RUNNING
		m.setStatus(Mission.RUNNING);

		// the trips which have an associated drone can start

		if (t.getDrone() != null) {
			
			t.setStartTime(new SimpleDateFormat("HH:mm:ss").format(cal
					.getTime()));
			t.setStatus(Trip.EXECUTING);
			
			
			if(t.getDrone().flyToAndDoAction(t.getTargetLocation(), t.getAction())){
				t.setStatus(Trip.COMPLETED);
			}else{
				t.setStatus(Trip.FAILED);
			}

			t.getDrone().setStatus(Drone.FREE);

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
