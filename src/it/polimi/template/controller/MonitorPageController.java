package it.polimi.template.controller;

import it.polimi.template.model.Drone;
import it.polimi.template.model.Item;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.view.MonitorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

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
			// TODO Start the execution, the behaviour depends on the diagram generation
//			for (Mission m : missions) {
//
//				dpsbl.sortTripsByPriority(m.getTrips());
//				mpob.startDroneAllocator(m, drones);
//				dpsbl.startTripLauncher(m);
//				dpsbl.startTripMonitor(m);
//
//				for (Trip t : m.getTrips()) {
//
//					trips.add(t);
//
//					if (!t.getUsed()) {
//
//						DefaultTableModel model = (DefaultTableModel) table
//								.getModel();
//
//						if (t.getDrone() == null)
//							model.addRow(new Object[] { "", t.getName(),
//									t.getStatus() });
//						else
//							model.addRow(new Object[] {
//									t.getDrone().getId(), t.getName(),
//									t.getStatus() });
//						t.setUsed(true);
//
//					}
//				}
//
//			}
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
