package it.polimi.template.controller;

import java.util.ArrayList;

import it.polimi.template.model.*;
import it.polimi.template.model.editor.*;

public class MissionsPageOkButtonListener {
	
	public void startDroneAllocator(Mission m, ArrayList<Drone> drones){
	
	DroneAllocator da = new DroneAllocator();
	
	da.run(m,drones);
	}

}
