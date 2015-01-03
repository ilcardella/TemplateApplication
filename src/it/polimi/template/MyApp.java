package it.polimi.template;

import it.polimi.template.model.*;
import it.polimi.template.view.*;

import java.awt.EventQueue;
import java.util.ArrayList;

public class MyApp {

	static ArrayList<Mission> missions = new ArrayList<Mission>();
	static ArrayList<Drone> drones = new ArrayList<Drone>();
	static ArrayList<Item> items = new ArrayList<Item>();

	public static void populateDronesandItems() {

		Drone d1 = new Drone();
		Drone d2 = new Drone();
		Drone d3 = new Drone();
		Drone d4 = new Drone();

		Item i1 = new Item();
		Item i2 = new Item();
		Item i3 = new Item();
		Item i4 = new Item();

		d1.setShapeCategory(2);
		d2.setShapeCategory(3);
		d3.setShapeCategory(1);
		d4.setShapeCategory(4);

		i1.setShapeCategory(1);
		i2.setShapeCategory(2);
		i3.setShapeCategory(3);
		i4.setShapeCategory(4);

		i1.setName("1");
		i2.setName("2");
		i3.setName("3");
		i4.setName("4");

		drones.add(d1);
		drones.add(d2);
		drones.add(d3);
		drones.add(d4);
		items.add(i1);
		items.add(i2);
		items.add(i3);
		items.add(i4);

	}

	public static void main(String[] args) {

		populateDronesandItems();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MissionsPage mp = new MissionsPage(missions, drones, items);
				mp.setVisible(true);
			}
		});

	}

}
