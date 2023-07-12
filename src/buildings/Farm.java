package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public class Farm extends EconomicBuilding {

	public Farm() {
		super(1000, 500);

	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
        // Checks for exceptions and updates level using super class implementation
        super.upgrade(); 
        this.setUpgradeCost(700);
    }

	public int harvest() {
		int currentLevel= this.getLevel();
		int gold=0;
		// Set the amount of gold that this Farm produces based on its level
		switch(currentLevel) {
		case 1: gold=500; break;
		case 2: gold=700; break;
		case 3: gold=1000; break;
		}
		return gold;
	}

}
