package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WhichUnitView extends JFrame {
	JButton view1;
	JButton view2;
	JButton view3;
	JButton view4;
	JButton view5;
	JButton view6;
	JButton view7;
	JButton view8;
	JButton view9;
	JButton view10;
public WhichUnitView(int x) {
	this.setBounds(0, 0, 800, 600);
	this.setVisible(true);//
	view1=new JButton("Unit 1");
	view2=new JButton("Unit 2");
	view3=new JButton("Unit 3");
	view4=new JButton("Unit 4");
	view5=new JButton("Unit 5");
	view6=new JButton("Unit 6");
	view7=new JButton("Unit 7");
	view8=new JButton("Unit 8");
	view9=new JButton("Unit 9");
	view10=new JButton("Unit 10");
	JPanel Buttons=new JPanel();
	Buttons.setPreferredSize(new Dimension(800,600));
	Buttons.setLayout(new GridLayout(0,1));
	ArrayList<JButton> buttons= new ArrayList<JButton>();
	buttons.add(view1);
	buttons.add(view2);
	buttons.add(view3);
	buttons.add(view4);
	buttons.add(view5);
	buttons.add(view6);
	buttons.add(view7);
	buttons.add(view8);
	buttons.add(view9);
	buttons.add(view10);
	for(int i=0;i<x;i++) {
		Buttons.add(buttons.get(i));
	}
	this.add(Buttons);
	this.revalidate();
	this.repaint();
}
public static void main(String[] args) {
	new WhichUnitView(10);
}
}
