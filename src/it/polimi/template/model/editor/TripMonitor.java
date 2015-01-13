package it.polimi.template.model.editor;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class TripMonitor extends Node implements Observer {

	@Override
	public Mission run(Mission m) {
		Trip t = m.getTrips().get(0);

		// TODO in questo ciclo while non ci entriamo mai perchè il tripLauncher prima di notificare aspetta che il drone abbia finito il trip
		// bisogna reimplementare il tutto senza ciclo ma con solo if
		while (t.getStatus() != Trip.COMPLETED) {

			// if the current trip is failed for an exception, the mission
			// status is
			// set to failed
			if (t.getStatus() == Trip.FAILED) {
				m.setStatus(Mission.FAILED);
				System.out.println("Mission " + m.getName() + " is failed");
				return m;
			}

			// if there are no more trips to perform, the mission is completed
			if (m.getTrips().isEmpty()) {
				m.setStatus(Mission.COMPLETED);
				System.out.println("Mission " + m.getName() + " is completed");
				return m;
			}
			// otherwise there is at least another trip in the mission and the
			// mission status is set to standby
			else {
				m.setStatus(Mission.STANDBY);
				System.out.println("Mission " + m.getName() + " is standby");

				return m;
			}

		}
		// if the current trip is completed, remove it from the list of trips
		// associated to the mission
		m.getTrips().remove(0);

		if (m.getTrips().isEmpty()) {
			m.setStatus(Mission.COMPLETED);
			System.out.println("Mission " + m.getName() + " is completed");
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
