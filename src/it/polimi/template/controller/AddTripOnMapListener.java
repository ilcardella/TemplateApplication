package it.polimi.template.controller;


import java.util.ArrayList;

import it.polimi.template.model.*;

public class AddTripOnMapListener {
	
	ArrayList<Trip> trips = new ArrayList<Trip>();


	
	public ArrayList<Trip> createTripWithName(String name, String missionName, ArrayList<Mission> missions){

		Trip t = new Trip();
		t.setName(name);

		
		for(Mission m : missions)
			if (m.getName()==missionName){
				t.setMission(m);

			}
		
		trips.add(t);
		
		return trips;

	}
	
	


	public ArrayList<Trip> getTrips() {
		return trips;
	}


	public void setTrips(ArrayList<Trip> trips) {
		this.trips = trips;
	}
	

	

}
