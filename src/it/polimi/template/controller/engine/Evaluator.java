package it.polimi.template.controller.engine;

import java.util.HashMap;
import java.util.Map;

import it.polimi.template.model.Action;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.model.interfaces.IEvaluator;

public class Evaluator implements IEvaluator {

	private Map<Trip, Object> dataMap = new HashMap<Trip, Object>();

	@Override
	public boolean writeActionOutcome(Object outcome, Trip trip) {
		// save the action result
		dataMap.put(trip, outcome);
		return true;
	}

	@Override
	public String evaluate(Mission missionToEvaluate) {
		String result = null;
		// Here the developer has to implement his own implementation
		// <eval>

		// Retrieve all entries of the map
		for (Map.Entry<Trip, Object> entry : dataMap.entrySet()) {

			// consider only the trips related to the mission to evaluate
			if (missionToEvaluate.getCompletedTrips().contains(entry.getKey())) {
				
				// take the Photo of the current Trip iteration
				Photo photo = (Photo) entry.getValue();
				
				// checking pest and disease
				if (photo.hasPest() || photo.hasDisease())
					
					return "WARNING: Pest/disease at location: "
							+ dataMap.keySet().iterator().getTargetLocation();

				// checking for some bloom
				if (photo.hasBloom()) {

					// create a new Trip to pollinate the flowers
					Trip trip = new Trip();
					trip.setName("PollinateTrip");
					trip.setTargetLocation(entry.getKey().getTargetLocation());
					trip.setAction(Action.POLLINATE);
					trip.setStatus(Trip.WAITING);

					// add this new trip to the list of the mission
					missionToEvaluate.getTrips().add(trip);
					// set the mission status to STANDBY
					missionToEvaluate.setStatus(Mission.STANDBY);
				}
			}
		}

		// set the result of the evaluation
		result = "Success";
		return result;
	}
}
