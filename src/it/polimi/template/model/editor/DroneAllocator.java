package it.polimi.template.model.editor;
import java.util.ArrayList;
import it.polimi.template.model.*;

public class DroneAllocator implements Node {



	public Mission run(Mission m,ArrayList<Drone> drones) {
		
		// if there is at least a trip to perform in the mission
		if (!m.getTrips().isEmpty()) {
			for (Drone d : drones) {
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

	@Override
	public Mission run(Mission m) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
