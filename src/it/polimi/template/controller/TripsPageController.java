package it.polimi.template.controller;

import it.polimi.template.model.Action;

import it.polimi.template.model.Item;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.utils.ItemsManager;
import it.polimi.template.view.TripsPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TripsPageController {

	private TripsPage tripsPage;
	private Mission mission;
	private char tripCounter = 65;

	public TripsPageController(TripsPage tripsPage, Mission mission) {
		this.tripsPage = tripsPage;
		this.mission = mission;

		this.tripsPage.setOkButtonListener(new TripsPageOkButtonListener());
		this.tripsPage
				.setDeleteAllButtonListener(new TripsPageDeleteAllButtonListener());
		this.tripsPage.setExportDoneActionListener(new ExportDoneListener());

	}

	class TripsPageOkButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO simulo l'aggiunta di 5 trip alla missione
	
			tripsPage.killWindow();
		}
	}

	class TripsPageDeleteAllButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			mission.getTrips().clear();

			// update the view
			tripsPage.deleteAllTrips();
		}

	}
	
	public class ExportDoneListener {

		public void actionPerformed() {
		manageDragAndDrop(tripsPage.getAction());
		}
		
	}

	public void manageDragAndDrop(String action) {
		// TODO Implementare anche le altre Action
		
		// if drop event is ok, create the Trip and set the name
		Trip trip = new Trip();
		trip.setName(mission.getName() + " - " + tripCounter);
		tripCounter++;

		if (action.equals(Action.PICK_ITEM.toString())
				|| action.equals(Action.RELEASE_ITEM.toString())) {

			if (action.equals(Action.PICK_ITEM.toString())) 
				trip.setAction(Action.PICK_ITEM);
			else
				trip.setAction(Action.RELEASE_ITEM);
			
			// create a string list with the name of the items
			List<String> itemsNames = new ArrayList<String>();
			for (Item i : ItemsManager.getItems())
				itemsNames.add(i.getName());

			// get the items from the user input
			String iName = tripsPage.showItemsPanel(itemsNames);

			// set the item to the trip
			for (Item i : ItemsManager.getItems())
				if (i.getName().equals(iName))
					trip.setItem(i);
		}
			// set the priority to the trip
			int priority = tripsPage.showPriorityPanel();
			trip.setPriority(priority);

			// set the delay
			int delay = tripsPage.showDelayPanel();
			trip.setDelay(delay);

			// add to the trip list of the mission
			mission.getTrips().add(trip);
			tripsPage.fillTripList(trip.getName());
		

	}
	

	
	

}
