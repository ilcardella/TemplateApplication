package it.polimi.template.model.editor;

import java.util.ArrayList;
import it.polimi.template.model.*;

public class DroneAllocator implements Node {

	public Mission run(Mission m, ArrayList<Drone> drones) {

		// if there is at least a trip to perform in the mission
		if (!m.getTrips().isEmpty()) {
			for (Trip t : m.getTrips()) {
				// if the drone shapeCategory suits the shapeCategory of the
				// item associated to the first trip, or the trip doesn't have
				// an associated item, assign that drone to the that trip
				for (Drone d : drones) {
					if ((t.getItem().getName().equals("no item"))
							|| (t.getItem().getShapeCategory() == d
									.getShapeCategory())) 
						t.setDrone(d);

						//break;
					

				}
			}

		}
		return m;
	}

	@Override
	public Mission run(Mission m) {
		// TODO Auto-generated method stub
		return null;
	}

}
