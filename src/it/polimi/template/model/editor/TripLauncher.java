package it.polimi.template.model.editor;

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
		log(m, "Mission " + m.getName() + " is running");

		// the trips which have an associated drone can start

		if (t.getDrone() != null) {
			
			t.setStartTime(new SimpleDateFormat("HH:mm:ss").format(cal
					.getTime()));
			t.setStatus(Trip.EXECUTING);
			log(m, "Trip " + t.getName() + " is executing");
			
			
			if(t.getDrone().flyToAndDoAction(t.getTargetLocation(), t.getAction())){
				t.setStatus(Trip.COMPLETED);
				log(m, "Trip " + t.getName() + " is completed");
			}else{
				t.setStatus(Trip.FAILED);
				log(m, "Trip " + t.getName() + " is failed");
			}

			t.getDrone().setStatus(Drone.FREE);
			log(m, "Drone " + t.getDrone().getId() + " is free");

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
