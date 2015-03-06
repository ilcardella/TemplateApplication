package it.polimi.template.controller.block;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Mission;
import java.util.Observable;
import java.util.Observer;

public class GateFIFO extends Node implements Observer {

	MissionWorker mw;
	boolean firstPassed = false;		 // instance of passed mission
	int incomingConnections = <num>; // incoming connections of the block, 
								 // this value is generated dinamically by Pluto Graphical Editor
	int counter = 0; 
	
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
		
		// if no instance has been passed yet
		if( !firstPassed ){
			firstPassed = true; // set flag to true
			counter++;
			return m;
		}
		// else if not all parallel instances have been passed yet
		else /*if(counter < incomingConnections)*/{
			counter++;
			// if this instance was the last one that had to be passed
			if(counter == incomingConnections){
				firstPassed = false; // set the flag to null
			}
			return null;
		}
//		return null;
		
	}

}
