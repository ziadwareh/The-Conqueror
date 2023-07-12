package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TargetView extends JFrame {
	private ArrayList<JButton> targetButtons;

	public TargetView() {
		this.setBounds(50, 50, 400, 300);
		this.targetButtons = new ArrayList<JButton>();
		JButton Sparta = new JButton("Sparta");
		Sparta.setActionCommand("targetSparta");
		Sparta.setFont(new Font("times new roman", Font.BOLD, 30));
		JButton Rome = new JButton("Rome");
		Rome.setActionCommand("targetRome");
		Rome.setFont(new Font("times new roman", Font.BOLD, 30));
		JButton Cairo = new JButton("Cairo");
		Cairo.setActionCommand("targetCairo");
		Cairo.setFont(new Font("times new roman", Font.BOLD, 30));
		targetButtons.add(Sparta); 
		targetButtons.add(Rome);
		targetButtons.add(Cairo);
		JPanel targets = new JPanel();
		targets.setPreferredSize(new Dimension(300, 400));
		targets.setLayout(new GridLayout(0, 1));
		targets.add(Sparta);
		targets.add(Cairo);
		targets.add(Rome);
		this.add(targets);
		this.revalidate();
		this.repaint();
	}

	public ArrayList<JButton> getTargetButtons() {
		return targetButtons;
	}
}