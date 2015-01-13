package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.controller.thread.MyWorker;
import it.polimi.template.model.*;

public class TripMonitor extends Node implements Observer {

	MyWorker w;
	
	public TripMonitor(MyWorker w) {
		this.w = w;
	}
	
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
				w.log(m, "Mission "+m.getName()+" is FAILED");
				return m;
			}

			// if there are no more trips to perform, the mission is completed
			if (m.getTrips().isEmpty()) {
				m.setStatus(Mission.COMPLETED);
				w.log(m, "Mission "+m.getName()+" is COMPLETED");
				return m;
			}
			// otherwise there is at least another trip in the mission and the
			// mission status is set to standby
			else {
				m.setStatus(Mission.STANDBY);
				w.log(m, "Mission "+m.getName()+" is STANDBY");
				return m;
			}

		}
		// if the current trip is completed, remove it from the list of trips
		// associated to the mission
		m.getTrips().remove(0);

		if (m.getTrips().isEmpty()) {
			m.setStatus(Mission.COMPLETED);
			w.log(m, "Mission "+m.getName()+" is COMPLETED");
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
