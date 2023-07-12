package units;

import java.util.ArrayList;

import exceptions.MaxCapacityException;

public class Army {
	private Status currentStatus;
	private ArrayList<Unit> units;
	private int distancetoTarget;
	private String target;
	private String currentLocation;
//	@SuppressWarnings("unused")
	private final int maxToHold = 10;
	//
	private boolean tragetReached;
	private boolean wasMarching;

	public Army(String currentLocation) {
		this.currentLocation = currentLocation;
		currentStatus = Status.IDLE;
		units = new ArrayList<Unit>();
		distancetoTarget = -1;
		target = "";
		//
		this.tragetReached=false;
		this.wasMarching=false;
	}

	public ArrayList<Unit> getUnitOfType(String type){
		ArrayList<Unit> result = new ArrayList<Unit>();
		for(int i = 0; i < this.units.size(); i++) {
			if(type.equals("Archer") && units.get(i) instanceof Archer)
				result.add(units.get(i));
			if(type.equals("Cavalry") && units.get(i) instanceof Cavalry)
				result.add(units.get(i));
			if(type.equals("Infantry") && units.get(i) instanceof Infantry)
				result.add(units.get(i));
		}
		return result;
	}
	public void relocateUnit(Unit unit) throws MaxCapacityException {
		if (this.getUnits().size() == this.maxToHold)
			throw new MaxCapacityException("No Room, Hantchiro wala ehh ?");
		unit.getParentArmy().getUnits().remove(unit);
		unit.setParentArmy(this);
		this.units.add(unit);
	}

	public int findUnit(Unit u) { // helper method for handelAttackUnit
		int i = 0;
		for (; i < this.units.size(); i++)
			if (this.units.get(i) == u)
				return i;
		return -1;
	}

	public void handleAttackedUnit(Unit u) {
		int i = findUnit(u);
		if (i == -1) {
			System.out.print("This unit does is already terminated or Never existed");
			return;
		}
		if (u.getCurrentSoldierCount() == 0)
			this.units.remove(i);
		}

	public double foodNeeded() {
		double foodNeeded = 0.0;
		for (int i = 0; i < this.units.size(); i++) {
			if (this.units.get(i) instanceof Archer) {
				Archer a = (Archer) this.units.get(i);
				switch (this.currentStatus) {
				case IDLE:
					foodNeeded += a.getCurrentSoldierCount() * a.getIdleUpkeep();
					break;
				case MARCHING:
					foodNeeded += a.getCurrentSoldierCount() * a.getMarchingUpkeep();
					break;
				case BESIEGING:
					foodNeeded += a.getCurrentSoldierCount() * a.getSiegeUpkeep();
					break;
				}
			}

			else if (this.units.get(i) instanceof Cavalry) {
				Cavalry c = (Cavalry) this.units.get(i);
				switch (this.currentStatus) {
				case IDLE:
					foodNeeded += c.getCurrentSoldierCount() * c.getIdleUpkeep();
					break;
				case MARCHING:
					foodNeeded += c.getCurrentSoldierCount() * c.getMarchingUpkeep();
					break;
				case BESIEGING:
					foodNeeded += c.getCurrentSoldierCount() * c.getSiegeUpkeep();
					break;
				}
			}

			else if (this.units.get(i) instanceof Infantry) {
				Infantry inf = (Infantry) this.units.get(i);
				switch (this.currentStatus) {
				case IDLE:
					foodNeeded += inf.getCurrentSoldierCount() * inf.getIdleUpkeep();
					break;
				case MARCHING:
					foodNeeded += inf.getCurrentSoldierCount() * inf.getMarchingUpkeep();
					break;
				case BESIEGING:
					foodNeeded += inf.getCurrentSoldierCount() * inf.getSiegeUpkeep();
					break;
				}
			}
		}

		return foodNeeded;
	}

	public Status getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public int getDistancetoTarget() {
		return distancetoTarget;
	}

	public void setDistancetoTarget(int distancetoTarget) {
		this.distancetoTarget = distancetoTarget;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public int getMaxToHold() {
		return maxToHold;
	}

	public boolean isTragetReached() {
		return tragetReached;
	}

	public void setTragetReached(boolean tragetReached) {
		this.tragetReached = tragetReached;
	}

	public boolean isWasMarching() {
		return wasMarching;
	}

	public void setWasMarching(boolean wasMarching) {
		this.wasMarching = wasMarching;
	}
	

}
