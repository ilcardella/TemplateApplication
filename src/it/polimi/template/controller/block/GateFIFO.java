package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import java.util.Observable;
import java.util.Observer;

public class GateFIFO extends Node implements Observer {

	MissionWorker mw;
	boolean flag = true; // control flag that denote if the first trip occurence is already passed
	Mission mBuffer; // the mission occurence that passed as first one

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

		// if flag is false, it means that the first mission is already passed
		// if flaf is true, this is the first occurence coming in
		if (flag == true) {
			// set flag false so that other occurrences will not pass
			flag = false;
			// save in the buffer the mission
			mBuffer = m;
			
			return m;
		}
		
		return null;
	}

}
