package views;

import java.awt.*;
import javax.swing.*;

public class UnitsInfo extends JFrame{
	private JPanel info;
	private JPanel titleP;
	private JPanel relocate;
	private JPanel checkList;
	private JLabel type;
	private JLabel level;
	private JLabel currentSoldierCount;
	private JLabel maxSoldierCount;
	private JButton relocateUnit;
	private int indexOfArmy;
	
	
	//The controller should enter these attributes
	public UnitsInfo(String type1, int level1,int currentSoldierCount1, int maxSoldierCount1) {
		this.setBounds(0, 0, 400, 400);
		//this.setVisible(true);
		indexOfArmy =-1;
		//this panel carries info of the Unit
		info = new JPanel();
		titleP = new JPanel();
		relocate = new JPanel();
		checkList = new JPanel();
		
		this.titleP.setBackground(Color.CYAN);
		
		//the info would be added to the Label
		this.info.setLayout(new GridLayout(4,0));
		this.titleP.setLayout(new FlowLayout());
		this.relocate.setLayout(new FlowLayout());
		this.checkList.setLayout(new BoxLayout(checkList,BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Unit's Info");
		type = new JLabel("                  Type:     "+ type1);
		level = new JLabel("                 Level:     " + level1);
		currentSoldierCount = new JLabel("                Current Soldier Count:    " + currentSoldierCount1);
		maxSoldierCount = new JLabel("                  Max Soldier Count:    " + maxSoldierCount1);
		relocateUnit = new JButton("Relocate Unit");
		//adding the buttons
		this.info.add(type);
		this.info.add(level);
		this.info.add(currentSoldierCount);
		this.info.add(maxSoldierCount);
		this.titleP.add(title);
		this.relocate.add(relocateUnit);
		this.add(relocate, BorderLayout.SOUTH);
		this.add(titleP, BorderLayout.NORTH);
		this.add(info, BorderLayout.CENTER);
		this.add(checkList,BorderLayout.EAST);
		
		this.repaint();
		this.revalidate();
	}

	public JLabel getLevel() {
		return level;
	}

	public JLabel getCurrentSoldierCount() {
		return currentSoldierCount;
	}

	public JLabel getMaxSoldierCount() {
		return maxSoldierCount;
	}
	
	// AWT has a method called getType so i couldn't call it that
	public String getTypeString() {
		String s = type.getText().substring(28,type.getText().length());
		return s;
	}

	public void setType(String type) {
		this.type.setText("                  Type:     "+ type);
	}

	public void setLevel(int level) {
		this.level.setText("                 Level:     " + level);
	}

	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount.setText("                Current Soldier Count:    " + currentSoldierCount);
	}

	public void setMaxSoldierCount(int maxSoldierCount) {
		this.maxSoldierCount.setText("                  Max Soldier Count:    " + maxSoldierCount);
	}
	public void setUnitInfo (int level, int currentSoldierCount, int maxSoldierCount) {
		setLevel(level);
		setCurrentSoldierCount(currentSoldierCount);
		setMaxSoldierCount(maxSoldierCount);
		this.revalidate();
		this.repaint();
	}
	
	public int countCheckedBoxes() {
		int c= 0;
		for(int i = 0; i < checkList.getComponentCount(); i++) {
			if(((JCheckBox)checkList.getComponent(i)).isSelected())
				c++;
		}
		return c;
	}
	public JPanel getCheckList() {
		return checkList;
	}

	public void setCheckList(JPanel checkList) {
		this.checkList = checkList;
	}

	public JButton getRelocateUnit() {
		return relocateUnit;
	}

	public void setRelocateUnit(JButton relocateUnit) {
		this.relocateUnit = relocateUnit;
	}

	public int getIndexOfArmy() {
		return indexOfArmy;
	}

	public void setIndexOfArmy(int indexOfArmy) {
		this.indexOfArmy = indexOfArmy;
	}
	
//	public static void main(String[] args) {
//		UnitsInfo u = new UnitsInfo("Cavalry", 4, 468, 45);
//		u.setType("Archer");
//		System.out.println(u.getTypeString());
//	}
	
	
	
	

}
