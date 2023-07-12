package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import units.Army;

public class NextAction extends JFrame{
	private JButton ManualAttack;
	private JButton Retreat;
	private JLabel text;
	private Army initiator;
	private JButton laySeige;
	
	public NextAction() {
		this.setBounds(400, 300, 800, 150);
		this.initiator=null;
		JPanel p = new JPanel();
		p.setBackground(Color.GREEN);
		p.setLayout(new GridLayout(2,0));
		this.text = new JLabel();
		text.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		JLabel n = new JLabel("                      Choose Your next Action:");
		n.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		p.add(text);
		p.add(n);
		this.add(p, BorderLayout.CENTER);
		
		ManualAttack= new JButton("Attack Defending Army");
		ManualAttack.setActionCommand("manualAttack");
		Retreat = new JButton("el gry nos el gad3anaaa!!!!!!!");
		Retreat.setActionCommand("retreat");
		
		ManualAttack.setEnabled(false);
		Retreat.setEnabled(false);
		laySeige = new JButton("Lay Seige");
		laySeige.setActionCommand("Seige");
		laySeige.setEnabled(false);

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.RED);
		buttons.setLayout(new FlowLayout());
		buttons.setPreferredSize(new Dimension(this.getWidth(), 40));
		JLabel space = new JLabel("                       ");
		buttons.add(ManualAttack);
		buttons.add(Retreat);
		buttons.add(laySeige);
		
		this.add(buttons, BorderLayout.SOUTH);
		
		this.repaint();
		this.revalidate();
	}

	public JButton getRetreat() {
		return Retreat;
	}

	public JButton getManualAttack() {
		return ManualAttack;
	}
	
	public JLabel getText() {
		return text;
	}

	public Army getInitiator() {
		return initiator;
	}

	public void setInitiator(Army initiator) {
		this.initiator = initiator;
	}

	public static void main(String[] args) {
		new NextAction();
	}

	public void setText(String text) {
		this.text.setText("            "+text);
	}

	public JButton getLaySeige() {
		return laySeige;
	}
	
	
}