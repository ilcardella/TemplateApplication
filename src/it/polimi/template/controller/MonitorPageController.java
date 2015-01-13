package it.polimi.template.controller;

import it.polimi.template.controller.thread.MyWorker;
import it.polimi.template.model.Drone;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.utils.Logger;
import it.polimi.template.view.MonitorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MonitorPageController {

	private MonitorPage monitorPage;
	private List<Mission> missions;
	private Logger logger;

	public MonitorPageController(MonitorPage monitorPage, List<Mission> missions) {
		this.monitorPage = monitorPage;
		this.missions = missions;

		this.monitorPage.setStartButtonListener(new StartButtonListener());
		this.monitorPage.setStopButtonListener(new StopButtonListener());
		
		this.logger = new Logger(monitorPage);

	}

	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			launchExecution();
		}
	}

	protected void launchExecution() {
		for (int i = 0; i < missions.size(); i++) {
			MyWorker worker = new MyWorker(missions.get(i));
			worker.execute();
		}
	}

	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Stop all the drones
			monitorPage.clearTable();
		}
	}

}
