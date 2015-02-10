package it.polimi.template.model.interfaces;

public interface IEvaluator {
	
	// This method let the drone to save the result
	// obtained from the action, it could be
	// a photo, a temperature value, a string, ecc.
	public boolean writeActionResult(Object result);
	
	// This method let the Mission to retrieve the result
	// of the last action
	public Object getActionResult();
}
