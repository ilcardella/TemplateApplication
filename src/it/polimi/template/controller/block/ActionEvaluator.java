package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import java.util.Observable;
import java.util.Observer;

public class ActionEvaluator extends Node implements Observer {

	MissionWorker mw;

	public ActionEvaluator(MissionWorker mw) {
		this.mw = mw;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		if (m != null) {
			setChanged();
			notifyObservers(m);
		}
	}

	@Override
	public Mission run(Mission m) {
		// we need to evaluate an action only if the mission is in standby or completed
		if (m.getStatus() == Mission.COMPLETED) {
			// Start the evaluation process of the last Action
			String result = m.getEvaluator().evaluate();
			// If the result is NOT "Success"
			if (!result.equals("Success")) {
				// Redo the last Trip
				// Get the last completed trip
				Trip last = m.getCompletedTrips().get(
						m.getCompletedTrips().size() - 1);
				// Set the status to Waiting
				last.setStatus(Trip.WAITING);
				// Put that trip in the first position of the tripList to be
				// done
				m.getTrips().add(0, last);

				mw.log(m, "Trip " + last.getName() + " will be executed again.");
			}
			return m;
		}
		return null;
	}

}
