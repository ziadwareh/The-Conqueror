package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BesiegingInfo extends JFrame{
	JPanel info;
	JPanel titleP;
	JLabel location;
	JLabel turnsUnderBesieging;
	
	//The controller should enter these attributes
	public BesiegingInfo(String l, int turnsCount) {
		this.setBounds(0, 0, 300, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//this panel carries info of the Unit
		info = new JPanel();
		titleP = new JPanel();
		
		//the info would be added to the Label
		this.info.setLayout(new GridLayout(2,0));
		this.info.setBackground(Color.ORANGE);
		this.titleP.setLayout(new FlowLayout());
		this.titleP.setBackground(Color.WHITE);
		JLabel title = new JLabel("Besieging Armies");
		location = new JLabel("                  City Under Siege:     "+ l);
		turnsUnderBesieging = new JLabel("                  Turns under Siege:     " + turnsCount);
		
		//adding the buttons
		this.info.add(location);
		this.info.add(turnsUnderBesieging);
		
		this.titleP.add(title);
		this.add(titleP, BorderLayout.NORTH);
		this.add(info, BorderLayout.CENTER);
		
		this.repaint();
		this.revalidate();
	}
	
	public static void main(String[] args) {
		new BesiegingInfo("Cairo", 2);
	}

}