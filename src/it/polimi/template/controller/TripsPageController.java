package it.polimi.template.controller;

import it.polimi.template.model.Item;
import it.polimi.template.model.Mission;
import it.polimi.template.view.TripsPage;

import java.util.List;

public class TripsPageController {
	
	private TripsPage tripsPage;
	private List<Item> items;
	private String missionName;

	public TripsPageController(TripsPage tripsPage, List<Item> items,
			String missionName) {
		this.tripsPage = tripsPage;
		this.items = items;
		this.missionName = missionName;
	}

}
