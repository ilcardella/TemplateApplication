package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.controller.thread.NodeWorker;
import it.polimi.template.model.*;

public abstract class Node extends Observable implements Observer{

	public abstract Mission run(Mission m);
	
	@Override
	public void update(Observable o, Object arg) {
		NodeWorker blockThread = new NodeWorker(this, (Mission) arg);
		blockThread.execute();
		try {
			while(!blockThread.isDone()){} // wait for the end of the thread execution
			Mission m = blockThread.get();
			if (m != null) {
				setChanged();
				notifyObservers(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
