package it.polimi.template.model.editor;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;
import it.polimi.template.utils.DronesManager;

public class DroneAllocator extends Node implements Observer {

	@Override
	public Mission run(Mission m) {
		Trip t = m.getTrips().get(0);

		// if there is at least a trip to perform in the mission
		if (!m.getTrips().isEmpty()) {

			for (Drone d : DronesManager.getDrones()) {
				if (d.getStatus() == Drone.FREE
						&& t.getStatus() != Trip.DELAYED) {
					if (t.getItem() == null
							|| t.getItem().getShapeCategory() == d
									.getShapeCategory()) {
						d.setStatus(Drone.BUSY);
						t.setDrone(d);
						
						break;
					}

				}

			}

		}

		return m;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		setChanged();
		notifyObservers(m);
		
	}

}
