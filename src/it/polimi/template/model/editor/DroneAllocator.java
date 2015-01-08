package it.polimi.template.model.editor;

import java.util.ArrayList;

import it.polimi.template.model.*;
import it.polimi.template.utils.DronesManager;

public class DroneAllocator implements Node {

	@Override
	public Mission run(Mission m) {
		Trip t = m.getTrips().get(0);

		// if there is at least a trip to perform in the mission
		if (!m.getTrips().isEmpty()) {

			if (t.getDelay() > 0) {
				t.setStatus(Trip.DELAYED);
//				System.out.println("Drone Allocator: Trip " + t.getName()
//						+ " is delayed");
			}
			
			for (Drone d : DronesManager.getDrones()) {
				if (d.getStatus() == Drone.FREE
						&& t.getStatus() != Trip.DELAYED) {
					if (t.getItem() == null ||t.getItem().getShapeCategory() == d.getShapeCategory()  ) {
						d.setStatus(Drone.BUSY);
						t.setDrone(d);

//						System.out.println("Drone Allocator: Drone "
//								+ d.getId() + " assigned to Trip "
//								+ t.getName());

						break;
					}

				}

			}

		}

		return m;
	}

}
