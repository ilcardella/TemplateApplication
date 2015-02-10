package it.polimi.template.utils;

import it.polimi.template.model.interfaces.IEvaluator;

public class Evaluator implements IEvaluator {

	@Override
	public boolean writeActionResult(Object result) {
		//<eval>
		return false;
	}

	@Override
	public Object getActionResult() {
		//<eval>
		return null;
	}

}
