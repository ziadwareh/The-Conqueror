package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PlayerInfoPanel extends JPanel{
	private JTextField playerName; 
	private JButton confirmSelectionButton;
	private ArrayList<JButton> cityButtons;
	
	public PlayerInfoPanel(int panelWidth, int panelHeight) {
		this.setSize(panelWidth,panelHeight);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		cityButtons = new ArrayList<JButton>();
		playerName = new JTextField("Enter The Player's Name Here", 20 );
		playerName.setPreferredSize(new Dimension (350,40));
		playerName.setMaximumSize(new Dimension (350,40));
		playerName.setFont(new Font("Bookman Old Style",Font.ITALIC,22));
		playerName.setBackground(Color.GRAY.brighter());
		
		confirmSelectionButton = new JButton("Confirm Selection");
		cityButtons.add(new JButton("Rome"));
        cityButtons.get(0).setActionCommand("choseRome");
        cityButtons.add(new JButton("Sparta"));
        cityButtons.get(1).setActionCommand("choseSparta");
        cityButtons.add(new JButton("Cairo"));
        cityButtons.get(2).setActionCommand("choseCairo");
        
		confirmSelectionButton.setPreferredSize(new Dimension(250, 40));
		confirmSelectionButton.setBackground(Color.GRAY.brighter());
		confirmSelectionButton.setFocusPainted(false);
		confirmSelectionButton.setFont(new Font("Bookman Old Style",Font.PLAIN,22));
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(panelWidth, panelHeight/3));
		top.setMaximumSize(new Dimension(panelWidth, panelHeight/3));
		top.setBackground(Color.DARK_GRAY.darker());
		JPanel empty = new JPanel();
		empty.setPreferredSize(new Dimension (panelWidth,30));
		empty.setBackground(Color.DARK_GRAY.darker());
		top.add(empty,BorderLayout.NORTH);
		top.add(playerName,BorderLayout.CENTER);
		this.add(top);
		JPanel bottom = new JPanel(new GridLayout(1,2,50,0));
		bottom.setPreferredSize(new Dimension(panelWidth, panelHeight*(2/3)));
		bottom.setBackground(Color.DARK_GRAY.darker());
		JPanel mapPanel = new JPanel();
		mapPanel.setBackground(Color.DARK_GRAY.darker());
		JLabel miniMap = new JLabel();
		try{
			Image map = (Image)ImageIO.read(new File("images/WorldMap hamadabelganzabel.png"));
			map = map.getScaledInstance(panelWidth/2, panelHeight*2/3, 0);
			miniMap = new JLabel(new ImageIcon(map));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		mapPanel.add(miniMap);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
		JPanel citiesPanel = new JPanel(new GridLayout(5, 1, 1, 50));
		citiesPanel.setMaximumSize(new Dimension(panelWidth/5, panelHeight*2/3));
		citiesPanel.setBackground(Color.DARK_GRAY.darker());
		JLabel empty1 = new JLabel("");
		citiesPanel.add(empty1);
		for(int i=0; i< cityButtons.size(); i++) {
			cityButtons.get(i).setMaximumSize(new Dimension(panelWidth/6, 40));
			cityButtons.get(i).setBackground(Color.GRAY.brighter());
			cityButtons.get(i).setFocusPainted(false);
			cityButtons.get(i).setFont(new Font("Bookman Old Style",Font.PLAIN,22));
			citiesPanel.add(cityButtons.get(i));
		}
		JPanel confirmSelectionPanel = new JPanel();
		confirmSelectionPanel.setMaximumSize(new Dimension(panelWidth/3,panelHeight*2/3));
		confirmSelectionPanel.setBackground(Color.DARK_GRAY.darker());
		confirmSelectionPanel.add(confirmSelectionButton,BorderLayout.CENTER);
		buttonsPanel.add(confirmSelectionPanel);
		buttonsPanel.add(citiesPanel);
		bottom.add(buttonsPanel);
		bottom.add(mapPanel);
		this.add(bottom);
	}

	public JTextField getPlayerName() {
		return playerName;
	}

	public JButton getConfirmSelectionButton() {
		return confirmSelectionButton;
	}

	public ArrayList<JButton> getCityButtons() {
		return cityButtons;
	}
}
