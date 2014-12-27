package it.polimi.template.controller;


import java.util.ArrayList;

import it.polimi.template.model.*;

public class MissionsPageAddMissionButtonListener {
	


	public void createMissionWithName(String name, ArrayList<Mission> missions) {
		Mission m = new Mission();
		m.setName(name);
		missions.add(m);
	}



}
