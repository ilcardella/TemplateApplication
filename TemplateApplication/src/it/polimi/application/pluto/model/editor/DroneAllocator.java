package it.polimi.application.pluto.model.editor;

import it.polimi.application.pluto.model.Drone;
import it.polimi.application.pluto.model.Mission;

import java.util.ArrayList;
import java.util.List;



public class DroneAllocator extends Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Mission run(Mission m) {
		List<Drone> availableDrones = new ArrayList<Drone>(); // =getAvailableDrones()
		// if there is at least a trip to perform in the mission
		if (!m.getTrips().isEmpty()) {
			for (Drone d : availableDrones) {
				// if the drone shapeCategory suits the shapeCategory of the
				// item associated to the first trip,
				// assign that drone to the that trip
				if (m.getTrips().get(0).getItem().getShapeCategory() == d
						.getShapeCategory()) {
					m.getTrips().get(0).setDrone(d);

					break;
				}
			}
		}
		return m;
	}

}
