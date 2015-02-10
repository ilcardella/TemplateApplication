package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;

import java.util.Observable;
import java.util.Observer;

public class ActionEvaluator extends Node implements Observer {

	MissionWorker mw;
	
	public ActionEvaluator(MissionWorker mw) {
		this.mw = mw;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		setChanged();
		notifyObservers(m);
	}

	@Override
	public Mission run(Mission m) {
		// TODO Auto-generated method stub
		return m;
	}

}
