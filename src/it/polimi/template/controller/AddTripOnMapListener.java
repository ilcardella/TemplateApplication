package it.polimi.template.controller;


import java.util.ArrayList;

import it.polimi.template.model.*;

public class AddTripOnMapListener {
	


	
	public ArrayList<Trip> createTripWithName(String name, String missionName, ArrayList<Mission> missions, ArrayList<Trip> trips){

		Trip t = new Trip();
		t.setName(name);

		
		for(Mission m : missions)
			if (m.getName()==missionName)
				t.setMission(m);

			
		
		trips.add(t);
		
		return trips;

	}
	
	



	

}
