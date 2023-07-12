package units;

import engine.UnitListener;
import exceptions.FriendlyFireException;

public abstract class Unit {
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
	private Army parentArmy;
	private UnitListener unitListener;
	private boolean doNothing;

	public Unit(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierConunt;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		this.currentSoldierCount = maxSoldierCount;
		this.parentArmy = null;
		this.doNothing = false;

	}

	public void setUnitListener(UnitListener unitListener) {
		this.unitListener = unitListener;
	}

	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}

	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount = currentSoldierCount;
	}

	public int getLevel() {
		return level;
	}

	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}

	public double getIdleUpkeep() {
		return idleUpkeep;
	}

	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}

	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}

	public Army getParentArmy() {
		return parentArmy;
	}

	public void setParentArmy(Army parentArmy) {
		this.parentArmy = parentArmy;
	}
	
	public boolean isDoNothing() {
		return doNothing;
	}

	public void setDoNothing(boolean doNothing) {
		this.doNothing = doNothing;
	}

	public void attack(Unit target) throws FriendlyFireException {
		if (this.parentArmy.getUnits().contains(target))
			throw new FriendlyFireException("Friendly Fire!");

		double factor = getFactor(target); // reduction factor
		int dead = (int) (this.getCurrentSoldierCount() * factor);
		if (dead >= target.getCurrentSoldierCount()) {
			target.setCurrentSoldierCount(0);
			target.getParentArmy().handleAttackedUnit(target);
			if(!this.doNothing)
				unitListener.onDeadUnit(target);
			target.setParentArmy(null);
		}

		else {
			target.setCurrentSoldierCount(target.getCurrentSoldierCount() - dead);
			if(!this.doNothing)
				unitListener.onGetDead(dead, target);
		}
	}

	private double getFactor(Unit target) {
		int attacker_level = this.getLevel();
		double factor = 0;
		if (this instanceof Archer) {
			if (target instanceof Archer) {
				switch (attacker_level) {
				case 1:
					factor = 0.3;
					break;
				case 2:
					factor = 0.4;
					break;
				case 3:
					factor = 0.5;
					break;
				}
			} else if (target instanceof Infantry) {
				switch (attacker_level) {
				case 1:
					factor = 0.2;
					break;
				case 2:
					factor = 0.3;
					break;
				case 3:
					factor = 0.4;
					break;
				}
			} else {
				switch (attacker_level) {
				case 1:
					factor = 0.1;
					break;
				case 2:
					factor = 0.1;
					break;
				case 3:
					factor = 0.2;
					break;
				}
			}
		} else if (this instanceof Infantry) {
			if (target instanceof Archer) {
				switch (attacker_level) {
				case 1:
					factor = 0.3;
					break;
				case 2:
					factor = 0.4;
					break;
				case 3:
					factor = 0.5;
					break;
				}
			} else if (target instanceof Infantry) {
				switch (attacker_level) {
				case 1:
					factor = 0.1;
					break;
				case 2:
					factor = 0.2;
					break;
				case 3:
					factor = 0.3;
					break;
				}
			} else {
				switch (attacker_level) {
				case 1:
					factor = 0.1;
					break;
				case 2:
					factor = 0.2;
					break;
				case 3:
					factor = 0.25;
					break;
				}
			}
		} else {
			if (target instanceof Archer) {
				switch (attacker_level) {
				case 1:
					factor = 0.5;
					break;
				case 2:
					factor = 0.6;
					break;
				case 3:
					factor = 0.7;
					break;
				}
			} else if (target instanceof Infantry) {
				switch (attacker_level) {
				case 1:
					factor = 0.3;
					break;
				case 2:
					factor = 0.4;
					break;
				case 3:
					factor = 0.5;
					break;
				}
			} else {
				switch (attacker_level) {
				case 1:
					factor = 0.2;
					break;
				case 2:
					factor = 0.2;
					break;
				case 3:
					factor = 0.3;
					break;
				}
			}
		}

		return factor;
	}
}