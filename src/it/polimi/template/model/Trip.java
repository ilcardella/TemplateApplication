package it.polimi.template.model;

public class Trip {

	public static final int WAITING = 1;
	public static final int COMPLETED = 2;
	public static final int FAILED = 3;
	public static final int EXECUTING = 4;
	public static final int DELAYED = 5;
	public static final int EXPIRED = 6;

	private String name;
	private Action action;
	private String sourceLocation; // Pattern: "x/y"
	private String targetLocation; // Pattern: "x/y"
	private int delay;
	private int priority;
	private int status;
	private Drone drone;
	private Item item;
	private long startTime;
	private Mission parentMission;


	private int faith = 1;
	// private boolean used = false;

	public Trip() {
		this.delay = 0;
		this.priority = 100;
		this.status = WAITING;
	}

	public synchronized String getSourceLocation() {
		return sourceLocation;
	}

	public synchronized void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public synchronized String getTargetLocation() {
		return targetLocation;
	}

	public synchronized void setTargetLocation(String targetLocation) {
		this.targetLocation = targetLocation;
	}

	public synchronized int getDelay() {
		return delay;
	}

	public synchronized void setDelay(int delay) {
		this.delay = delay;
	}

	public synchronized int getPriority() {
		return priority;
	}

	public synchronized void setPriority(int priority) {
		this.priority = priority;
	}

	public synchronized Drone getDrone() {
		return drone;
	}

	public synchronized void setDrone(Drone drone) {
		this.drone = drone;
	}

	public synchronized Item getItem() {
		return item;
	}

	public synchronized void setItem(Item item) {
		this.item = item;
	}

	public synchronized long getStartTime() {
		return startTime;
	}

	public synchronized void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public synchronized int getStatus() {
		return status;
	}

	public synchronized void setStatus(int status) {
		this.status = status;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized Mission getParentMission() {
		return parentMission;
	}

	public synchronized void setParentMission(Mission mission) {
		this.parentMission = mission;
	}

	// public boolean getUsed() {
	// return used;
	// }
	//
	// public void setUsed(boolean used) {
	// this.used = used;
	// }

	public synchronized Action getAction() {
		return action;
	}

	public synchronized void setAction(Action action) {
		this.action = action;
	}

	public static String getStatusNameFromValue(int value) {

		switch (value) {
		case WAITING:
			return "WAITING";
		case EXECUTING:
			return "EXECUTING";
		case COMPLETED:
			return "COMPLETED";
		case FAILED:
			return "FAILED";
		case DELAYED:
			return "DELAYED";
		case EXPIRED:
			return "EXPIRED";
		default:
			return "Unknown";
		}
	}

	public boolean executeTrip() {
		setStartTime(System.currentTimeMillis());
		
		Object actionOutcome = this.drone.flyToAndDoAction(this.targetLocation,
				this.action);
		if (actionOutcome != null) {
			this.parentMission.getEvaluator().writeActionOutcome(actionOutcome,
					this);
			
			if(faith > 0){
				faith--;
				return false;
			}
			
			return true;
		}
		return false;
	}

	// two trips are equals if they have the same name
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof Trip)) {
			return false;
		} else {
			Trip t = (Trip) obj;
			if (t.getName().equals(this.getName())) {
				if(t.getAction().equals(this.getAction())){
					if(t.getSourceLocation().equals(this.getSourceLocation())){
						if(t.getTargetLocation().equals(this.getTargetLocation())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
