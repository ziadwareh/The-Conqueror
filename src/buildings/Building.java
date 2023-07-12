package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;

public abstract class Building {

	private int cost;
	private int level;
	private int upgradeCost;
	private boolean coolDown;

	public Building(int cost, int upgradeCost) {
		this.cost = cost;
		this.upgradeCost = upgradeCost;
		this.level = 1;
		coolDown = true;
	}

	public int getCost() {
		return cost;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public boolean isCoolDown() {
		return coolDown;
	}

	public void setCoolDown(boolean inCooldown) {
		this.coolDown = inCooldown;
	}
	
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		// throw a BuildingInCoolDownException if user tries to upgrade a builing
		// still in cooldown
		if (this.isCoolDown())
			throw new BuildingInCoolDownException("Building cannot upgrade while in cooldown");
		// throw a MaxLevelException if user tries to upgrade a level 3 builing
		if (this.getLevel() == 3)
			throw new MaxLevelException("Cannot upgrade, Max level 3 has been reached");
		// Get current Level for all types of Building subclasses and update its value
		int currentLevel = this.getLevel();
		this.setLevel(currentLevel + 1);
		this.setCoolDown(true);
	}
	
	/*public void upgrade() throws BuildingInCoolDownException, MaxLevelException{
		if( this instanceof Farm) {
			Farm f = (Farm) this;
			MaxLevelException ml = new MaxLevelException("Max Level reached");
			BuildingInCoolDownException cd = new BuildingInCoolDownException("Building in cool down");
			if(f.getLevel() == 2)
				throw(ml);
			else {
				f.setLevel(f.getLevel() + 1 );
				f.setUpgradeCost(700);
				f.setCoolDown(false);
			}
			
			if(f.isCoolDown() == false)
				throw(cd);
			
			f.setCoolDown(true);
		}
		
		else if( this instanceof Market) {
			Market m = (Market) this;
			MaxLevelException ml = new MaxLevelException("Max Level reached");
			BuildingInCoolDownException cd = new BuildingInCoolDownException("Building in cool down");
			if (m.getLevel() == 2)
				throw(ml);
			else {
				m.setLevel(m.getLevel() + 1 );
				m.setUpgradeCost(1000);
				m.setCoolDown(false);
			}
			
			if(m.isCoolDown() == false)
				throw(cd);
			
			m.setCoolDown(true);
		}
		
		else if( this instanceof ArcheryRange) {
			ArcheryRange ar = (ArcheryRange) this;
			MaxLevelException ml = new MaxLevelException("Max Level reached");
			BuildingInCoolDownException cd = new BuildingInCoolDownException("Building in cool down");
			if(ar.getLevel() == 3)
				throw(ml);
			else {
				switch(ar.getLevel()){
					case 1: ar.setLevel(ar.getLevel() + 1 );ar.setUpgradeCost(700);ar.setRecruitmentCost(450);ar.setCoolDown(false);break;
					case 2: ar.setLevel(ar.getLevel() + 1 );ar.setRecruitmentCost(500);ar.setCoolDown(false);break;
				}
			}
			
			if(ar.isCoolDown() == false)
				throw(cd);
			
			ar.setCoolDown(true);
		}
		
		else if( this instanceof Barracks) {
			Barracks b = (Barracks) this;
			MaxLevelException ml = new MaxLevelException("Max Level reached");
			BuildingInCoolDownException cd = new BuildingInCoolDownException("Building in cool down");
			if(b.getLevel() == 3)
				throw(ml);
			else {
				switch(b.getLevel()){
					case 1: b.setLevel(b.getLevel() + 1 );b.setUpgradeCost(1500);b.setRecruitmentCost(550);b.setCoolDown(false);break;
					case 2: b.setLevel(b.getLevel() + 1 );b.setRecruitmentCost(600);b.setCoolDown(false);break;
				}
			}
			
			if(b.isCoolDown() == false)
				throw(cd);
			
			b.setCoolDown(true);
		}
		
		else if( this instanceof Stable) {
			Stable s = (Stable) this;
			MaxLevelException ml = new MaxLevelException("Max Level reached");
			BuildingInCoolDownException cd = new BuildingInCoolDownException("Building in cool down");
			if(s.getLevel() == 3)
				throw(ml);
			else {
				switch(s.getLevel()){
					case 1: s.setLevel(s.getLevel() + 1 );s.setUpgradeCost(2000);s.setRecruitmentCost(650);s.setCoolDown(false);break;
					case 2: s.setLevel(s.getLevel() + 1 );s.setRecruitmentCost(700);s.setCoolDown(false);break;
				}
			}
			
			if(s.isCoolDown() == false)
				throw(cd);
			
			s.setCoolDown(true);
		}
		
	}*/

}
