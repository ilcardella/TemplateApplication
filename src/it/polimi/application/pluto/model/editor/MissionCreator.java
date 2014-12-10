package it.polimi.application.pluto.model.editor;

import it.polimi.application.pluto.model.Mission;
import it.polimi.application.pluto.model.Trip;

import java.util.ArrayList;





public class MissionCreator extends Node {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mission run(ArrayList<Trip> trips) {

		Mission m = new Mission();
		m.setTrips(trips);
		m.setStatus(Mission.UNEXECUTED);

		return m;

	}

}
