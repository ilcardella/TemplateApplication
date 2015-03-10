package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;

import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.controller.thread.NodeWorker;
import it.polimi.template.model.*;

public class MissionCreator extends Node {

	MissionWorker w;

	public MissionCreator(MissionWorker w) {
		this.w = w;
	}

	public Mission run(Mission m) {
		if (m.getTrips() != null && m.getStatus() == Mission.UNEXECUTED) {
			m.setStatus(Mission.UNEXECUTED);
			w.log(m, "Mission " + m.getName() + " is UNEXECUTED");
			return m;
		} else
			return null;
	}

//	@Override
//	public void update(Observable o, Object arg) {
//		// TODO al posto di chiamare la run, lanciare il NodeWorker
//		// il quale avvia la run
//		NodeWorker blockThread = new NodeWorker(this, (Mission) arg);
//		blockThread.execute();
//		try {
//			Mission m = blockThread.get();
//			if (m != null) {
//				setChanged();
//				notifyObservers(m);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
////		Mission m = this.run((Mission) arg);
//		
//	}

}
