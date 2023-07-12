package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CairoCityView extends CityView {
	public CairoCityView() {
		this.getCityName().setText("Cairo");
		this.getMap().setIcon(new ImageIcon("Images/Cairo resized 7.jpg"));
		this.repaint();
		this.revalidate();

	}

}
