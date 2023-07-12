package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;
import exceptions.*;
import units.Status;
import buildings.*;

public class Game{
	private Player player;
	private ArrayList<City> availableCities;
	private ArrayList<Distance> distances;
	private final int maxTurnCount = 50;
	private int currentTurnCount;
	private GameListener gameListener;
	private boolean finished_Battle;

	public Game(String playerName, String playerCity) throws IOException {

		player = new Player(playerName);
		availableCities = new ArrayList<City>();
		distances = new ArrayList<Distance>();
		currentTurnCount = 1;
		loadCitiesAndDistances();
		for (City c : availableCities) {
			if (c.getName().equalsIgnoreCase(playerCity))

				player.getControlledCities().add(c);

			else
				loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");

		}
		this.finished_Battle=true;
	}

	private void loadCitiesAndDistances() throws IOException {

		BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
		String currentLine = br.readLine();
		ArrayList<String> names = new ArrayList<String>();

		while (currentLine != null) {

			String[] content = currentLine.split(",");
			if (!names.contains(content[0])) {
				availableCities.add(new City(content[0]));
				names.add(content[0]);
			} else if (!names.contains(content[1])) {
				availableCities.add(new City(content[1]));
				names.add(content[1]);
			}
			distances.add(new Distance(content[0], content[1], Integer.parseInt(content[2])));
			currentLine = br.readLine();

		}
		br.close();
	}

	public void loadArmy(String cityName, String path) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = br.readLine();
		Army resultArmy = new Army(cityName);
		while (currentLine != null) {
			String[] content = currentLine.split(",");
			String unitType = content[0].toLowerCase();
			int unitLevel = Integer.parseInt(content[1]);
			Unit u = null;
			if (unitType.equals("archer")) {

				if (unitLevel == 1)
					u = (new Archer(1, 60, 0.4, 0.5, 0.6));

				else if (unitLevel == 2)
					u = (new Archer(2, 60, 0.4, 0.5, 0.6));
				else
					u = (new Archer(3, 70, 0.5, 0.6, 0.7));
			} else if (unitType.equals("infantry")) {
				if (unitLevel == 1)
					u = (new Infantry(1, 50, 0.5, 0.6, 0.7));

				else if (unitLevel == 2)
					u = (new Infantry(2, 50, 0.5, 0.6, 0.7));
				else
					u = (new Infantry(3, 60, 0.6, 0.7, 0.8));
			} else if (unitType.equals("cavalry")) {
				if (unitLevel == 1)
					u = (new Cavalry(1, 40, 0.6, 0.7, 0.75));

				else if (unitLevel == 2)
					u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
				else
					u = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
			}
			u.setParentArmy(resultArmy);
			resultArmy.getUnits().add(u);
			currentLine = br.readLine();
		}
		br.close();
		for (City c : availableCities) {
			if (c.getName().toLowerCase().equals(cityName.toLowerCase()))
				c.setDefendingArmy(resultArmy);
		}
	}

	public boolean isGameOver() {
		if (this.currentTurnCount == 51)
			return true;
		else if (this.getPlayer().getControlledCities().containsAll(availableCities))
			return true;
		return false;
	}

	public ArrayList<City> getAvailableCities() {
		return availableCities;
	}

	public ArrayList<Distance> getDistances() {
		return distances;
	}

	public int getCurrentTurnCount() {
		return currentTurnCount;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMaxTurnCount() {
		return maxTurnCount;
	}
	
	public void setGameListener(GameListener gameListener) {
		this.gameListener = gameListener;
	}

	public void setCurrentTurnCount(int currentTurnCount) {
		this.currentTurnCount = currentTurnCount;
	}

	//
	public void targetCity(Army army, String targetName) {
		// An army's current location can be its own city or the one it is attacking
		String currentCity = army.getCurrentLocation();
		// Updates the old target city's attributes if the army was besieging it and is
		// retreating and going to another city
		if (army.getCurrentStatus() == Status.BESIEGING) {
			for (int i = 0; i < availableCities.size(); i++) {
				if (availableCities.get(i).getName().equals(currentCity)) {
					availableCities.get(i).setUnderSiege(false);
					availableCities.get(i).setTurnsUnderSiege(-1);
					break;
				}
			}
		}
		// gets the appropriate Distance object then updates the army's attributes
		int i;
		for (i = 0; i < distances.size(); i++) {
			Distance d = distances.get(i);
			if (d.getFrom().equals(currentCity) && d.getTo().equals(targetName) || d.getFrom().equals(targetName) && d.getTo().equals(currentCity))
				break;
		}
		army.setTarget(targetName);
		army.setCurrentStatus(Status.MARCHING);
		army.setDistancetoTarget(distances.get(i).getDistance());
	}

	// This method is called after the player has defeated a city to take control of
	// and
	// update that defeated city
	public void occupy(Army a, String cityName) {
		City defeatedCity = null;
		// Loop that gets the defeated city from the game's avaialable cities
		for (int i = 0; i < availableCities.size(); i++) {
			if (availableCities.get(i).getName().equalsIgnoreCase(cityName)) {
				defeatedCity = availableCities.get(i);
				break;
			}
		}
		defeatedCity.setDefendingArmy(a);
		defeatedCity.setTurnsUnderSiege(-1);
		defeatedCity.setUnderSiege(false);
		player.getControlledCities().add(defeatedCity);
		this.finished_Battle=true;
	}

	public void autoResolve(Army attacker, Army defender) throws FriendlyFireException {
		for (int i = 0; defender.getUnits().size() != 0 && attacker.getUnits().size() != 0; i++) {
			int attacker_Unit_index = (int) ((Math.random() * attacker.getUnits().size()));
			int defender_Unit_index = (int) ((Math.random() * defender.getUnits().size()));
			Unit attacker_Unit = attacker.getUnits().get(attacker_Unit_index);
			attacker_Unit.setDoNothing(true);
			Unit defender_Unit = defender.getUnits().get(defender_Unit_index);
			defender_Unit.setDoNothing(true);
			if (i % 2 == 0) {
				attacker_Unit.attack(defender_Unit);
			} else {
				defender_Unit.attack(attacker_Unit);
			}
		}
		if (defender.getUnits().size() == 0) {
			gameListener.onBattleResult(true);
			//occupy(attacker, defender.getCurrentLocation());
		}
		else 
			gameListener.onBattleResult(false);
		
		this.player.getControlledArmies().remove(attacker);
	}
	public void endTurn() {

		this.currentTurnCount++;

		for (int i = 0; i < this.getPlayer().getControlledCities().size(); i++) {

			for (int j = 0; j < this.getPlayer().getControlledCities().get(i).getMilitaryBuildings().size(); j++) {
				this.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).setCoolDown(false);
				this.getPlayer().getControlledCities().get(i).getMilitaryBuildings().get(j).setCurrentRecruit(0);
			}

			for (int j = 0; j < this.getPlayer().getControlledCities().get(i).getEconomicalBuildings().size(); j++) {
				this.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j).setCoolDown(false);
				double h = this.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j).harvest();
				if (this.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Farm)
					this.getPlayer().setFood(this.getPlayer().getFood() + h);
				if (this.getPlayer().getControlledCities().get(i).getEconomicalBuildings().get(j) instanceof Market)
					this.getPlayer().setTreasury(this.getPlayer().getTreasury() + h);
			}
		}

		for (int i = 0; i < this.getPlayer().getControlledArmies().size(); i++) {

			double z = this.getPlayer().getControlledArmies().get(i).foodNeeded();
			if(this.getPlayer().getFood()>z)
				this.getPlayer().setFood(this.getPlayer().getFood() - z);
			else
				this.getPlayer().setFood(0);
			
			if(!this.getPlayer().getControlledArmies().get(i).getTarget().equals("")) {
				if (this.getPlayer().getControlledArmies().get(i).getDistancetoTarget() != 0) {
					int d = this.getPlayer().getControlledArmies().get(i).getDistancetoTarget() - 1;
					this.getPlayer().getControlledArmies().get(i).setDistancetoTarget(d);
				}

				 if (this.getPlayer().getControlledArmies().get(i).getDistancetoTarget() == 0) {
					String a = this.getPlayer().getControlledArmies().get(i).getTarget();
					this.getPlayer().getControlledArmies().get(i).setCurrentLocation(a);
					this.getPlayer().getControlledArmies().get(i).setCurrentStatus(Status.IDLE);
					this.getPlayer().getControlledArmies().get(i).setTarget("");
					//
					this.getPlayer().getControlledArmies().get(i).setTragetReached(true);
					this.getPlayer().getControlledArmies().get(i).setWasMarching(true);
				}
			}

			if (this.getPlayer().getFood() <= 0) {
				for (int j = 0; j < this.getPlayer().getControlledArmies().get(i).getUnits().size(); j++) {
					int k = (int) (this.getPlayer().getControlledArmies().get(i).getUnits().get(j)
							.getCurrentSoldierCount() * 0.9);
					this.getPlayer().getControlledArmies().get(i).getUnits().get(j).setCurrentSoldierCount(k);
				}
			}
		}

		for (int j = 0; j < this.availableCities.size(); j++) {
			if (this.availableCities.get(j).isUnderSiege()) {
				int i = this.availableCities.get(j).getTurnsUnderSiege() + 1;
				this.availableCities.get(j).setTurnsUnderSiege(i);
				for (int k = 0; k < this.availableCities.get(j).getDefendingArmy().getUnits().size(); k++) {
					int h = (int) (this.availableCities.get(j).getDefendingArmy().getUnits().get(k)
							.getCurrentSoldierCount() * 0.9);
					this.availableCities.get(j).getDefendingArmy().getUnits().get(k).setCurrentSoldierCount(h);
				}
			}
			else
				this.availableCities.get(j).setTurnsUnderSiege(0);
		}
	}
	public boolean isFinished_Battle() {
		return finished_Battle;
	}

	public void setFinished_Battle(boolean finished_Battle) {
		this.finished_Battle = finished_Battle;
	}
}