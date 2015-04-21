package it.polimi.template.model;

import java.io.IOException;
import java.util.Random;

public class Drone {

	public static final int LONG = 1;
	public static final int SHORT = 2;
	public static final int HEAVY = 3;
	public static final int LIGHT = 4;

	public static final int FREE = 5;
	public static final int BUSY = 6;
	public static final int CHARGING = 7;
	public static final int UNAVAILABLE = 8;

	public static final String HOME_LOCATION = "0/0"; // coordinates of home
														// location
	private static final long FLY_TIME = 1000; // in millis
	private static final long TAKE_OFF_TIME = 1000; // in millis
	private static final long LAND_TIME = 1000; // in millis

	private int id;
	private int status;
	private int shapeCategory;
	private int batteryLevel;
	private String currentLocation;

	public Drone() {
		this.id = new Random().nextInt(Integer.MAX_VALUE) + 1;
		this.status = 5;
		this.currentLocation = "0/0"; // homelocation
	}

	public synchronized int getId() {
		return id;
	}

	public synchronized void setId(int id) {
		this.id = id;
	}

	public synchronized int getStatus() {
		return status;
	}

	public synchronized void setStatus(int status) {
		this.status = status;
	}

	public synchronized int getShapeCategory() {
		return shapeCategory;
	}

	public synchronized void setShapeCategory(int shapeCategory) {
		this.shapeCategory = shapeCategory;
	}

	public synchronized int getBatteryLevel() {
		return batteryLevel;
	}

	public synchronized void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public synchronized Object flyToAndDoAction(String target,
			final Action action) {
		try {
			// fly to the target location
			flyTo(target);
			// do the action
			Object outcome = action.doAction();
			// save the action result in the evaluator
			// eval.writeActionOutcome(outcome);
			return outcome;
		} catch (Exception e) {
			return null;
		}
	}

	public synchronized boolean flyTo(String target) {
		// try {
		// Thread.sleep(FLY_TIME);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		ProcessBuilder pb = new ProcessBuilder("/usr/bin/python2", 
				"/home/alberto/Projects/Demo/crazyflie-clients-python-2014.12.3/examples/trip.py");
		try {
			Process p = pb.start();
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(FLY_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

	public synchronized static String getStatusNameFromValue(int value) {

		switch (value) {
		case FREE:
			return "FREE";
		case BUSY:
			return "BUSY";
		case CHARGING:
			return "CHARGING";
		case UNAVAILABLE:
			return "UNAVAILABLE";
		default:
			return "Unknown";
		}
	}

	// The drone lands at the current position
	public synchronized boolean land() {
		try {
			Thread.sleep(LAND_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	// The drone takes off
	public synchronized boolean takeOff() {
		try {
			Thread.sleep(TAKE_OFF_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public synchronized String getCurrentLocation() {
		return currentLocation;
	}
}
