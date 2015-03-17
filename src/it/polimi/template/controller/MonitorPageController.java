package it.polimi.template.controller;

import it.polimi.template.controller.engine.Engine;
import it.polimi.template.controller.thread.MissionWorker;
import it.polimi.template.model.Drone;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.utils.DronesManager;
import it.polimi.template.view.MissionsPage;
import it.polimi.template.view.MonitorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

public class MonitorPageController {

	private MonitorPage monitorPage;
	private MissionsPage missionPage;
	private Engine engine;


	public MonitorPageController(MonitorPage monitorPage,
			MissionsPage missionPage, List<Mission> missions) {
		
		this.monitorPage = monitorPage;
		this.missionPage = missionPage;
		this.engine = new Engine(this);
		this.engine.setMissions(missions);

		this.monitorPage.setStartButtonListener(new StartButtonListener());
		this.monitorPage.setStopButtonListener(new StopButtonListener());
		this.monitorPage.setBackButtonListener(new BackButtonListener());
	}

	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			engine.startMissionsExecution();

		}
	}

	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (engine.isRunning()) {
				monitorPage.showDialog("Please wait for all missions completion!");
			} else {

				for (Iterator<Mission> iter = engine.getMissions()
						.listIterator(); iter.hasNext();) {
					Mission m = iter.next();
					if (m.getStatus() == Mission.COMPLETED) {
						missionPage.removeMissionFromList(m.getName());
						iter.remove();
					}
				}
				monitorPage.dispose();
				missionPage.setVisible(true);
			}
		}
	}

	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// prompt user what to do
			String selection = monitorPage.showStopOptions();
			// cancel all threads
			engine.stopMissionsExecution(selection);

		}
	}

	public synchronized void log(Mission m, String s) {
		updateMonitorTable(m);
		printToMonitorConsole(s);
		System.out.println(s);
	}

	private void updateMonitorTable(Mission mission) {

		String missionName = "";
		String missionStatus = "";
		String tripName = "";
		String tripStatus = "";
		int droneID = 0;
		String droneStatus = "";

		missionName = mission.getName();
		missionStatus = Mission.getStatusNameFromValue(mission.getStatus());

		if (mission.getTrips().size() > 0) {

			// If there are other trips to complete
			tripName = mission.getTrips().get(0).getName();
			tripStatus = Trip.getStatusNameFromValue(mission.getTrips().get(0)
					.getStatus());

			if (mission.getTrips().get(0).getDrone() != null) {
				droneID = mission.getTrips().get(0).getDrone().getId();
				droneStatus = Drone.getStatusNameFromValue(mission.getTrips()
						.get(0).getDrone().getStatus());
			}
		}

		this.monitorPage.updateTableRow(missionName, missionStatus, droneID,
				droneStatus, tripName, tripStatus);

	}

	private void printToMonitorConsole(String s) {
		this.monitorPage.fillConsole(s + '\n');
	}

}
