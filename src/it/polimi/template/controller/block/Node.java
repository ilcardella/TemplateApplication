package it.polimi.template.controller.block;

import java.util.Observable;
import it.polimi.template.model.*;

public abstract class Node extends Observable{
	
	public abstract Mission run(Mission m);

}
