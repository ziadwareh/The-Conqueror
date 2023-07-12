package engine;

import units.Unit;

public interface UnitListener {
	void onDeadUnit(Unit deadUnit);

	void onGetDead(int dead, Unit target);
}