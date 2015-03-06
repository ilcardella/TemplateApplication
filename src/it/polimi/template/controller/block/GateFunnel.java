package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;

import java.util.Observable;
import java.util.Observer;

public class GateFunnel extends Node implements Observer {
	MissionWorker w;

	public GateFunnel(MissionWorker worker) {
		this.w = worker;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		if (m != null) {
			setChanged();
			notifyObservers(m);
		}
	}

	@Override
	public Mission run(Mission m) {
		//TODO write algorithm
		return null;
	}
}
