package it.polimi.template.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import it.polimi.template.view.MissionsPage;
import it.polimi.template.view.MonitorPage;
import it.polimi.template.view.TripsPage;
import it.polimi.template.model.*;

public class MissionPageController {

	private MissionsPage missionPage;
	List<Mission> missions;

	public MissionPageController(MissionsPage view) {

		this.missionPage = view;
		this.missionPage
				.setAddMissionButtonListener(new AddMissionButtonListener());
		this.missionPage
				.setDeleteMissionButtonListener(new DeleteMissionButtonListener());
		this.missionPage
				.setRemoveAllMissionButtonListener(new RemoveAllMissionButtonListener());
		this.missionPage.setRenameButtonListener(new RenameButtonListener());
		this.missionPage.setTripsButtonListener(new SetTripsButtonListener());
		this.missionPage
				.setMissionsPageOkButtonListener(new MissionsPageOkButtonListener());

	}

	class AddMissionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Mission m = new Mission();
			String name = missionPage.showNewNamePanel("Write mission name");
			if (name != "") {
				m.setName(name);
				if (missions == null)
					missions = new ArrayList<Mission>();
				missions.add(m);

				missionPage.addMissionToList(name);
			}
		}

	}

	class DeleteMissionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = missionPage.getSelectedMission();
			for (int i = 0; i < missions.size(); i++)
				if (missions.get(i).getName().equals(name))
					missions.remove(i);
			missionPage.removeMissionFromList(name);

		}

	}

	class RemoveAllMissionButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			missions.clear();

			missionPage.clearMissionList();
		}

	}

	class RenameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String oldName = missionPage.getSelectedMission();
			String newName = missionPage.showNewNamePanel("Rename Mission");
			for (int i = 0; i < missions.size(); i++)
				if (missions.get(i).getName().equals(oldName))
					missions.get(i).setName(newName);
			missionPage.renameSelectedMission(newName);
		}
	}

	class SetTripsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String missionName = missionPage.getSelectedMission();

			if (!missionName.equals("")) {

				for (int i = 0; i < missions.size(); i++) {

					if (missions.get(i).getName().equals(missionName)) {

						Mission m = missions.get(i);

						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								TripsPage tripsPage = new TripsPage(missionName);
								TripsPageController tripsPageController = new TripsPageController(
										tripsPage, m);
								tripsPage.setVisible(true);
							}
						});
					}
				}
			}
		}
	}

	class MissionsPageOkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MonitorPage monitorPage = new MonitorPage();
			MonitorPageController monitorController = new MonitorPageController(
					monitorPage, missions);
			monitorPage.setVisible(true);
		}
	}
	
	class SetTripsMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
