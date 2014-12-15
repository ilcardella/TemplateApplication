package it.polimi.template.controller;

import it.polimi.template.view.*;

import it.polimi.template.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class UserApp {
	
	/*static ArrayList<Mission> missions = new ArrayList<Mission>();
	static ArrayList<Trip> trips = new ArrayList<Trip>();
	ArrayList<Drone> drones = new ArrayList<Drone>();

	public static void main(String args[]) {

		InitialPage ip = new InitialPage();
		
		boolean quit = false;
		boolean quit1 = false;
		boolean quit2 = false;

		ip.printMenu();

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		if (choice == 2)
			return;

		MissionsPage mp = new MissionsPage();

		
		do {
			mp.printMenu();

			int choice1 = sc.nextInt();

			if (choice1 == 1) {
				mp.NameMission();
				String name = sc.next();
				
				Mission m = new Mission();

			//	m.setName(name);
				missions.add(m);
			}
			if (choice1 == 2) {
				mp.deleteMission();
				String name = sc.next();
				for (Mission m : missions) {
			//		if (m.getName() == name)
				//		missions.remove(m);

				}
			}

			if (choice1 == 3) {

				TripsPage tp = new TripsPage();
				do {
					tp.printMenu();
					int choice2 = sc.nextInt();

					if (choice2 == 1) {
						tp.setMission();
						String mission = sc.next();
						tp.setTripStart();
						String start = sc.next();
						Trip t = new Trip();
						t.setSourceLocation(start);
						tp.setTripDest();
						String dest = sc.next();
						t.setTargetLocation(dest);
						tp.setDel();
						int del = sc.nextInt();
						t.setDelay(del);
						tp.setPrior();
						int prior = sc.nextInt();
						t.setPriority(prior);
						tp.setN();
						String n = sc.next();
					//	t.setName(n);

						trips.add(t);
						for (Mission m : missions) {
						//	if (m.getName() == mission)
							//	m.setTrips(trips);

						}

					}
					if (choice2 == 2) {
						tp.deleteTrip();
						String n = sc.next();
						for (Trip t : trips) {
						//	if (t.getName() == n)
							//	trips.remove(t);

						}

					}

					if (choice2 == 3) {
						quit1 = true;
					}
					if (choice2 == 4) {
						trips.clear();
					}
					if (choice2 >= 5 || choice2 <= 0) {
						ErrorPage ep = new ErrorPage();
						ep.menuError();
					}
				} while (!quit1);
			}
			if (choice1 == 4) {

				DronesPage dp = new DronesPage();
				do {
					dp.printMenu();
					int choice3 = sc.nextInt();
					if (choice3 == 1 || choice3 == 2) {
						quit2 = true;
					}
					if (choice3 == 3) {

					}
					if (choice3 >= 4 || choice3 <= 0) {
						ErrorPage ep = new ErrorPage();
						ep.menuError();
					}
				} while (!quit2);
			}
			if (choice1 == 5) {

				missions.clear();

			}
			if (choice1 == 6) {

				quit = true;

			}
			if (choice1 >= 7 || choice1 <= 0) {

				ErrorPage ep = new ErrorPage();
				ep.menuError();

			}

		} while (!quit);
	}*/
	
	



}
