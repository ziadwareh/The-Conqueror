package views;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.*;

public class ChoosePAView extends JFrame {
	private JPanel PA;
	private JPanel title;
	private JPanel footer;
	private JLabel windowTitle;
	private JButton DefendingArmy;
	private JButton Initiate;
	private JButton next;
	
	public ChoosePAView() {
		this.setBounds(50, 50, 400, 300);
		this.setVisible(true);
		this.PA = new JPanel();
		this.PA.setLayout(new FlowLayout());
		this.add(PA, BorderLayout.CENTER);
		this.title = new JPanel();
		this.title.setLayout(new FlowLayout());
		this.add(title, BorderLayout.NORTH);
		this.windowTitle = new JLabel("Chosse the Unit's Parent Army");
		this.footer = new JPanel();
		this.footer.setLayout(new FlowLayout());
		this.add(footer, BorderLayout.SOUTH);
		this.Initiate = new JButton("Initiate a new Army");
		this.next = new JButton("Next");
		this.DefendingArmy = new JButton("Defending Army");
		this.title.add(windowTitle);
		this.footer.add(Initiate);
		addLabels(footer, 20);
		this.footer.add(next);
		this.PA.add(DefendingArmy);
		
		this.revalidate();
		this.repaint();
	}
	
	private static void addLabels(JPanel countries, int x) {
        for(int i=0;i<x;i++) {
            countries.add(new JLabel());
        }
    }
	
//	public static void main(String[] args) {
//		new ChoosePAView();
//	}
}
