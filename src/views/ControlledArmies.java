package views;
import units.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class ControlledArmies extends JFrame {
	private JPanel info;
	private JPanel titleP;
	private ArrayList<JButton> armies;
	private JButton defendingArmy;
	private JButton initiateArmy;
	private String initiatedBy;
	
	public ControlledArmies(ArrayList<Army> a) {
		
		this.setBounds(0, 0, 300, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		armies = new ArrayList<JButton>();
		initiatedBy = "";
		titleP = new JPanel();
		JLabel title = new JLabel("Controlled Armies");
		this.titleP.setPreferredSize(new Dimension(this.getWidth(),30));
		this.titleP.setLayout(new FlowLayout());
		this.titleP.add(title);
		this.add(titleP, BorderLayout.NORTH);
		info = new JPanel();
		this.info.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()-60));
		this.info.setLayout(new FlowLayout());
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(this.getWidth(),30));
		//bottom.setLayout(new GridLayout(1,3,10,15));
		defendingArmy = new JButton("Defending Army");
		defendingArmy.setActionCommand("relocateIntoDA");
		defendingArmy.setEnabled(false);
		initiateArmy = new JButton("Initiate Army");
		initiateArmy.setEnabled(false);
		bottom.add(defendingArmy,BorderLayout.EAST);
		bottom.add(new JLabel(""),BorderLayout.CENTER);
		bottom.add(initiateArmy,BorderLayout.WEST);
		for(int i = 0; i<a.size(); i++) {
			JButton Army = new JButton("Army:       " + (i+1) +"      Status:      " + a.get(i).getCurrentStatus());
			Army.setFont(new Font("Times New Roman", Font.BOLD,18));
			Army.setEnabled(false);
			armies.add(Army);
			info.add(armies.get(i));
		}
		
		this.add(info, BorderLayout.CENTER);
		this.add(bottom,BorderLayout.SOUTH);
		this.repaint();
		this.revalidate();
	}

	public ArrayList<JButton> getArmies() {
		return armies;
	}

	public String getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public void enableArmies() {
		for(int i = 0; i < armies.size(); i++) {
			armies.get(i).setEnabled(true);
		}
	}
	public void disableArmies() {
		for(int i = 0; i < armies.size(); i++) {
			armies.get(i).setEnabled(false);
		}
	}
	
	public void updateArmyStatus(int i, String newStatus) {
		armies.get(i).setText("Army:       " + (i+1) +"      Status:      " + newStatus);
		this.revalidate();
		this.repaint();
	}

	public JButton getDefendingArmy() {
		return defendingArmy;
	}

	public void setDefendingArmy(JButton defendingArmy) {
		this.defendingArmy = defendingArmy;
	}

	public JButton getInitiateArmy() {
		return initiateArmy;
	}

	public void setInitiateArmy(JButton initiateArmy) {
		this.initiateArmy = initiateArmy;
	}
	
//	public static void main(String[] args) {
//		Army a = new Army("Ahmed");
//		Army b = new Army("Ahmed");
//		Army c = new Army("Ahmed");
//		Army d = new Army("Ahmed");
//		Army e = new Army("Ahmed");
//		ArrayList k = new ArrayList<Army>();
//		k.add(a);
//		k.add(b);
//		k.add(c);
//		k.add(d);
//		k.add(e);
//		new ControlledArmies(k);
//	}
}