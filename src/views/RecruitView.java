package views;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RecruitView extends JFrame{
	private JButton Cavalry;
	private JButton Infantry;
	private JButton Archer;
	
	
	public RecruitView() {
		this.setBounds(50, 50, 400, 300);
		this.setVisible(true);
		this.Cavalry = new JButton("Cavalry");
		this.Cavalry.setActionCommand("recruitCavalry");
		this.Cavalry.setFont(new Font("times new roman", Font.BOLD, 30));
		this.Infantry = new JButton("Infantry");
		this.Cavalry.setActionCommand("recruitInfantry");
		this.Infantry.setFont(new Font("times new roman", Font.BOLD, 30));
		this.Archer = new JButton("Archer");
		this.Cavalry.setActionCommand("recruitArcher");
		this.Archer.setFont(new Font("times new roman", Font.BOLD, 30));
		JPanel targets = new JPanel();
		targets.setPreferredSize(new Dimension(300, 400));
		targets.setLayout(new GridLayout(0, 1));
		targets.add(Cavalry);
		targets.add(Archer);
		targets.add(Infantry);
		this.add(targets);
		
		this.revalidate();
		this.repaint();
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

