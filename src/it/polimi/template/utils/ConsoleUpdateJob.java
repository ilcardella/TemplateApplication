package it.polimi.template.utils;

import it.polimi.template.view.MonitorPage;

public class ConsoleUpdateJob implements Runnable {

	String missionName = "";
	int missionStatus = 0;
	String tripName = "";
	int tripStatus = 0;
	int droneID = 0;
	int droneStatus = 0;

	private MonitorPage view;

	public ConsoleUpdateJob(String missionName, int missionStatus,
			String tripName, int tripStatus, int droneID, int droneStatus,
			MonitorPage view) {
		this.missionName = missionName;
		this.missionStatus = missionStatus;
		this.tripName = tripName;
		this.tripStatus = tripStatus;
		this.droneID = droneID;
		this.droneStatus = droneStatus;
		this.view = view;
	}

	@Override
	public void run() {
		this.view.fillConsole("MissionName = " + missionName
				+ ", MissionStatus = " + missionStatus + ", DroneID = "
				+ droneID + ", DroneStatus = " + droneStatus + ", TripName = "
				+ tripName + ", TripStatus = " + tripStatus + '\n');
	}

}
