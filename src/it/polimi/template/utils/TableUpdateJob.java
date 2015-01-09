package it.polimi.template.utils;

import it.polimi.template.view.MonitorPage;

public class TableUpdateJob implements Runnable {

	String missionName = "";
	int missionStatus = 0;
	String tripName = "";
	int tripStatus = 0;
	int droneID = 0;
	int droneStatus = 0;
	
	private MonitorPage view;

	public TableUpdateJob(String missionName, int missionStatus,
			String tripName, int tripStatus, int droneID, int droneStatus, MonitorPage view) {
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
		view.updateTableRow(missionName, missionStatus,
				droneID, droneStatus, tripName, tripStatus);
	}

}
