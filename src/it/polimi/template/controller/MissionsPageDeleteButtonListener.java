package it.polimi.template.controller;

import java.util.ArrayList;

import it.polimi.template.model.*;

public class MissionsPageDeleteButtonListener {
	
	public void remove(ArrayList<Mission> missions, String mission){
		
		for(int i=0;i<missions.size();i++)
			if(missions.get(i).getName().equals(mission))
				missions.remove(i);
		
	}

}
