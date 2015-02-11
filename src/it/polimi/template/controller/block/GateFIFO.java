package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;

import java.util.Observable;
import java.util.Observer;

public class GateFIFO extends Node implements Observer {

	MissionWorker mw;

	public GateFIFO(MissionWorker mw) {
		this.mw = mw;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Mission m = this.run((Mission) arg1);
		if (m != null) {
			setChanged();
			notifyObservers(m);
		}
	}

	@Override
	public Mission run(Mission m) {
		// TODO Auto-generated method stub
		return m;
	}

}
