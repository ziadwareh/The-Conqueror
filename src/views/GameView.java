package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameView extends JFrame {
	private ArrayList<JPanel>panels;

	public GameView() {
		setTitle("The Conquerer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension size = Toolkit. getDefaultToolkit().getScreenSize();
		setBounds(size.width/2 - 500, size.height/2 - 325, 800, 600);
		panels = new ArrayList<JPanel>();
		StartGamePanel startGamePanel = new StartGamePanel(800,600);
		this.add(startGamePanel);
		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(800,600);
		WorldMapView worldMapView = new WorldMapView(); 
		panels.add(startGamePanel);
		panels.add(playerInfoPanel);
		panels.add(worldMapView);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}

	public void updateView(JPanel newPanel) {
			for(int i = 0 ; i < panels.size() ; i++) {
				this.remove(panels.get(i));
			}
			this.add(newPanel, BorderLayout.CENTER);
			
			this.revalidate();
			this.repaint();
	}
	
	public void updateView(JPanel newPanel, JPanel exceptionPanel) {
		for(int i=0; i < panels.size(); i++) {
			if(panels.get(i) == exceptionPanel)
				i++;
			this.remove(panels.get(i));
		}
	}

	public ArrayList<JPanel> getPanels() {
		return panels;
	}
	
	public static void main(String[] args) {
		GameView v = new GameView();
	}
}
