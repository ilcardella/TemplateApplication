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

		// the trips which have an associated drone can start

		for (Trip t : m.getTrips()) {

			
			if (t.getDrone() != null) {
				t.setStartTime(new SimpleDateFormat("HH:mm:ss").format(cal
						.getTime()));
				t.setStatus(4);
			}

		}

		return m;
	}

}
