package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StartGamePanel extends JPanel{
	private JButton StartGameButton;
	public StartGamePanel(int panelWidth, int panelHeight) {
		StartGameButton = new JButton("Start Game");
		//Dimension size = Toolkit. getDefaultToolkit().getScreenSize();
		setLayout(null);
		setSize(panelWidth,panelHeight);
		StartGameButton.setBounds((this.getSize().width/2)-125, this.getSize().height/2, 250, 55);
		StartGameButton.setBackground(Color.GRAY.brighter());
		StartGameButton.setFocusPainted(false);
		StartGameButton.setFont(new Font("Monotype Corsiva", Font.BOLD, 22));
		add(StartGameButton);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage bgd = getImage("images/the conquerer.png");
		bgd = scale(bgd,this.getWidth(),this.getHeight());
		g.drawImage(bgd, 0,0, this);
	}
	
	public static BufferedImage getImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File (path));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }

	public JButton getStartGameButton() {
		return StartGameButton;
	}
	
	
}
