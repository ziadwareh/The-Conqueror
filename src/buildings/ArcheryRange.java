package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);

	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        super.upgrade();
        this.setUpgradeCost(700);
            if (this.getLevel() == 2)
                this.setRecruitmentCost(450);
            else
                this.setRecruitmentCost(500);
    }

	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		// Check for and throw BuildingInCooldownException and MaxRecruitedException
		if (this.isCoolDown())
			throw new BuildingInCoolDownException("Cannot recruit while in cooldown");
		if (this.getCurrentRecruit() == this.getMaxRecruit())
			throw new MaxRecruitedException("Reached max number of recruits per turn " + this.getMaxRecruit());
		// Get building's current level to determine the appropriate values for the unit
		int currentLevel = this.getLevel();
		Unit unit = null;
		switch (currentLevel) {
		case 1:
		case 2:unit = new Archer(currentLevel, 60, 0.4, 0.5, 0.6); break;
		case 3:unit = new Archer(currentLevel, 70, 0.5, 0.6, 0.7); break;
		}
		// Updates the number of units recruited by the building during a turn
		this.setCurrentRecruit(this.getCurrentRecruit() + 1);
		return unit;
	}

}
