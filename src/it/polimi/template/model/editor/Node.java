package it.polimi.template.model.editor;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public abstract class Node extends Observable{
	
	public abstract Mission run(Mission m);

}
