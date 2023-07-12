package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MarchingInfo extends JFrame{
	JPanel info;
	JPanel titleP;
	JLabel target;
	JLabel distance;
	
	//The controller should enter these attributes
	public MarchingInfo(String TargetedCity, int TurnsLeft) {
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
		this.titleP.setBackground(Color.white);
		JLabel title = new JLabel("Marching Armies");
		target = new JLabel("                  Targeted City:     "+ TargetedCity);
		distance = new JLabel("         Distance remaining to reach target:     " + TurnsLeft);
		
		//adding the buttons
		this.info.add(target);
		this.info.add(distance);
		
		this.titleP.add(title);
		this.add(titleP, BorderLayout.NORTH);
		this.add(info, BorderLayout.CENTER);
		
		this.repaint();
		this.revalidate();
	}
	
	public static void main(String[] args) {
		new MarchingInfo("Cairo", 2);
	}

}