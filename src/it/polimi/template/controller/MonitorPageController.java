package it.polimi.template.controller;

import it.polimi.template.model.Drone;
import it.polimi.template.model.Item;
import it.polimi.template.model.Mission;

import java.util.List;

public class MonitorPageController {

	private List<Item> items;
	private List<Drone> drones;
	private List<Mission> missions;
	
	public MonitorPageController(List<Item> items, List<Drone> drones,
			List<Mission> missions) {
		this.items = items;
		this.drones = drones;
		this.missions = missions;
	}

}
