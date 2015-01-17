package it.polimi.template.controller;

import it.polimi.template.controller.thread.MyWorker;
import it.polimi.template.model.Drone;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.utils.DronesManager;
import it.polimi.template.view.MonitorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

public class MonitorPageController {

	private MonitorPage monitorPage;
	private List<Mission> missions;

	public MonitorPageController(MonitorPage monitorPage, List<Mission> missions) {
		this.monitorPage = monitorPage;
		this.missions = missions;

		this.monitorPage.setStartButtonListener(new StartButtonListener());
		this.monitorPage.setStopButtonListener(new StopButtonListener());
	}

	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			launchExecution();
		}
	}

	protected void launchExecution() {
		for (int i = 0; i < missions.size(); i++) {
			MyWorker worker = new MyWorker(missions.get(i), this);
			worker.execute();
		}
	}

	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// clear the view
			monitorPage.clearTable();
			monitorPage.clearConsole();
			// prompt user what to do
			String selection = monitorPage.showStopOptions();
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
			for(Mission m: missions){
				m.setStatus(Mission.STANDBY);
				log(m, "Mission "+m.getName()+" STOPPED");
			}
		}
	}

	public void log(Mission m, String s) {
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
