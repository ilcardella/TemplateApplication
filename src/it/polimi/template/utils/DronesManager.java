package it.polimi.template.utils;

import it.polimi.template.model.Drone;

import java.util.ArrayList;
import java.util.List;

public class DronesManager {
	
	private static List<Drone> drones;
	private final static int NUM_OF_DRONE = 30;
	
	public synchronized static List<Drone> getDrones() {
		if (drones == null) {
			drones = new ArrayList<Drone>();

			for (int i = 0; i < NUM_OF_DRONE; i++) {
				Drone d = new Drone();
				d.setShapeCategory(1);
				drones.add(d);
			}
		}
		return drones;
	}
}
