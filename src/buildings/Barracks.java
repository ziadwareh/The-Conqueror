package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Infantry;
import units.Unit;

public class Barracks extends MilitaryBuilding {

	public Barracks() {
		super(2000, 1000, 500);

	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        // Checks for exceptions and updates level using super class implementation
        super.upgrade();
        // Note: Recruitment Cost is updated based on the new level. i.e Recruitment
        // cost for level 2 is the cost needed for upgrading to level 3
            this.setUpgradeCost(1500);
            if (this.getLevel() == 2)
                this.setRecruitmentCost(550);
            else
                this.setRecruitmentCost(600);
    }

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		// Check for and throw BuildingInCooldownException and MaxRecruitedException
		if (this.isCoolDown())
			throw new BuildingInCoolDownException("Cannot recruit while in cooldown");
		if (this.getCurrentRecruit() == this.getMaxRecruit())
			throw new MaxRecruitedException("Reached max number of recruits per turn " + this.getMaxRecruit());
		// Get building's current level to determine the appropriate values for the unit
		int currentLevel = this.getLevel();
		Unit unit= null;
		switch(currentLevel) {
		case 1:
		case 2: unit = new Infantry(currentLevel,50,0.5,0.6,0.7); break;
		case 3: unit = new Infantry(currentLevel,60,0.6,0.7,0.8); break;
		}
		// Updates the number of units recruited by the building during a turn
		this.setCurrentRecruit(this.getCurrentRecruit() + 1);
		return unit;
	}

}
