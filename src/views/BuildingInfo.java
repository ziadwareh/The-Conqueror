package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract public class BuildingInfo extends JFrame {
	private JLabel level;
	private JLabel upgradecost;
	private JLabel BuildingPic;
	private JLabel BuildingName;
	private JButton upgrade;
	private JPanel stats;
	private JPanel buttons;

	public BuildingInfo() {
		this.setBounds(0, 0, 800, 600);
		// title Panel;
		JPanel title = new JPanel();
		title.setPreferredSize(new Dimension(this.getWidth(), 30));
		title.setLayout(new FlowLayout());
		this.add(title, BorderLayout.NORTH);
		// building name label
		this.BuildingName = new JLabel();
		this.BuildingName.setFont(new Font("Times new roman", Font.BOLD, 24));
		title.add(this.BuildingName);
		// Content Panel (it will contain status and the building pic )
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(1, 0));
		content.setPreferredSize(new Dimension(this.getWidth(), 570));
		add(content, BorderLayout.SOUTH);
		// building pic
		this.BuildingPic = new JLabel();
		content.add(BuildingPic);
		// panel that will hold the stats and upgrade button
		JPanel status = new JPanel();
		status.setPreferredSize(new Dimension(content.getWidth() / 2, 570));
		status.setLayout(new GridLayout(0, 1));
		content.add(status);
		// panel with the labels that will represent my desired data;
		this.stats = new JPanel();
		stats.setPreferredSize(new Dimension(status.getWidth(), 500));
		stats.setLayout(new GridLayout(0, 2));
		status.add(stats);
		JLabel level_label = new JLabel("Current Level: ");
		level_label.setFont(new Font("times new roman", Font.BOLD, 24));
		this.level = new JLabel();
		level.setFont(new Font("times new roman", Font.BOLD, 24));
		JLabel upgrade_cost_label = new JLabel("Upgrade Cost");
		upgrade_cost_label.setFont(new Font("times new roman", Font.BOLD, 24));
		this.upgradecost = new JLabel();
		upgradecost.setFont(new Font("times new roman", Font.BOLD, 24));
		stats.add(level_label);
		stats.add(this.level);
		stats.add(upgrade_cost_label);
		stats.add(this.upgradecost);
		content.add(status);
		// upgrade & Recruit Buttons
		this.upgrade = new JButton("Upgrade");
		this.upgrade.setFont(new Font("times new roman", Font.BOLD, 30));
		this.upgrade.setPreferredSize(new Dimension(content.getWidth()/2, 70));
		this.buttons= new JPanel();
		this.buttons.setPreferredSize(new Dimension(content.getWidth(),70));
		this.buttons.setLayout(new GridLayout(1,0));
		status.add(buttons);
		buttons.add(upgrade);
	}

	public JLabel getLevel() {
		return level;
	}

	public JLabel getUpgradecost() {
		return upgradecost;
	}

	public JLabel getBuildingPic() {
		return BuildingPic;
	}

	public JLabel getBuildingName() {
		return BuildingName;
	}

	public JButton getUpgrade() {
		return upgrade;
	}

	public JPanel getStats() {
		return stats;
	}

	public JPanel getButtons() {
		return buttons;
	}
	

}