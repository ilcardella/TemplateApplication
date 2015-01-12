package it.polimi.template.model.editor;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class MissionModifier extends Node implements Observer {

	@Override
	public Mission run(Mission m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		notifyObservers(m);
	}

}
