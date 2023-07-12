package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class EBuildingView extends JFrame {
	JPanel EB;
	JButton Farm;
	JButton Market;
	JPanel Buttons; 
	
	public EBuildingView() {
		
		this.setBounds(0, 0, 300, 300);
	//	this.setVisible(true);
		//creating the upper panel that will hold the Economical Buildings label.
		this.EB=new JPanel();
		this.EB.setPreferredSize(new Dimension(this.getWidth(),30));
		this.EB.setLayout(new FlowLayout());
		this.add(EB, BorderLayout.NORTH);
		JLabel EBL = new JLabel("Economical Building"); //Label Carrying the word
		this.EB.add(EBL);
		
		//add a new panel carrying the buttons
		this.Buttons=new JPanel();
		this.add(Buttons, BorderLayout.SOUTH);
		Farm = new JButton("Farm");		
		Market = new JButton("Market");
		
		Market.setIcon(new ImageIcon("Images/market.png"));
		Farm.setIcon(new ImageIcon("Images/Farm.png"));
		
		this.Buttons.setLayout(new GridLayout(2,0));
		this.Buttons.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()-300));
		this.Buttons.add(Farm);
		this.Buttons.add(Market);
		this.add(Buttons, BorderLayout.CENTER);
		
		this.repaint();
		this.revalidate();
	}
	
	public static void main(String[] args) {
		new EBuildingView();
	}

	public JButton getFarm() {
		return Farm;
	}

	public JButton getMarket() {
		return Market;
	}
	
}