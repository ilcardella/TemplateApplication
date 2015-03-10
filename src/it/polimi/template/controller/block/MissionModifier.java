package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class MissionModifier extends Node implements Observer {

	@Override
	public Mission run(Mission m) {
		return m;
	}

//	@Override
//	public void update(Observable o, Object arg) {
//		Mission m = this.run((Mission) arg);
//		if (m != null) {
//			setChanged();
//			notifyObservers(m);
//		}
//	}

}
