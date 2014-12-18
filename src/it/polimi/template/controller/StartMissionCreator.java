package it.polimi.template.controller;

import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.model.editor.MissionCreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartMissionCreator {

	MissionCreator mc = new MissionCreator();

	Mission targetMission = new Mission();
	ArrayList<Trip> targetTrips = new ArrayList<Trip>();

	public void run(String name, ArrayList<Mission> missions,
			ArrayList<Trip> trips) {

		for (Mission m : missions)
			if (m.getName() == name)
				targetMission = m;

		for (Trip t : trips)
			if (t.getMission() == targetMission)
				targetTrips.add(t);

		mc.run(targetMission, targetTrips);

	}

}
