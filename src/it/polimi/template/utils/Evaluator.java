package it.polimi.template.utils;

import java.util.HashMap;
import java.util.Map;

import it.polimi.template.model.Trip;
import it.polimi.template.model.interfaces.IEvaluator;

public class Evaluator implements IEvaluator {

	private Object actionOutcome;
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
		
		// set the result of the evaluation
		result = "Success";
		return result;
	}

}
