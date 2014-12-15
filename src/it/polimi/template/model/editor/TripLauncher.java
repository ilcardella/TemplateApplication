package it.polimi.template.model.editor;
import java.text.SimpleDateFormat;

import it.polimi.template.model.*;

import java.util.Calendar;

public class TripLauncher implements Node {

	private Calendar cal = Calendar.getInstance();

	@Override
	public Mission run(Mission m) {

		// the mission status is set to RUNNING
		m.setStatus(2);
		// the start time is set for the trip
		m.getTrips()
				.get(0)
				.setStartTime(
						new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
		

		return m;
	}

}
