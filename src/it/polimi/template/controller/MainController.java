package it.polimi.template.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import it.polimi.template.view.MissionsPage;
import it.polimi.template.model.*;

public class MainController {

	private MissionsPage missionPage;
	List<Item> items;
	List<Drone> drones;
	List<Mission> missions;


	public MainController(MissionsPage view, List<Item> items,
			List<Drone> drones) {

		this.missionPage = view;
		this.items = items;
		this.drones = drones;
		this.missionPage.addMissionButtonListener(new AddMissionButtonListener());
		this.missionPage.deleteMissionButtonListener(new DeleteMissionButtonListener());


	}

	class AddMissionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Mission m = new Mission();
			String name=missionPage.showNewMissionNamePanel();
			m.setName(name);
			if(missions==null)
				missions = new ArrayList<Mission>();
			missions.add(m);
			
			missionPage.addMissionToList(name);
		}

	}
	
	class DeleteMissionButtonListener implements ActionListener {
		
		public void remove(ArrayList<Mission> missions, String mission){
			
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String name=missionPage.getSelectedMission();
			for(int i=0;i<missions.size();i++)
				if(missions.get(i).getName().equals(name))
					missions.remove(i);
		}

	}

}
