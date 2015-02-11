package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;

import java.util.Observable;
import java.util.Observer;

public class GateFIFO extends Node implements Observer {

	MissionWorker mw;
	boolean flag = true; // control flag that denote if the first trip occurence is already passed
	Trip lastTripPassed; // the trip occurence that passed as first one, among all trips incoming in this block

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

		// if flag is false, it means that one of the multiple trip occurences is already passed
		// if flaf is true, this is the first occurence coming in
		if (m != null && flag == true) {
			// set flag false so that other occurrences will not pass
			flag = false;

			// TODO
			
			return m;
		}

		// flag = true;
		return null;
	}

}
