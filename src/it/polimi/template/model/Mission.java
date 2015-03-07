package it.polimi.template.model;

import it.polimi.template.controller.engine.Evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Mission {

	public static final int UNEXECUTED = 1;
	public static final int RUNNING = 2;
	public static final int COMPLETED = 3;
	public static final int FAILED = 4;
	public static final int STANDBY = 5;

	private int id;
	private String name;
	private int status;
	private List<Trip> trips;
	private List<Trip> completedTrips;
	// private boolean used = false;
	private boolean repeat = false;
	private int safeTimer = 480; // 480sec = 8 mins
	private Evaluator evaluator;

	public Mission() {

		this.id = new Random().nextInt(Integer.MAX_VALUE) + 1;
		this.status = UNEXECUTED;
		this.trips = new ArrayList<Trip>();
		this.completedTrips = new ArrayList<Trip>();
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

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public List<Trip> getCompletedTrips() {
		return completedTrips;
	}

	public void setCompletedTrips(List<Trip> trips) {
		this.completedTrips = trips;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public boolean getUsed() {
	// return used;
	// }
	//
	// public void setUsed(boolean used) {
	// this.used = used;
	// }

	public static String getStatusNameFromValue(int value) {

		switch (value) {
		case UNEXECUTED:
			return "UNEXECUTED";
		case RUNNING:
			return "RUNNING";
		case COMPLETED:
			return "COMPLETED";
		case FAILED:
			return "FAILED";
		case STANDBY:
			return "STANDBY";
		default:
			return "Unknown";
		}
	}

	public int getSafeTimer() {
		return this.safeTimer;
	}

	public void setSafeTimer(int t) {
		this.safeTimer = t;
	}

	public boolean isRepeateable() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public Evaluator getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof Mission)) {
			return false;
		} else {
			Mission m = (Mission) obj;
			if (m.getName().equals(this.getName())) {
				if (m.getTrips().size() == this.getTrips().size()) {
					for (int i = 0; i < m.getTrips().size(); i++) {
						if (!m.getTrips().get(i).equals(this.getTrips().get(i))) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}

}
