package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.*;

public class TimerMonitor extends Node implements Observer {

	MissionWorker missionThread;
	
	public TimerMonitor(MissionWorker m){
		this.missionThread = m;
	}
	
	@Override
	public Mission run(Mission m) {
		// TODO implementare il metodo
		return m;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mission m = this.run((Mission) arg);
		setChanged();
		notifyObservers(m);
	}

}
