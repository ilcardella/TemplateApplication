package it.polimi.template.controller;

import java.util.ArrayList;

import it.polimi.template.model.*;

public class AddTripOnMapListener {

	public void createTripWithName(String name, String nameItem,
			String priority, String delay, String missionName,
			ArrayList<Mission> missions, 
			ArrayList<Item> items) {

		Trip t = new Trip();
		t.setName(name);
		t.setPriority(Integer.parseInt(priority));
		t.setDelay(Integer.parseInt(delay));


		for (Item i : items)
			if (i.getName().equals(nameItem))
				t.setItem(i);

		for (Mission m : missions)
			if (m.getName().equals(missionName)){
				t.setMission(m);
				m.getTrips().add(t);
			}

		


	}

}
