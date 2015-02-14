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

		

		// set the result of the evaluation
		result = "Success";
		return result;
	}
}






/* ######################################################################################
* 
* Here are the implementation of the studied applications
* 
* 
* ALFALFA 
* 
  // Retrieve all entries of the map
		for (Map.Entry<Trip, Object> entry : dataMap.entrySet()) {

			// consider only the trips related to the mission to evaluate
			if (missionToEvaluate.getCompletedTrips().contains(entry.getKey())) {
				
				// take the Photo of the current Trip iteration
				Photo photo = (Photo) entry.getValue();
				
				// checking pest and disease
				if (photo.hasPest() || photo.hasDisease())
					
					return "WARNING: Pest/disease at location: "
							+ entry.getKey().getTargetLocation();

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
* 
* --------------------------------------------------------------------------------------------
* 
* PUTTI DANZANTI
* 
  // generate the final ortophoto
		OrtoPhoto ortophoto = stitch(dataMap.values());
		
		// if the aberration is greater than a fixed threshold
		if(ortophoto.getAberration() > ABERRATION_THRESHOLD){
			
			// for each photo that compose the ortophoto
			for (Photo photo: ortophoto.getPhotoCollection()){
				
				// if the overlap of the photo is not enough
				if (ortophoto.getOverlapOfGivenPhoto(photo) < OVERLAP_THRESHOLD){
					
					// Retrieve all entries of the map
					for (Map.Entry<Trip, Object> entry : dataMap.entrySet()) {
						
						// take the Photo of the current iteration
						Photo p = (Photo) entry.getValue();
						
						// if we found the photo in the map
						if(photo.equals(p)){
							
							// create a new Trip
							Trip trip = new Trip();
							trip.setName("NewTrip");
							trip.setTargetLocation(entry.getKey().getTargetLocation());
							trip.setAction(Action.TAKE_PHOTO);
							trip.setStatus(Trip.WAITING);

							// add this new trip to the list of the mission
							missionToEvaluate.getTrips().add(trip);
							
							// set the mission status to STANDBY
							missionToEvaluate.setStatus(Mission.STANDBY);
						}
					}
				}
			}
		}

* --------------------------------------------------------------------------------------------
* PM10
* 
	// building a new map with only the trip-measure couples of the mission
		// to evaluate
		Map<Trip, Integer> missionMap = new Map<Trip, Integer>();
		for (Map.Entry<Trip, Object> entry : dataMap.entrySet()) {
			if (missionToEvaluate.getCompletedTrips().contains(entry.getKey())) {
				missionMap.put(entry.getKey(), (Integer) entry.getValue());
			}
		}

		// this method use the Trip location and the pollution measure to
		// calculate
		// the gradients and then return a list of String that indicates
		// the positions of these gradients
		List<String> gradientsPositions = calculateGradients(missionMap);

		for (String position : gradientsPositions) {

			// create a new Trip to calculate pollution at the gradient position
			Trip trip = new Trip();
			trip.setName("GradientTrip");
			trip.setTargetLocation(position);
			trip.setAction(Action.MEASURE);
			trip.setStatus(Trip.WAITING);

			// add this new trip to the list of the mission
			missionToEvaluate.getTrips().add(trip);
			// set the mission status to STANDBY
			missionToEvaluate.setStatus(Mission.STANDBY);

		}

* 
* --------------------------------------------------------------------------------------------
* PURSUE
* 
  CODE HERE
* 
* ---------------------------------------------------------------------------------------------
*/
