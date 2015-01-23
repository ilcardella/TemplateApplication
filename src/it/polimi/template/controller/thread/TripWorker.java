package it.polimi.template.controller.thread;

import it.polimi.template.model.Trip;

import javax.swing.SwingWorker;

public class TripWorker extends SwingWorker<Integer, String> {

	public static final Integer COMPLETED = 1;
	public static final Integer FAILED = 0;

	private Trip trip;

	public TripWorker(Trip t) {
		this.trip = t;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		
		if (trip.executeTrip()) {
			return TripWorker.COMPLETED;
		}
		return TripWorker.FAILED;
	}

}
