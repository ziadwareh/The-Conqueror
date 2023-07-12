package engine;

import java.util.ArrayList;

import buildings.*;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;

public class Player{
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;

	public Player(String name) {
		this.name = name;
		this.controlledCities = new ArrayList<City>();
		this.controlledArmies = new ArrayList<Army>();
	}

	// Helper for recruitUnit method
	public int getCity(String cityName) {
		for (int i = 0; i < this.controlledCities.size(); i++)
			if (this.controlledCities.get(i).getName().equals(cityName))
				return i;
		return -1;
	}

	// helper for recruit unit method
	public int getBuilding(String type, City city) {
		int i = 0;
		City c = city;
		if (type.equals("Cavalry")) {
			for (; i < c.getMilitaryBuildings().size(); i++)
				if (c.getMilitaryBuildings().get(i) instanceof Stable)
					return i;
		}

		else if (type.equals("Infantry")) {
			for (; i < c.getMilitaryBuildings().size(); i++)
				if (c.getMilitaryBuildings().get(i) instanceof Barracks)
					return i;
		}

		else if (type.equals("Archer")) {
			for (; i < c.getMilitaryBuildings().size(); i++)
				if (c.getMilitaryBuildings().get(i) instanceof ArcheryRange)
					return i;
		}

		return -1;
	}

	// Helper for initiate Army method
	public Unit getUnit(City c, Unit u) {
		int i = 0;
		for (; i < c.getDefendingArmy().getUnits().size(); i++)
			if (c.getDefendingArmy().getUnits().get(i).equals(u))
				break;
		return c.getDefendingArmy().getUnits().get(i);
	}

	public void initiateArmy(City city, Unit unit) {
		Army a = new Army(city.getName());
		a.getUnits().add(getUnit(city, unit));
		city.getDefendingArmy().getUnits().remove(unit);
		unit.setParentArmy(a);
		this.controlledArmies.add(a);
	}

	// Given a player's city name and type of unit this method recruits that unit
	// into the player's chosen city from his controlled cities
	public void recruitUnit(String type, String cityName) throws BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException {
		// use helper to get an instance of the corresponding city
		int z = getCity(cityName);
		if(z== -1)
			return;
		City chosenCity = this.controlledCities.get(z);
		ArrayList<MilitaryBuilding> buildings = chosenCity.getMilitaryBuildings();
		int i;
		// loop over the available military buildings and check the type of the unit
		// along with the current building type until we find two matching types
		for (i = 0; i < buildings.size(); i++) {
			if (type.equalsIgnoreCase("Infantry") && buildings.get(i) instanceof Barracks)
				break;
			if (type.equalsIgnoreCase("Archer") && buildings.get(i) instanceof ArcheryRange)
				break;
			if (type.equalsIgnoreCase("Cavalry") && buildings.get(i) instanceof Stable)
				break;
		}
		// create a instance of the needed building to operate on
		MilitaryBuilding chosenBuilding = buildings.get(i);
		// checks if player can afford the recruitment and throws exceptions if not
		if (this.treasury < (double) chosenBuilding.getRecruitmentCost())
			throw new NotEnoughGoldException("You cannot afford to recruit this unit!!\n Unit type: " + type
					+ " Gold needed: " + chosenBuilding.getRecruitmentCost() + " Gold available: " + this.treasury);
		// call recruit method in try catch block, check for exceptions if not then
		// deduct gold from treasury and update both parent and defending army
		Unit recruitedUnit;
		try {
			recruitedUnit = chosenBuilding.recruit();
		} catch (BuildingInCoolDownException e) {
			throw e;
		} catch (MaxRecruitedException e) {
			throw e;
		}
		this.treasury -= chosenBuilding.getRecruitmentCost();
		Army defendingArmy = chosenCity.getDefendingArmy();
		recruitedUnit.setParentArmy(defendingArmy);
		defendingArmy.getUnits().add(recruitedUnit);
		// else should throw MaxCapacityException but its not declared in the signiture
	}

//	public boolean cannotBuild(String type, ArrayList<EconomicBuilding> economicalBuildings, ArrayList<MilitaryBuilding> militaryBuildings) {
//		
//	}
	public double getCost(String type) { // helper for build
		if (type.equalsIgnoreCase("Farm"))
			return 1000;
		if (type.equalsIgnoreCase("Market") || type.equalsIgnoreCase("ArcheryRange"))
			return 1500;
		if (type.equalsIgnoreCase("Barracks"))
			return 2000;
		else
			return 2500;
	}

	public char getType(Double cost, ArrayList<EconomicBuilding> economicalBuildings,
			ArrayList<MilitaryBuilding> militaryBuildings, String type) {// helper for build
//	'f'=Farm
//	'm'=Market
//	'a'=ArcheryRange
//	'b'=Barracks
//	's'=Stable
//	' '=there exists a similar building in the city therefore we cannot build
		char result;
		if (cost == 1000) { // therefore this is for sure a farm
			result = 'f';
			for (int i = 0; i < economicalBuildings.size(); i++)
				if (economicalBuildings.get(i) instanceof Farm)
					result = ' ';
		} else if (cost == 1500) {// then this is either a Marker or and ArcheryRange.
			if (type.equalsIgnoreCase("Market")) {
				result = 'm';
				for (int i = 0; i < economicalBuildings.size(); i++)
					if (economicalBuildings.get(i) instanceof Market)
						result = ' ';
			} else {// if not Market, then its ArcheryRange
				result = 'a';
				for (int i = 0; i < militaryBuildings.size(); i++)
					if (militaryBuildings.get(i) instanceof ArcheryRange)
						result = ' ';
			}
		} else if (cost == 2000) {// then it is certainly a Barrack
			result = 'b';
			for (int i = 0; i < militaryBuildings.size(); i++)
				if (militaryBuildings.get(i) instanceof Barracks)
					result = ' ';
		} else {// therefore it is a stable
			result = 's';
			for (int i = 0; i < militaryBuildings.size(); i++)
				if (militaryBuildings.get(i) instanceof Stable)
					result = ' ';
		}
		return result;
	}

	public void build(String type, String cityName) throws NotEnoughGoldException {
		City c = null;
		for (int i = 0; i < this.controlledCities.size(); i++) {
			if (this.controlledCities.get(i).getName().equalsIgnoreCase(cityName)) {
				c = this.controlledCities.get(i);
				break;
			}
		}
		double cost = getCost(type);
		if (this.getTreasury() < cost)
			throw new NotEnoughGoldException("not enough Diniro Mi Amigo");
		
		char building_type = getType(cost, c.getEconomicalBuildings(), c.getMilitaryBuildings(), type);
		boolean built = true;
		switch (building_type) {
		case 'f':
			c.getEconomicalBuildings().add(new Farm());
			break;
		case 'm':
			c.getEconomicalBuildings().add(new Market());
			break;
		case 'a':
			c.getMilitaryBuildings().add(new ArcheryRange());
			break;
		case 'b':
			c.getMilitaryBuildings().add(new Barracks());
			break;
		case 's':
			c.getMilitaryBuildings().add(new Stable());
			break;
		default:
			built = false;
			break;
		}
		if (built)
			this.setTreasury(this.getTreasury() - cost);
	}

	public void upgradeBuilding(Building b)
			throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
		NotEnoughGoldException n = new NotEnoughGoldException("No Enough Gold!");
		if (this.treasury < b.getUpgradeCost())
			throw n;
		int oldUpgradeCost = b.getUpgradeCost();
		try {
			b.upgrade();
		} catch (MaxLevelException e) {
			throw e;
		} catch (BuildingInCoolDownException e) {
			throw e;
		}
		this.treasury -= oldUpgradeCost;
	}

	public void laySiege(Army army, City city) throws TargetNotReachedException, FriendlyCityException {
		TargetNotReachedException e = new TargetNotReachedException("Target not reached!");
		FriendlyCityException f = new FriendlyCityException("Friendly city; you own it!");
		if (army.getDistancetoTarget() != 0 && !army.getCurrentLocation().equals(city.getName()))
			throw e;
		else if (this.controlledCities.contains(city))
			throw f;
		else {
			army.setCurrentStatus(Status.BESIEGING);
			city.setUnderSiege(true);
			city.setTurnsUnderSiege(0);
		}
	}

	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}

}
