package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class GateFIFO extends Node implements Observer {

	MissionWorker mw;		
	// TODO Tag
	int incomingConnections = <num>; 	
	int counter = 0;
	Set<Mission> missionBuffer = new HashSet<Mission>();
	
	public GateFIFO(MissionWorker mw) {
		this.mw = mw;
	}

//	@Override
//	public void update(Observable arg0, Object arg1) {
//		Mission m = this.run((Mission) arg1);
//		if (m != null) {
//			setChanged();
//			notifyObservers(m);
//		}
//	}

	@Override
	public Mission run(Mission m) {
		// if we have only one incoming conn
		// we alwayls let the mission pass through
		if(incomingConnections == 1){
			return m;
		}
		// if no instance of m has been passed yet
		else if( !missionBuffer.contains(m) ){
			counter++;
			missionBuffer.add(m);
			
			return m;
		}
		// if m is in the buffer we stop it
		else {
			counter++;
			// if this instance was the last one that had to be passed
			if(counter == incomingConnections){
				missionBuffer.remove(m);
			}
			return null;
		}
		
	}

}
