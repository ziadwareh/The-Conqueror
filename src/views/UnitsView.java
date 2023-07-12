package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UnitsView extends JFrame {
	private JPanel header;
	private JPanel Content;
	private JButton Cavalry;
	private JButton Infantry;
	private JButton Archer;
	private int armyIndex;

	public UnitsView() {
		// initializing the window
		this.setBounds(0, 0, 800, 600);
		//this.setVisible(true);
		
		// initializing the header
		this.header = new JPanel();
		this.header.setPreferredSize(new Dimension(50, 30));
		this.header.setLayout(new FlowLayout());
		JLabel title = new JLabel("Units");
		title.setFont(new Font("Times New Roman",Font.BOLD, 24));
		this.header.add(title);
		this.add(header, BorderLayout.NORTH);
		
		// initializing the content panel that contains the grid and everything else as
		// stated in the draft
		this.Content = new JPanel();
		this.Content.setPreferredSize(new Dimension(this.getWidth(),570));
		this.Content.setLayout(new GridLayout(0,1));
		this.add(Content, BorderLayout.SOUTH);
		
		//now will configure what's inside the Content Panel
		//for the Cavalry
		JPanel cav = createPanel(this.Content.getWidth(),this.Content.getHeight()/3);
		JLabel cav_title=new JLabel("Cavalry");
		cav_title.setFont(new Font("Times New Roman",Font.BOLD, 24));
		cav.add(cav_title);
		cav.add(new JLabel(new ImageIcon("Images/Cavalry resized.jpg")));
		this.Cavalry=new JButton("view");
		this.Cavalry.setActionCommand("viewCavalry");
		cav.add(this.Cavalry);
		//for the infantry
		JPanel inf= createPanel(this.Content.getWidth(),this.Content.getHeight()/3);
		JLabel inf_title=new JLabel("Infantry");
		inf_title.setFont(new Font("Times New Roman",Font.BOLD, 24));
		inf.add(inf_title);
		inf.add(new JLabel(new ImageIcon("images/Infantry rescaled.jpg")));
		this.Infantry=new JButton("view");
		this.Infantry.setActionCommand("viewInfantry");
		inf.add(Infantry);
		//for the Archer
		JPanel arch=createPanel(this.Content.getWidth(),this.Content.getHeight()/3);
		JLabel arch_title=new JLabel("Archers");
		arch_title.setFont(new Font("Times New Roman",Font.BOLD, 24));
		arch.add(arch_title);
		arch.add(new JLabel(new ImageIcon("images/Archer rescaled.jpg")));
		this.Archer= new JButton("view");
		this.Archer.setActionCommand("viewArcher");
		arch.add(Archer);
		this.armyIndex = -1;
		//
		this.Content.add(cav);
		this.Content.add(inf);
		this.Content.add(arch);
		this.revalidate();
		this.repaint();
	}
	public static JPanel createPanel(int width, int height) {
		JPanel result=new JPanel();
		result.setPreferredSize(new Dimension(width, height));
		result.setLayout(new FlowLayout());
		return result;
	}
	
	
	public int getArmyIndex() {
		return armyIndex;
	}
	public void setArmyIndex(int armyIndex) {
		this.armyIndex = armyIndex;
	}
	public JPanel getHeader() {
		return header;
	}
	public JPanel getContent() {
		return Content;
	}
	public JButton getCavalry() {
		return Cavalry;
	}
	public JButton getInfantry() {
		return Infantry;
	}
	public JButton getArcher() {
		return Archer;
	}
	
}
