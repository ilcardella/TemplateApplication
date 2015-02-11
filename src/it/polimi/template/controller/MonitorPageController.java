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

//	private List<Mission> missions;
//	private List<MissionWorker> threadList;

	public MonitorPageController(MonitorPage monitorPage,
			MissionsPage missionPage, List<Mission> missions) {
		
		this.monitorPage = monitorPage;
		this.missionPage = missionPage;
//		this.missions = missions;
		this.engine = new Engine(this);
		this.engine.setMissions(missions);

		this.monitorPage.setStartButtonListener(new StartButtonListener());
		this.monitorPage.setStopButtonListener(new StopButtonListener());
		this.monitorPage.setBackButtonListener(new BackButtonListener());
	}

	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			launchExecution();
			engine.startMissionsExecution();

		}
	}

	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (Iterator<Mission> iter = engine.getMissions().listIterator(); iter
					.hasNext();) {
				Mission m = iter.next();
				if (m.getStatus() == Mission.COMPLETED) {
					missionPage.removeMissionFromList(m.getName());
					iter.remove();
				}
			}
			monitorPage.dispose();
			monitorPage.setVisible(false);
		}
	}

//	protected void launchExecution() {
//		threadList = new ArrayList<MissionWorker>();
//		for (int i = 0; i < missions.size(); i++) {
//			MissionWorker worker = new MissionWorker(missions.get(i), this);
//			threadList.add(worker);
//
//			worker.execute();
//
//		}
//	}

	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// clear the view
			// monitorPage.clearTable();
			// monitorPage.clearConsole();
			// kill all the threads to stop the execution of the missions

			// TODO Bisogna usare un Thread per ogni drone ed eseguire l'azione.
			for (MissionWorker t : engine.getMissionsThreadList()) {
				if (!t.isCancelled())
					// TODO non blocca i thread, risolvere
					t.cancel(true);
			}
			// prompt user what to do
			String selection = monitorPage.showStopOptions();
			// TODO al posto di iterare tutti i droni, iterare le missioni
			// e prendere i droni associati al primo trip e usare quelli
			// così è possibile mettere anche il log all'interno dello switch
			// case
			// for each drone that is flyng
			for (Drone d : DronesManager.getDrones()) {
				// if the drone is busy it means it is not at home location
				if (d.getStatus() == Drone.BUSY) {
					// depending on the user selection
					switch (selection) {
					case "RTL":
						// do RTL
						d.setStatus(Drone.BUSY);
						d.flyTo(Drone.HOME_LOCATION);
						d.setStatus(Drone.FREE);
						break;
					case "Land":
						// do Land
						d.setStatus(Drone.BUSY);
						d.land();
						d.setStatus(Drone.FREE);
						break;
					default:
						// do RTL as default
						d.setStatus(Drone.BUSY);
						d.flyTo(Drone.HOME_LOCATION);
						d.setStatus(Drone.FREE);
						break;
					}
				}
			}
			// log the status of all not-completed missions after the stop
			for (Mission m : engine.getMissions()) {
				// if the mission is not completed yet
				if (!(m.getStatus() == Mission.COMPLETED)) {
					// set it to stanby because it could be completed in future
					m.setStatus(Mission.STANDBY);
					log(m, "Mission " + m.getName() + " STOPPED");
				}
			}

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
