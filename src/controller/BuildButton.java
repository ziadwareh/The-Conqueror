package controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BuildButton extends JFrame {
	private JButton Build;
	private JLabel Cost;

	public BuildButton() {
		this.setBounds(400, 200, 270, 150);
		JLabel message = new JLabel(" You First need to build");
		message.setPreferredSize(new Dimension(this.getWidth(), 30));
		message.setFont(new Font("times new roman", Font.BOLD, 24));
		this.Build = new JButton("Build");
		this.Build.setPreferredSize(new Dimension(this.getWidth(), 70));
		this.Build.setFont(new Font("times new roman", Font.BOLD, 24));
		JPanel fees = new JPanel();
		fees.setPreferredSize(new Dimension(this.getWidth(), 50));
		this.setLayout(new FlowLayout());
		this.Cost = new JLabel();
		this.Cost.setFont(new Font("times new roman", Font.BOLD, 24));
		JLabel text = new JLabel("Cost: ");
		text.setFont(new Font("times new roman", Font.BOLD, 24));
		fees.add(text);
		fees.add(Cost);
		this.setLayout(new GridLayout(0, 1));
		this.add(message);
		this.add(Build);
		this.add(fees);
		this.repaint();
		this.revalidate();
	}

	public JButton getBuild() {
		return Build;
	}

	public JLabel getCost() {
		return Cost;
	}

}
