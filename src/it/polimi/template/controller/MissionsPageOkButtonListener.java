package it.polimi.template.controller;

import java.util.ArrayList;

import java.util.List;

import it.polimi.template.model.*;
import it.polimi.template.model.editor.*;

public class MissionsPageOkButtonListener {

	public void startDroneAllocator(Mission m, ArrayList<Drone> drones) {

		sortDescendant(m.getTrips());
		System.out.print(m.getTrips().get(0).getName());
		DroneAllocator da = new DroneAllocator();
		da.run(m, drones);
	}

	public void sortDescendant(List<Trip> trips) {

		Trip temp = new Trip();

		for (int i = 0; i < trips.size(); i++) {
			for (int j = i+1; j < trips.size(); j++) {
				if (trips.get(i).getPriority() < trips.get(j).getPriority())
					temp = trips.set(i, trips.get(j));
				trips.set(j, temp);
			}
		}
	}

}
