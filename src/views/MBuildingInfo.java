package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class MBuildingInfo extends BuildingInfo {
	private JLabel recruitmentcost;
	private JButton Recruit;

	public MBuildingInfo() {
		JLabel recruitment_cost_label = new JLabel("Recruitment Cost: ");
		recruitment_cost_label.setFont(new Font("times new roman", Font.BOLD, 24));
		this.recruitmentcost = new JLabel();
		recruitmentcost.setFont(new Font("times new roman", Font.BOLD, 24));
		this.getStats().add(recruitment_cost_label);
		this.getStats().add(this.recruitmentcost);
		this.Recruit = new JButton("Recruit");
		this.Recruit.setFont(new Font("times new roman", Font.BOLD, 30));
		this.Recruit.setPreferredSize(new Dimension());
		this.getButtons().add(Recruit);
		this.revalidate();
		this.repaint();

	}

	public JLabel getRecruitmentcost() {
		return recruitmentcost;
	}

	public JButton getRecruit() {
		return Recruit;
	}
	
}