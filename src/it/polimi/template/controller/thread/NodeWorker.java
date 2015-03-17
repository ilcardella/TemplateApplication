package it.polimi.template.controller.thread;

import it.polimi.template.controller.block.Node;
import it.polimi.template.model.Mission;

import javax.swing.SwingWorker;

public class NodeWorker  extends SwingWorker<Mission, String> {

	private Node block;
	private Mission m;
	
	public NodeWorker(Node b, Mission m){
		this.block = b;
		this.m = m;
	}
	
	@Override
	protected Mission doInBackground() throws Exception {
		return this.block.run(m);
	}

}
