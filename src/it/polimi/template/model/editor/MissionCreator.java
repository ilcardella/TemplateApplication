package it.polimi.template.model.editor;

import java.util.ArrayList;

import it.polimi.template.model.*;

public class MissionCreator implements Node {


	@Override
	public Mission run(Mission m) {
		return null;
	}

	public Mission run(ArrayList<Trip> trips) {

		Mission m = new Mission();
		m.setTrips(trips);
		m.setStatus(1);

		return m;

	}



}
