package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.*;
import it.polimi.template.utils.DronesManager;

public class DroneAllocator extends Node implements Observer {

	MissionWorker w;

	public DroneAllocator(MissionWorker w) {
		this.w = w;
	}

	@Override
	public Mission run(Mission m) {
		boolean droneAssigned = false;
		// Here only missions in standby or unexecuted must be considered
		if (m.getStatus() == Mission.UNEXECUTED
				|| m.getStatus() == Mission.STANDBY) {

			// The next Trip must be the one with the higher priority
			// If all Trips have the same priority, then it will be the first in the list
			Trip t = m.getTrips().get(0);
			int maxPriority = t.getPriority();
			for(Trip x: m.getTrips()){
				if(x.getPriority() > t.getPriority())
					t = x;
			}

			// if there is at least a trip to perform in the mission
			if (!m.getTrips().isEmpty()) {

				// until a drone has not been assigned we need to wait for an available drone
				while (!droneAssigned) {
					// for each drones
					for (Drone d : DronesManager.getDrones()) {
						// if the drone is free
						if (d.getStatus() == Drone.FREE) {
							// if there is an item, check the shapecategory
							if (t.getItem() == null
									|| t.getItem().getShapeCategory() == d
											.getShapeCategory()) {

								d.setStatus(Drone.BUSY);
								t.setDrone(d);
								w.log(m, "Drone " + d.getId()
										+ " assigned to Trip " + t.getName());
								w.log(m, "Drone " + d.getId() + " is BUSY");

								droneAssigned = true; // set flag to true to exit to the while
								break; // exit the for loop
							}

						}

					} // end of for loop
				}
				return m;
			}
		}
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		if (m != null) {
			setChanged();
			notifyObservers(m);
		}
	}

}
