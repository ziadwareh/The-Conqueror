package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Market extends EconomicBuilding {

	public Market() {
		super(1500, 700);
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
        // Checks for exceptions and updates level using super class implementation
        super.upgrade();
        this.setUpgradeCost(1000);
    }

	public int harvest() {
		int currentLevel= this.getLevel();
		int gold=0;
		// Set the amount of gold that this Market produces based on its level
		switch(currentLevel) {
		case 1: gold=1000; break;
		case 2: gold=1500; break;
		case 3: gold=2000; break;
		}
		return gold;
	}

}
