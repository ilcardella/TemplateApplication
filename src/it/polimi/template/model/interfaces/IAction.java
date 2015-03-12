package it.polimi.template.model.interfaces;

public interface IAction {
	
	static final long ACTION_TIME = 1000;

	public Object doAction();
	
	public boolean isItemRequired();
}
