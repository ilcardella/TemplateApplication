package it.polimi.template.controller.block;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.*;

public class ConditionBlock extends Node implements Observer {

	@Override
	public Mission run(Mission m) {

		return m;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
