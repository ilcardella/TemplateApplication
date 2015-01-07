package it.polimi.template.controller;

import it.polimi.template.model.Drone;
import it.polimi.template.model.Item;
import it.polimi.template.model.Mission;
import it.polimi.template.view.MonitorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MonitorPageController {

	private MonitorPage monitorPage;
	private List<Item> items;
	private List<Drone> drones;
	private List<Mission> missions;

	public MonitorPageController(MonitorPage monitorPage, List<Item> items,
			List<Drone> drones, List<Mission> missions) {
		this.monitorPage = monitorPage;
		this.items = items;
		this.drones = drones;
		this.missions = missions;

		this.monitorPage.setStartButtonListener(new StartButtonListener());
		this.monitorPage.setStopButtonListener(new StopButtonListener());

	}

	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Inizia tutto
		}
	}

	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Stoppa tutto
		}
	}

}
