package it.polimi.template.controller;

import java.util.ArrayList;

import it.polimi.template.model.Drone;
import it.polimi.template.model.Mission;
import it.polimi.template.model.editor.Clock;
import it.polimi.template.model.editor.TripLauncher;

public class DronesPageStartButtonListener {
	
	
	public void StartClock(Mission m, ArrayList<Drone> drones){
		Clock c = new Clock(drones);

	c.run(m);
	}
	
	public void StartTripLauncher(Mission m){
		TripLauncher tl = new TripLauncher();

	tl.run(m);
	}
}
