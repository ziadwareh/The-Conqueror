package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class WorldMapView extends JPanel {
    private JButton rome;
	private JButton cairo;
    private JButton sparta;
    private JButton ArmyStatus;
    private JButton Target;
    private JButton Profile;
    private JButton EndTurn;
    private JButton Rules;

    public WorldMapView() {
        this.setLayout(new BorderLayout());
        this.rome = new JButton("Rome");
        this.rome.setEnabled(false);
        this.cairo = new JButton("Cairo");
        this.cairo.setEnabled(false);
        this.sparta = new JButton("Sparta");
        this.sparta.setEnabled(false);
        this.ArmyStatus = new JButton("Controlled Armies Status");
        this.Target = new JButton("Target");
        this.Profile = new JButton("Player Profile");
        this.EndTurn= new JButton("End Turn");
        this.Rules= new JButton("Rules?");
        JPanel countries = new JPanel();
        countries.setSize(this.getWidth(), 90);
        countries.setLayout(new FlowLayout());
        countries.add(sparta);
        countries.add(cairo);
        countries.add(rome);
        countries.add(ArmyStatus);
        countries.add(Target);
        Target.setActionCommand("target");
        countries.add(Profile);
        countries.add(EndTurn);
        countries.add(Rules);
        Profile.setActionCommand("profile");
        Rules.setActionCommand("rules");
        this.add(countries, BorderLayout.SOUTH);
        
    }
    
    public JButton getRules() {
		return Rules;
	}

	public JButton getProfile() {
		return Profile;
	}
    public JButton getRome() {
		return rome;
	}
	public JButton getCairo() {
		return cairo;
	}
	public JButton getSparta() {
		return sparta;
	}
	public JButton getArmyStatus() {
		return ArmyStatus;
	}
	
	public JButton getTarget() {
		return Target;
	}
	

	public JButton getEndTurn() {
		return EndTurn;
	}

protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bgd = getImage("Images/WorldMap hamadabelganzabel.png");
        bgd = scale(bgd, this.getWidth(), this.getHeight());
        g.drawImage(bgd, 0, 0, this);
    }

    public static BufferedImage getImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
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
    private static void addLabels(JPanel countries, int x) {
        for(int i=0;i<x;i++) {
            countries.add(new JLabel());
        }
    }
    
}