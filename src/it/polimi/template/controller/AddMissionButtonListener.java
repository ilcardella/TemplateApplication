package it.polimi.template.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.template.model.*;

public class AddMissionButtonListener implements ActionListener {
	
	ArrayList<Mission> missions = new ArrayList<Mission>();
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Mission m = new Mission();
		missions.add(m);
	}

}
