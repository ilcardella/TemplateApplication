package it.polimi.template.controller;


import java.util.ArrayList;

import it.polimi.template.model.*;

public class AddMissionButtonListener {
	
	ArrayList<Mission> missions=new ArrayList<Mission>();


	public void createMissionWithName(String name) {
		Mission m = new Mission();
		m.setName(name);
		missions.add(m);
	}

	public ArrayList<Mission> getMissions() {
		return missions;
	}

	public void setMissions(ArrayList<Mission> missions) {
		this.missions = missions;
	}

}
