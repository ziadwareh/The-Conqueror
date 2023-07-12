package controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import engine.*;
import views.*;
import units.*;


public class TmpController implements ActionListener{
	private JFrame window;
	
	public TmpController() {
		window = new JFrame();
		window.setTitle("Test");
		window.setVisible(true);
		//window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension size = Toolkit. getDefaultToolkit().getScreenSize();
		window.setBounds(size.width/2 - 500,size.height/2 - 325,1000,650);
		PlayerInfoPanel panel = new PlayerInfoPanel(1000,650);
		panel.getPlayerName().addActionListener(this);
		window.add(panel);
		window.revalidate();
		window.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JTextField) {
			
		}	
	}
}
