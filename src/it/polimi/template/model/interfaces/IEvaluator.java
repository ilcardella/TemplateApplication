package it.polimi.template.model.interfaces;

import it.polimi.template.model.Trip;

public interface IEvaluator {
	
	// This method let the trip to save the action outcome, it could be
	// a photo, a temperature value, a string, ecc.
	public boolean writeActionOutcome(Object outcome, Trip trip);
	
	// This method evaluate the actions results and give a string output
	public String evaluate();
}
