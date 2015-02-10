package it.polimi.template.model;

import java.util.Observable;
import java.util.Random;

public class Drone {

	public static final int LONG = 1;
	public static final int SHORT = 2;
	public static final int HEAVY = 3;
	public static final int LIGHT = 4;

	public static final int FREE = 5;
	public static final int BUSY = 6;
	public static final int CHARGING = 7;

	public static final String HOME_LOCATION = "0/0"; // coordinates of home
														// location

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getShapeCategory() {
		return shapeCategory;
	}

	public void setShapeCategory(int shapeCategory) {
		this.shapeCategory = shapeCategory;
	}

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public boolean flyToAndDoAction(String target, final Action action) {
		try {
			// fly to the target location
			flyTo(target);
			// do the action
			action.doAction();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean flyTo(String target) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String getStatusNameFromValue(int value) {

		switch (value) {
		case FREE:
			return "FREE";
		case BUSY:
			return "BUSY";
		case CHARGING:
			return "CHARGING";
		default:
			return "Unknown";
		}
	}

	// The drone lands at the current position
	public boolean land() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	// The drone takes off
	public boolean takeOff() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}
}
