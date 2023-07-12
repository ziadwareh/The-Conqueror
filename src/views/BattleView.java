package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import units.*;

public class BattleView extends JFrame {
	private JTextArea battle;
	private JPanel p;
	private JPanel pPlayerArmy;
	private JPanel pDefendingArmy;
	private JLabel i1;
	private JLabel i2;
	private JLabel image;
	private JLabel type;
	private JLabel currentSoldierCount;
	private JButton attack;
	private JButton auto;
	private JButton attackerUnit;
	private JButton defenderUnit;
	private ArrayList<JButton> playerUnits;
	private ArrayList<JButton> DefendingUnits;
	private Army attackingArmy;
	private Army defendingArmy;

	@SuppressWarnings("deprecation")
	public BattleView(Army da, Army pa) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, 950, 500);
		this.attackingArmy = pa;
		this.defendingArmy = da;
		JPanel pInstruction;
		JPanel title;
		JPanel bottom;
		JLabel t;

		// battle text log
		battle = new JTextArea();
		battle.setEditable(false);
		battle.setPreferredSize(new Dimension(200, getHeight() - 20));
		battle.setText("GOOOOOOO");
		battle.setLineWrap(true);
		this.add(battle, BorderLayout.EAST);

		// title panel and label
		t = new JLabel("                BATTLE IS ON!!!!!!                           ");
		t.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		title = new JPanel();
		title.setPreferredSize(new Dimension(this.getWidth(), 30));
		title.add(t);
		this.add(title, BorderLayout.NORTH);

		// battle panel
		p = new JPanel();
		p.setPreferredSize(new Dimension(700, this.getHeight() - 30));
		p.setLayout(new BorderLayout());

		// Player Army
		pPlayerArmy = new JPanel();
		pPlayerArmy.setPreferredSize(new Dimension(200, p.getHeight() - 40));
		pPlayerArmy.setLayout(new FlowLayout());
		pPlayerArmy.setBackground(Color.BLUE);
		JLabel pat = new JLabel("                Your Army:               ");
		pPlayerArmy.add(pat);

		ArrayList<Unit> playerArcher;
		ArrayList<Unit> playerCavalry;
		ArrayList<Unit> playerInfantry;
		playerUnits = new ArrayList<JButton>();
		playerArcher = pa.getUnitOfType("Archer");
		for (int j = 0; j < playerArcher.size(); j++) {
			JButton archerButton = new JButton("Archer  " + (j+1));
			playerUnits.add(archerButton);
		}

		playerCavalry = pa.getUnitOfType("Cavalry");
		for (int j = 0; j < playerCavalry.size(); j++) {
			JButton cavalryButton = new JButton("Cavalry  " + (j+1));
			playerUnits.add(cavalryButton);
		}

		playerInfantry = pa.getUnitOfType("Infantry");
		for (int j = 0; j < playerInfantry.size(); j++) {
			JButton infantryButton = new JButton("Infantry  " + (j+1));
			playerUnits.add(infantryButton);
		}

		for (int i = 0; i < playerUnits.size(); i++)
			pPlayerArmy.add(playerUnits.get(i));

		p.add(pPlayerArmy, BorderLayout.EAST);

		// Defending Army
		pDefendingArmy = new JPanel();
		pDefendingArmy.setPreferredSize(new Dimension(200, p.getHeight() - 40));
		pDefendingArmy.setLayout(new FlowLayout());
		pDefendingArmy.setBackground(Color.RED);
		JLabel dat = new JLabel("Defending Army:     ");
		pDefendingArmy.add(dat);

		ArrayList<Unit> DefendingArcher;
		ArrayList<Unit> DefendingCavalry;
		ArrayList<Unit> DefendingInfantry;
		DefendingUnits = new ArrayList<JButton>();

		DefendingArcher = da.getUnitOfType("Archer");
		for (int j = 0; j < DefendingArcher.size(); j++) {
			JButton archerButton = new JButton("Archer  " + (j+1));
			DefendingUnits.add(archerButton);
		}

		DefendingCavalry = da.getUnitOfType("Cavalry");
		for (int j = 0; j < DefendingCavalry.size(); j++) {
			JButton cavalryButton = new JButton("Cavalry  " + (j+1));
			DefendingUnits.add(cavalryButton);
		}

		DefendingInfantry = da.getUnitOfType("Infantry");
		for (int j = 0; j < DefendingInfantry.size(); j++) {
			JButton infantryButton = new JButton("Infantry  " +( j+1));
			DefendingUnits.add(infantryButton);
		}

		for (int i = 0; i < DefendingUnits.size(); i++)
			pDefendingArmy.add(DefendingUnits.get(i));

		p.add(pDefendingArmy, BorderLayout.WEST);

		// Instructions
		pInstruction = new JPanel();
		pInstruction.setPreferredSize(new Dimension(300, p.getHeight() - 40));
		pInstruction.setLayout(new GridLayout(4, 0));
		i1 = new JLabel("Choose your attacking Unit !");
		// when a button is pressed we use .hide() to remove the label and show another
		pInstruction.add(i1);
//		image = new JLabel();
//		image.setIcon(new ImageIcon("images/fight.jpg"));
//		image.hide();
//		image.show(); //a method to be used in the controller to show the label when a button is pushed
		// pInstruction.add(image);
		i2 = new JLabel("Choose the Unit to attack !");
		i2.hide();
		pInstruction.add(i2);
		type = new JLabel("   Type:   ");
		pInstruction.add(type);
		currentSoldierCount = new JLabel("   Current Soldier Count:   ");
		pInstruction.add(currentSoldierCount);

		p.add(pInstruction, BorderLayout.CENTER);

		// bottom attack panel
		bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(p.getWidth(), 50));
		bottom.setLayout(new FlowLayout());
		attack = new JButton("ATTTAAAAACK!!!!!!!!");
		attack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		auto = new JButton("Auto Resolve");
		auto.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		bottom.add(auto);
		bottom.add(attack);
		attack.hide();
		// use .show() in the controller after the player choose a unit to attack
		p.add(bottom, BorderLayout.SOUTH);

		// add p to the window
		this.add(p, BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
	}

	public void removeFromPPlayerArmy(JButton deadUnit) {
		this.playerUnits.remove(deadUnit);
		pPlayerArmy.remove(deadUnit);
	}

	public void removeFromPDefendingArmy(JButton deadUnit) {
		this.DefendingUnits.remove(deadUnit);
		pDefendingArmy.remove(deadUnit);
	}

	public void disableDefendingUnits() {
		for (int i = 0; i < this.getDefendingUnits().size(); i++)
			this.getDefendingUnits().get(i).setEnabled(false);
	}

	public void disableAttackingUnits() {
		for (int i = 0; i < this.getPlayerUnits().size(); i++)
			this.getPlayerUnits().get(i).setEnabled(false);
	}

	public void enableAttackingUnits() {
		for (int i = 0; i < this.getPlayerUnits().size(); i++)
			this.getPlayerUnits().get(i).setEnabled(true);
	}

	public void enableDefendingUnits() {
		for (int i = 0; i < this.getDefendingUnits().size(); i++)
			this.getDefendingUnits().get(i).setEnabled(true);
	}

	public void setType(String type) {
		this.type.setText("   Type:   " + type);
	}

	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount.setText("   Current Soldier Count:   " + currentSoldierCount);
	}

	@SuppressWarnings("deprecation")
	public void iterateInstructions(int i) {
		if (i == 0) {
			i1.show();
			i2.hide();
			attack.hide();
		}
		if (i == 1) {
			i1.hide();
			i2.show();
			attack.hide();
		} else {
			i1.hide();
			i2.hide();
			attack.show();
		}
		this.revalidate();
		this.repaint();
	}

	// to be continued
	public void onBattleLog() {
		String happened = "";

	}

	public Army getAttackingArmy() {
		return attackingArmy;
	}

	public Army getDefendingArmy() {
		return defendingArmy;
	}

	public JTextArea getBattle() {
		return battle;
	}

	public void setBattle(JTextArea battle) {
		this.battle = battle;
	}

	public JButton getAttackerUnit() {
		return attackerUnit;
	}

	public void setAttackerUnit(JButton attackerUnit) {
		this.attackerUnit = attackerUnit;
	}

	public JButton getDefenderUnit() {
		return defenderUnit;
	}

	public void setDefenderUnit(JButton defenderUnit) {
		this.defenderUnit = defenderUnit;
	}

	public ArrayList<JButton> getPlayerUnits() {
		return playerUnits;
	}

		public void setPlayerUnits() {
		ArrayList<Unit> playerArcher;
		ArrayList<Unit> playerCavalry;
		ArrayList<Unit> playerInfantry;
		playerArcher = this.attackingArmy.getUnitOfType("Archer");
		int j=0;
		for (j = 0; j < playerArcher.size(); j++) {
			this.playerUnits.get(j).setText("Archer  " + (j+1));
			this.playerUnits.get(j).setActionCommand("Archer  " + (j+1));
		}

		playerCavalry = this.attackingArmy.getUnitOfType("Cavalry");
		for (int i = 0; i < playerCavalry.size(); i++) {
			this.playerUnits.get(j).setText("Calary  " + (i+1));
			this.playerUnits.get(j).setActionCommand("Cavalry  " + (i+1));
			j++;
		}

		playerInfantry = this.attackingArmy.getUnitOfType("Infantry");
		for (int i = 0; i < playerInfantry.size(); i++) {
			this.playerUnits.get(j).setText("Infantry  " + (i+1));
			this.playerUnits.get(j).setActionCommand("Infantry  " + (i+1));
			j++;
		}
	}

	public ArrayList<JButton> getDefendingUnits() {
		return DefendingUnits;
	}

	public void setDefendingUnits() {
		ArrayList<Unit> DefendingArcher;
		ArrayList<Unit> DefendingCavalry;
		ArrayList<Unit> DefendingInfantry;
		
		DefendingArcher = this.defendingArmy.getUnitOfType("Archer");
		int j=0;
		for (j = 0; j < DefendingArcher.size(); j++) {
			this.DefendingUnits.get(j).setText("Archer  " + (j+1));
			this.DefendingUnits.get(j).setActionCommand("Archer  " + (j+1));
		}

		DefendingCavalry = this.defendingArmy.getUnitOfType("Cavalry");
		for (int i = 0; i < DefendingCavalry.size(); i++) {
			this.DefendingUnits.get(j).setText("Calary  " + (i+1));
			this.DefendingUnits.get(j).setActionCommand("Cavalry  " + (i+1));
			j++;
		}

		DefendingInfantry = this.defendingArmy.getUnitOfType("Infantry");
		for (int i = 0; i < DefendingInfantry.size(); i++) {
			this.DefendingUnits.get(j).setText("Infantry  " + (i+1));
			this.DefendingUnits.get(j).setActionCommand("Infantry  " + (i+1));
			j++;
		}
	}
	
	public Unit getAttackingUnit() {
		String tmp = attackerUnit.getActionCommand();
		String type = tmp.substring(0, tmp.length() - 3);
		ArrayList<Unit> units = attackingArmy.getUnitOfType(type);
		int i = Integer.parseInt("" + tmp.charAt(tmp.length() - 1)) - 1;
		return units.get(i);
	}

	public Unit getDefendingUnit() {
		String tmp = defenderUnit.getActionCommand();
		String type = tmp.substring(0, tmp.length() - 3);
		ArrayList<Unit> units = defendingArmy.getUnitOfType(type);
		int i = Integer.parseInt("" + tmp.charAt(tmp.length() - 1)) - 1;
		return units.get(i);
	}

	public void disableAll() {
		this.disableAttackingUnits();
		this.disableDefendingUnits();
		this.attack.setEnabled(false);
		this.auto.setEnabled(false);
	}

	public void enableAll() {
		this.enableAttackingUnits();
		this.enableDefendingUnits();
		this.auto.setEnabled(true);
		this.attack.setEnabled(true);
	}

	public JLabel getI1() {
		return i1;
	}

	public JLabel getI2() {
		return i2;
	}

	public JButton getAttack() {
		return attack;
	}

	public JButton getAuto() {
		return auto;
	}
	public void updateBattleLog(String s) {
		if(battle.getLineCount() == 20)
			battle.removeAll();
		battle.append(s);
	}

//	public static void main(String[] args) {
//		Army da = new Army("Cairo");
//		Army pa = new Army("Sparta");
//		Unit a = new Cavalry(1, 54, 0.5, 0.4, 0.3);
//		Unit b = new Cavalry(1, 54, 0.5, 0.4, 0.3);
//		Unit c = new Infantry(1, 54, 0.5, 0.4, 0.3);
//		Unit d = new Infantry(1, 54, 0.5, 0.4, 0.3);
//		Unit e = new Archer(1, 54, 0.5, 0.4, 0.3);
//		Unit f = new Archer(1, 54, 0.5, 0.4, 0.3);
//		Unit g = new Cavalry(1, 54, 0.5, 0.4, 0.3);
//		Unit h = new Cavalry(1, 54, 0.5, 0.4, 0.3);
//		da.getUnits().add(a);
//		pa.getUnits().add(b);
//		da.getUnits().add(c);
//		pa.getUnits().add(d);
//		da.getUnits().add(e);
//		pa.getUnits().add(f);
//		da.getUnits().add(g);
//		pa.getUnits().add(h);
//		BattleView v = new BattleView(da, pa);
//	}

}