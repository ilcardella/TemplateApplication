package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import java.util.Observable;
import java.util.Observer;

public class GateFIFO extends Node implements Observer {

	MissionWorker mw;
	Mission mBuffer = null;

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
		
		if(mBuffer == null){
			mBuffer = m;
			return m;
		}
		
		// if(tutte le istanze attese sono arrivate) -> mBuffer = null;
		
		return null;
	}

}
