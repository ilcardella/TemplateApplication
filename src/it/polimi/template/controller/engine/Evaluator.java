package it.polimi.template.controller.engine;

import java.util.HashMap;
import java.util.Map;

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
	public String evaluate() {
		String result;
		// Here the developer has to implement his own implementation
		// Using the actionOutcome object
		//<eval>
		
//		for(Photo photo: dataMap.values()){
//			
//			Trip trip = null;
//			
//			for(Map.Entry<Trip, Object> entry: dataMap.entrySet()){
//				if(entry.getValue().equals(photo)){
//					trip = entry.getKey();
//					break;
//				}
//			}
//			
//			if (photo.hasPest() == true || photo.hasDisease() == true)
//				
//				return "WARNING: Pest/disease at location: "+ dataMap.keySet().iterator() .getTargetLocation();
//
//			if (dataMap.getPhoto().getBloom() == true){
//
//				Trip t = new Trip();
//				trip.setTargetLocation (dataMap.getTrip.getTargetLocation());
//				trip.setAction (Pollinate);
//				trip.setStatus (WAITING);
//
//				m.getTrips().add(t);
//			}
//		}
		
		// set the result of the evaluation
		result = "Success";
		return result;
	}

}
