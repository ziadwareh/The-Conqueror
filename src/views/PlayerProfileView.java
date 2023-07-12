package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.City;
import engine.Game;
import engine.Player;

public class PlayerProfileView extends JFrame {

	public PlayerProfileView(Game g) {
		this.setBounds(500, 200, 250, 250);
		this.setVisible(false);

		JPanel title = new JPanel();
		title.setLayout(new FlowLayout());
		title.setPreferredSize(new Dimension(this.getWidth(), 30));
		title.setBackground(Color.CYAN);
		JLabel t = new JLabel("Your Profile");
		t.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		title.add(t);
		this.add(title, BorderLayout.NORTH);

		JPanel info = new JPanel();
//		info.setBackground(Color.GREEN);
		int z = 3 + g.getPlayer().getControlledCities().size();
		info.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() - 30));

		// info needed.
		// Name
		JLabel name = new JLabel("            Name:                    " + g.getPlayer().getName());
		info.add(name);

		// Controlled Cities
		JLabel cities = new JLabel("        Controlled Cities:                          ");
		info.add(cities);

		for (int i = 0; i < g.getPlayer().getControlledCities().size(); i++) {
			JLabel k = new JLabel("\n                                               "
					+ g.getPlayer().getControlledCities().get(i).getName());
			info.add(k);
		}
		// just some space
		JLabel space = new JLabel(
				"                                                                                    ");
		info.add(space);

		// Treasury
		JLabel treasury = new JLabel("            Gold:                         " + g.getPlayer().getTreasury());

		// Food
		JLabel food = new JLabel("            Food:                         " + g.getPlayer().getFood());

		// add to the Panel

		info.add(treasury);
		info.add(food);

		JLabel turnCount = new JLabel("            Turn Count:                         " + g.getCurrentTurnCount());
		info.add(turnCount);

		this.add(info, BorderLayout.CENTER);
		this.repaint();
		this.revalidate();
	}

}