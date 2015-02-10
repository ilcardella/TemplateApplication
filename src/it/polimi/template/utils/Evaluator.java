package it.polimi.template.utils;

import it.polimi.template.model.interfaces.IEvaluator;

public class Evaluator implements IEvaluator {

	private Object actionOutcome;
	private String result;

	@Override
	public boolean writeActionOutcome(Object outcome) {
		// save the action result
		this.actionOutcome = outcome;
		return true;
	}

	@Override
	public String getEvaluationResult() {
		return result;
	}

	public void evaluate() {
		// Here the developer has to implement his own implementation
		// Using the actionOutcome object
		// <eval>
		
		// set the result of the evaluation
		this.result = "Success";
	}

}
