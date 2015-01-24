package it.polimi.template.controller.block;

import java.text.SimpleDateFormat;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.*;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class TripLauncher extends Node implements Observer {

	private Calendar cal = Calendar.getInstance();
	MissionWorker missionThread;

	public TripLauncher(MissionWorker w) {
		this.missionThread = w;
	}

	@Override
	public Mission run(Mission m) {

		Trip t = m.getTrips().get(0);

		// the trips which have an associated drone can start

		if (t.getDrone() != null) {

			t.setStartTime(new SimpleDateFormat("HH:mm:ss").format(cal
					.getTime()));
			
			missionThread.executeTrip(t);
			
			// the trip status is set to EXECUTING
			t.setStatus(Trip.EXECUTING);
			missionThread.log(m, "Trip "+t.getName()+" is EXECUTING");
			
			// the mission status is set to RUNNING
			m.setStatus(Mission.RUNNING);
			missionThread.log(m, "Mission "+m.getName()+" is RUNNING");
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
