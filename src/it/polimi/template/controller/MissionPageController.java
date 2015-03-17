package it.polimi.template.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import it.polimi.template.view.MissionsPage;
import it.polimi.template.view.MonitorPage;
import it.polimi.template.view.TripsPage;
import it.polimi.template.model.*;

public class MissionPageController {

	//TODO Tag
	private final boolean MISSION_REPETITION_ENABLED = false/*<mRep>*/; //default: false
	private final boolean TRIP_SAFETIME_ENABLED = true/*<tSafe>*/; //default: true
	
	private MissionsPage missionPage;
	List<Mission> missions = new ArrayList<Mission>();

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
		this.missionPage.setListMouseListener(new ListMouseListener());
		
		// --------------------- This is for evaluation -------------------------
		//-----------------------------------------------------------------------
		createEvaluationModel();
		// ----------------------------------------------------------------------

	}

	private void createEvaluationModel() {
		int mNum = 5;
		int tNum = 5;
		for(int i = 0; i< mNum; i++){
			Mission m = new Mission();
			m.setName("mission"+i);
			for(int j=0; j<tNum; j++){
				Trip t = new Trip();
				t.setAction(Action.MEASURE);
				t.setName(m.getName()+"-"+j);
				t.setParentMission(m);
				t.setTargetLocation("4/4");
				m.getTrips().add(t);
			}
			missions.add(m);
			missionPage.addMissionToList(m.getName());
		}
	}

	class AddMissionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Mission m = new Mission();
			String name = missionPage.showNewNamePanel("Write mission name");
			if (name != "") {
				// update the model
				m.setName(name);
				if (missions == null)
					missions = new ArrayList<Mission>();
				missions.add(m);

				// update the view
				missionPage.addMissionToList(name);
				
				if(MISSION_REPETITION_ENABLED)
					m.setRepeat(missionPage.showRepeatPanel());
				if(TRIP_SAFETIME_ENABLED){
					long time = missionPage.showTimerPanel() * 60 * 1000; // transform in millis
					if(time < 1)
						m.setSafeTimer(600000); // 10 minutes in millis as default
					else
						m.setSafeTimer(time);
				}
			}
		}

	}

	class DeleteMissionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = missionPage.getSelectedMission();
			for (int i = 0; i < missions.size(); i++) {
				if (missions.get(i).getName().equals(name)) {
					missions.remove(i);
					missionPage.removeSelectedMissionFromList();
				}
			}
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
			launchTripsPage();
		}
	}

	class MissionsPageOkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					MonitorPage monitorPage = new MonitorPage();
					MonitorPageController monitorController = new MonitorPageController(
							monitorPage, missionPage, missions);
					monitorPage.setVisible(true);
				}
			});
			missionPage.setVisible(false);
		}
	}

	class ListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				launchTripsPage();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

	private void launchTripsPage() {
		final String missionName = missionPage.getSelectedMission();

		if (!missionName.equals("")) {

			for (int i = 0; i < missions.size(); i++) {

				if (missions.get(i).getName().equals(missionName)) {

					final Mission m = missions.get(i);

					// Build hashmap with tripsName and its position
					final Map<String, String> map = new HashMap<String, String>();
					if (m.getTrips() != null && m.getTrips().size() > 0) {
						for (Trip t : m.getTrips()) {
							map.put(t.getName(), t.getTargetLocation());
						}
					}
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							TripsPage tripsPage = new TripsPage(missionName,
									map);
							TripsPageController tripsPageController = new TripsPageController(
									tripsPage, m);
							tripsPage.setVisible(true);
						}
					});
				}
			}
		}
		else{
			missionPage.showNoMissionSelectedPanel();
		}
	}

}
