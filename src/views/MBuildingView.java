package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class MBuildingView extends JFrame {
	JPanel MB;
	JButton ArcheryRange;
	JButton Barracks;
	JButton Stable;
	JPanel Buttons;

	public MBuildingView() {

		this.setBounds(0, 0, 300, 400);
		// creating the upper panel that will hold the Military Buildings label.
		this.MB = new JPanel();
		this.MB.setPreferredSize(new Dimension(this.getWidth(), 30));
		this.MB.setLayout(new FlowLayout());
		this.add(MB, BorderLayout.NORTH);
		JLabel MBL = new JLabel("Military Building"); // Label Carrying the word
		this.MB.add(MBL);

		// add a new panel carrying the buttons and the buttons
		this.Buttons = new JPanel();
		ArcheryRange = new JButton("Archery Range");
		ArcheryRange.setPreferredSize(new Dimension(230, 70));
		Barracks = new JButton("Barracks");
		Barracks.setPreferredSize(ArcheryRange.getSize());
		Stable = new JButton("Stable");
		Stable.setPreferredSize(ArcheryRange.getSize());

		// Add Image to the button
		ArcheryRange.setIcon(new ImageIcon("Images/ArcheryRange.png"));
		Barracks.setIcon(new ImageIcon("Images/Barracks.png"));
		Stable.setIcon(new ImageIcon("Images/Stable.png"));
		//

		this.Buttons.setLayout(new GridLayout(3, 0));
		this.Buttons.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() - 300));
		this.Buttons.add(ArcheryRange);
		this.Buttons.add(Barracks);
		this.Buttons.add(Stable);

		// add the panel to the frame
		this.add(Buttons, BorderLayout.CENTER);

		this.repaint();
		this.revalidate();
	}

	public JButton getArcheryRange() {
		return ArcheryRange;
	}

	public JButton getBarracks() {
		return Barracks;
	}

	public JButton getStable() {
		return Stable;
	}
}