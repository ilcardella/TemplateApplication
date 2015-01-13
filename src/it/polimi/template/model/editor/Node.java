package it.polimi.template.model.editor;

import java.util.Observable;
import it.polimi.template.model.*;

public abstract class Node extends Observable{
	
	public abstract Mission run(Mission m);
	
	/**
	 * This method notify the observers of the mission so that the view can be updated
	 * @param m
	 * @param s
	 */
	void log(Mission m, String s){
		System.out.println(s);
		m.setAsChanged();
		m.notifyObservers(s);
	}

}
