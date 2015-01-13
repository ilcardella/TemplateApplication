package it.polimi.template.utils;

import java.util.Observable;
import java.util.Observer;

import it.polimi.template.model.Drone;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.view.MonitorPage;

public class Logger implements Observer{

	private MonitorPage monitorPage;
	
	public Logger(MonitorPage monitorpage){
		this.monitorPage = monitorpage;
	}
	
	public Logger(){}
	
	public void notifyUpdateOfStatus(Mission m, String s){
		updateMonitorTable(m);
		printToMonitorConsole(s);
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
			tripStatus = Trip.getStatusNameFromValue(mission.getTrips().get(0).getStatus());

			if (mission.getTrips().get(0).getDrone() != null) {
				droneID = mission.getTrips().get(0).getDrone().getId();
				droneStatus = Drone.getStatusNameFromValue(mission.getTrips().get(0).getDrone().getStatus());
			}
		}
		

		
		this.monitorPage.updateTableRow(missionName, missionStatus, droneID, droneStatus, tripName, tripStatus);
		
	}
	
	private void printToMonitorConsole(String s){
		this.monitorPage.fillConsole(s);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
