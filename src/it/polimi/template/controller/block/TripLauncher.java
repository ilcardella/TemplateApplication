package it.polimi.template.controller.block;

import java.text.SimpleDateFormat;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.controller.thread.TripWorker;
import it.polimi.template.model.*;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class TripLauncher extends Node implements Observer {

	private Calendar cal = Calendar.getInstance();
	MissionWorker w;

	public TripLauncher(MissionWorker w) {
		this.w = w;
	}

	@Override
	public Mission run(Mission m) {

		Trip t = m.getTrips().get(0);

		// the mission status is set to RUNNING
		m.setStatus(Mission.RUNNING);
		w.log(m, "Mission "+m.getName()+" is RUNNING");

		// the trips which have an associated drone can start

		if (t.getDrone() != null) {

			t.setStartTime(new SimpleDateFormat("HH:mm:ss").format(cal
					.getTime()));
			t.setStatus(Trip.EXECUTING);
			w.log(m, "Trip "+t.getName()+" is EXECUTING");

//			boolean tripResult = t.executeTrip();
			
			// Create a new SwingWorker that will manage the execution of the Trip
			TripWorker tripThread = new TripWorker(t);
			// Setting the instance of the thread to the Mission SwingWorker 
			w.setTripThread(tripThread);
			// Launching the thread
			tripThread.execute();
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
