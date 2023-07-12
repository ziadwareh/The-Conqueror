package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class RulesView extends JFrame{
	public RulesView() {
		this.setBounds(0, 0, 1050, 700);
		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		p.setBackground(Color.yellow);
		p.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		p.setLayout(new GridLayout(54,0));
		JLabel r1 = new JLabel("                                                  Rules and Describtion:");
		JLabel r2 = new JLabel("                                                 The goal is to conquer the whole world by taking control over every other city.");
		JLabel r3 = new JLabel("                                                 Each city has an army that defends it from conquerors.");
		JLabel r4 = new JLabel("                                                 You needs to build a powerful army and defeat the defending army in order to take control over the city.");
		JLabel r5 = new JLabel("                                                 Once the attacking army reaches the city, it can either lay siege on the target city trying to starve them out or directly attack the defending army.");
		JLabel r6 = new JLabel("                                                 If You chooses to besiege the city, the defending army will gradually lose soldiers each turn the city is under siege for max 3 turns.");
		JLabel r7 = new JLabel("                                                 During these three turns, You can choose to break the siege and leave or fight the defending army. After these three turns, you have to fight the defending army in a decisive battle.");
		JLabel r8 = new JLabel("                                                 When You engages the defending army in a battle, you can choose to either automatically resolves the battle or he manually commands your units during it.");
		JLabel r9 = new JLabel("                                                 In auto resolve mode, a random unit from the attacking army will attack another random unit from the defending army then, same action");
		JLabel n1 = new JLabel("                                                 happens but this time the defending army will be the attacker and so on till one of the two armies is completely destroyed.");
		JLabel r10 = new JLabel("                                                 Only the attacked unit receives damage and have some of its soldiers lost during the attack. The attacking unit does not receive any damage in the process.");
		JLabel r11 = new JLabel("                                                 In the manual mode, the battle starts by You choosing one of your units to attack another unit in the defending army followed by a random unit from the defending");
		JLabel n2 = new JLabel("                                                 army attack another random unit from Your army and so on till one of the two armies is destroyed.");
		JLabel r12 = new JLabel("                                                 Once the city's defending army is defeated, You takes control over it and can build any building or recruit any unit inside it.");
		
		JLabel r13 = new JLabel("                                                                                                                                                              ");
		
		//Resources
		JLabel r14 = new JLabel("                                                  Available Resources:");
		JLabel r15 = new JLabel("                                                 1. Gold is the main resource needed to build or upgrade any building and also to recruit units.");
		JLabel r16 = new JLabel("                                                 Gold will also be used to maintain and upkeep any army You controls.");
		JLabel r17 = new JLabel("                                                 You has to pay a certain amount of gold each turn for this purpose.");
		JLabel r18 = new JLabel("                                                 If You don't have enough gold to maintain/upkeep your army, soldiers in your armies will gradually leave the army each turn until you have enough gold to maintain your army again.");
		JLabel r19 = new JLabel("                                                 2. Food is used to keep the soldiers in Your army alive.");
		JLabel r20 = new JLabel("                                                 Soldiers consume a certain amount of food each turn.");
		JLabel r21 = new JLabel("                                                 They consume more while marching to a city and will consume the most while besieging the city.");
		JLabel r22 = new JLabel("                                                 If You do not have sufficient amount of food for all of your soldiers, they will gradually die each turn until you have enough food again to feed your army.");
		
		JLabel r23 = new JLabel("                                                                                                                                                              ");
		
		//Building
		JLabel r24 = new JLabel("                                                    Buildings:");
		JLabel r26 = new JLabel("                                                 There are two types of buildings, economic and military. More about them later.");
		JLabel r27 = new JLabel("                                                 Buildings can be upgraded to enhance their effects. The higher the level of the building, the better its outcome.");
		JLabel r28 = new JLabel("                                                 Each turn, You can only do one action per building. Either building it, upgrading it or recruit units from it (for military buildings).");
		JLabel r29 = new JLabel("                                                 Military buildings also have a max number of units that can be recruited from them per turn.");
		
		JLabel r30 = new JLabel("                                                                                                                                                              ");
		
		//Economical Building
		JLabel r31 = new JLabel("                                                  Economical Buildings: ");
		JLabel r32 = new JLabel("                                                 1. Markets: these buildings are the source of gold for You.");
		JLabel r33 = new JLabel("                                                 The more markets you controls, the more profit you gains hence, the bigger your treasury is.");
		JLabel r34 = new JLabel("                                                 The gained profit can be used in constructing other buildings, upgrading them and recruit units.");
		JLabel r35 = new JLabel("                                                 2. Farms: They provide the food supplies of the army.");
		JLabel r36 = new JLabel("                                                 The bigger the army, the bigger the supply it will need.");
		JLabel r37 = new JLabel("                                                 You will need to balance the size of your army and the supplies you can provide to your army");
		
		JLabel r38 = new JLabel("                                                                                                                                                              ");
		
		//Military Buildings
		JLabel r39 = new JLabel("                                                Military Buildings and Units:");
		JLabel r40 = new JLabel("                                                 1. Archery Range: This building will enable You to recruit archers.");
		JLabel r41 = new JLabel("                                                 Archers can attack from range using their bows.");
		JLabel r42 = new JLabel("                                                 They have great advantage over foot units but greatly vulnerable to mounted units.");
		JLabel r43 = new JLabel("                                                 2. Barracks: This building will enable You to recruit infantry.");
		JLabel r44 = new JLabel("                                                 Infantry units are foot units that can engage in hand to hand combat.");
		JLabel r45 = new JLabel("                                                 They have good advantage against mounted units but vulnerable against archers");
		JLabel r46 = new JLabel("                                                 3. Stable: This building will enable You to recruit cavalry units.");
		JLabel r47 = new JLabel("                                                 Cavalry units are horse riders that can charge fast towards their enemies dealing great damage.");
		JLabel r48 = new JLabel("                                                 They are supreme against archers, good against infantry units and mediocre against other cavalry units.");
		
		JLabel r49 = new JLabel("                                                                                                                                                              ");
		
		JLabel r50 = new JLabel("                                                       Winning/losing:");
		JLabel r51 = new JLabel("                                                                                                      You will win if he managed to conquer all cities available in the game under the 50 turns.");
		JLabel r52 = new JLabel("                                                                                                      If these turns passed and the player did not achieve this goal, this is considered a loss.");
		
		//font and size
		r1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
		r2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));
		r3.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r4.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r5.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r6.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r7.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r8.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r9.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r10.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r11.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r12.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r13.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r14.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
		r15.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r16.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r17.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r18.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r19.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r20.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r21.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r22.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r23.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r24.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
		r26.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r27.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r28.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r29.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r30.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r31.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
		r32.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r33.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r34.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r35.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r36.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r37.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r38.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r39.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
		r40.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r41.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r42.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r43.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r44.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r45.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r46.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r47.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r48.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r49.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r50.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
		n1.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		n2.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r51.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
		r52.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 11));
	
		
		//add labels to panel
		p.add(r1);
		p.add(r2);
		p.add(r3);
		p.add(r4);
		p.add(r5);
		p.add(r6);
		p.add(r7);
		p.add(r8);
		p.add(r9);
		p.add(n1);
		p.add(r10);
		p.add(r11);
		p.add(n2);
		p.add(r12);
		p.add(r13);
		p.add(r14);
		p.add(r15);
		p.add(r16);
		p.add(r17);
		p.add(r18);
		p.add(r19);
		p.add(r20);
		p.add(r21);
		p.add(r22);
		p.add(r23);
		p.add(r24);
		p.add(r26);
		p.add(r27);
		p.add(r28);
		p.add(r29);
		p.add(r30);
		p.add(r31);
		p.add(r32);
		p.add(r33);
		p.add(r34);
		p.add(r35);
		p.add(r36);
		p.add(r37);
		p.add(r38);
		p.add(r39);
		p.add(r40);
		p.add(r41);
		p.add(r42);
		p.add(r43);
		p.add(r44);
		p.add(r45);
		p.add(r46);
		p.add(r47);
		p.add(r48);
		p.add(r49);
		p.add(r50);
		p.add(r51);
		p.add(r52);
		
		this.add(p);
		this.revalidate();
		this.repaint();
	}
	
	public static void main(String[] args) {
		new RulesView();
	}

}
