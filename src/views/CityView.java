package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract public class CityView extends JFrame {
	private JButton EconomicalBuildings;
	private JButton MilitaryBuildings;
	private JButton DefendingArmy;
	private JLabel CityName;
	private JLabel map;
	private boolean accessable;

	public CityView() {
		this.setBounds(0, 0, 800, 600);
		JPanel citytitle = new JPanel();
		citytitle.setPreferredSize(new Dimension(50, 30));
		citytitle.setLayout(new FlowLayout());
		this.add(citytitle, BorderLayout.NORTH);
		
		// Map
		JPanel cityMap = new JPanel();// for this we just add a label and change its content to the city map
		cityMap.setPreferredSize(new Dimension(this.getWidth(), 50));
		cityMap.setLayout(new FlowLayout());
		this.add(cityMap, BorderLayout.CENTER);
		this.map = new JLabel();
		cityMap.add(map);
		
		// setting the buttons
		JPanel Infrastructure = new JPanel();
		Infrastructure.setPreferredSize(new Dimension(this.getWidth(), 100));
		Infrastructure.setLayout(new FlowLayout());
		this.add(Infrastructure, BorderLayout.SOUTH);
		EconomicalBuildings = new JButton("Economical Buildings");
		EconomicalBuildings.setActionCommand("EBuilding");
		MilitaryBuildings = new JButton("Military Buildings");
		MilitaryBuildings.setActionCommand("MBuildingView");
		DefendingArmy = new JButton("Defending Army");
		Infrastructure.add(EconomicalBuildings);
		Infrastructure.add(DefendingArmy);
		Infrastructure.add(MilitaryBuildings);
		
		// City name label
		this.CityName = new JLabel();
		this.CityName.setFont(new Font("Times New Roman", Font.BOLD, 24));
		citytitle.add(this.CityName);
		accessable = false;
	}

	public boolean isAccessable() {
		return accessable;
	}

	public void setAccessable(boolean accessable) {
		this.accessable = accessable;
	}

	public JButton getEconomicalBuildings() {
		return EconomicalBuildings;
	}

	public JButton getMilitaryBuildings() {
		return MilitaryBuildings;
	}

	public JButton getDefendingArmy() {
		return DefendingArmy;
	}

	public JLabel getCityName() {
		return CityName;
	}

	public JLabel getMap() {
		return map;
	}

}
