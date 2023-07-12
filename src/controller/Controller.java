package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import engine.*;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.MaxCapacityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import views.*;
import units.*;

public class Controller implements ActionListener, ItemListener, UnitListener, GameListener {
	private Game theConquerer;
	private GameView view;
	private ArrayList<CityView> cityViews;
	private UnitsView unitsView;
	private UnitsInfo unitsInfo;
	private TargetView targetView;
	private ControlledArmies controlledArmiesView;
	private BattleView battleView;
	private JButton selectedCity;
	private BuildButton BuildButton;
	private JButton selectedBuilding;
	private Clip citySound;
	private EBuildingView EBuildingView;
	private MBuildingView MBuildingView;
	private EBuildingInfo EBuildingInfo;
	private MBuildingInfo MBuildingInfo;
	//
	private NextAction NextAction;
	private PlayerProfileView PlayerProfileView;
	private boolean target_is_reached;
	private Army attaking_army;
	private boolean seiging;

	public Controller() {
		view = new GameView();
		selectedCity = null;
		BuildButton = new BuildButton();
		selectedBuilding = null;
		citySound = null;
		cityViews = new ArrayList<CityView>();
		RomeCityView rcv = new RomeCityView();
		SpartaCityView scv = new SpartaCityView();
		CairoCityView ccv = new CairoCityView();
		unitsView = new UnitsView();
		unitsInfo = new UnitsInfo("", 0, 0, 0);
		targetView = new TargetView();
		MBuildingView = new MBuildingView();
		EBuildingView = new EBuildingView();
		MBuildingInfo = new MBuildingInfo();
		EBuildingInfo = new EBuildingInfo();

		cityViews.add(rcv);
		cityViews.add(scv);
		cityViews.add(ccv);

		((StartGamePanel) view.getPanels().get(0)).getStartGameButton().addActionListener(this);

		((PlayerInfoPanel) view.getPanels().get(1)).getPlayerName().addActionListener(this);
		((PlayerInfoPanel) view.getPanels().get(1)).getConfirmSelectionButton().addActionListener(this);
		for (int i = 0; i < ((PlayerInfoPanel) view.getPanels().get(1)).getCityButtons().size(); i++)
			((PlayerInfoPanel) view.getPanels().get(1)).getCityButtons().get(i).addActionListener(this);

		((WorldMapView) view.getPanels().get(2)).getRome().addActionListener(this);
		((WorldMapView) view.getPanels().get(2)).getCairo().addActionListener(this);
		((WorldMapView) view.getPanels().get(2)).getSparta().addActionListener(this);
		((WorldMapView) view.getPanels().get(2)).getArmyStatus().addActionListener(this);
		((WorldMapView) view.getPanels().get(2)).getTarget().addActionListener(this);
		((WorldMapView) view.getPanels().get(2)).getProfile().addActionListener(this);
		((WorldMapView) view.getPanels().get(2)).getEndTurn().addActionListener(this);
		((WorldMapView) view.getPanels().get(2)).getRules().addActionListener(this);

		for (int i = 0; i < this.cityViews.size(); i++) {
			cityViews.get(i).getEconomicalBuildings().addActionListener(this);
			cityViews.get(i).getMilitaryBuildings().addActionListener(this);
			cityViews.get(i).getDefendingArmy().addActionListener(this);
		}

		unitsView.getCavalry().addActionListener(this);
		unitsView.getInfantry().addActionListener(this);
		unitsView.getArcher().addActionListener(this);

		unitsInfo.getRelocateUnit().addActionListener(this);

		BuildButton.getBuild().addActionListener(this);

		for (int i = 0; i < targetView.getTargetButtons().size(); i++) {
			targetView.getTargetButtons().get(i).addActionListener(this);
		}

		MBuildingView.getStable().addActionListener(this);
		MBuildingView.getBarracks().addActionListener(this);
		MBuildingView.getArcheryRange().addActionListener(this);

		EBuildingView.getFarm().addActionListener(this);
		EBuildingView.getMarket().addActionListener(this);

		MBuildingInfo.getUpgrade().addActionListener(this);
		MBuildingInfo.getRecruit().addActionListener(this);

		EBuildingInfo.getUpgrade().addActionListener(this);
		//
		NextAction = new NextAction();
		NextAction.getManualAttack().addActionListener(this);
		NextAction.getRetreat().addActionListener(this);
		NextAction.getLaySeige().addActionListener(this);
		this.target_is_reached = false;
		this.attaking_army = null;

		this.seiging = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JTextField)
			return;
		if (this.theConquerer != null && (this.theConquerer.isGameOver()
				|| this.theConquerer.getPlayer().getControlledCities().size() == 3)) {
			System.out.println("uo");
			System.out.println(this.theConquerer.getPlayer().getControlledCities().size());
			if (this.theConquerer.getPlayer().getControlledCities().size() == 3) {
				new ErrorPopUpWindow("Gongratulations! You are the Ultimate Conquerer!");
				playSound("sounds/Winning Speech.wav");
			} else {
				new ErrorPopUpWindow("Game Over! You Lost the Game");
				playSound("sounds/taban lak cristiano.wav");
			}
			view.updateView(view.getPanels().get(0));

		}

		else if (e.getActionCommand().equals("Start Game")) {
			playSound("sounds/7abes damohom.wav");
			view.updateView(view.getPanels().get(1));
		}

		else if (e.getActionCommand().equals("Confirm Selection")) {
			if (selectedCity != null) {
				String playerName = ((PlayerInfoPanel) view.getPanels().get(1)).getPlayerName().getText();
				setAccessable(selectedCity.getActionCommand());

				try {
					theConquerer = new Game(playerName, selectedCity.getText());
					this.PlayerProfileView = new PlayerProfileView(theConquerer);
					this.PlayerProfileView.setVisible(false);
					this.theConquerer.getPlayer().setTreasury(5000);
					this.theConquerer.getPlayer().setFood(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				view.updateView(view.getPanels().get(2));
			}

			else
				new ErrorPopUpWindow("You must select a city before confirming selection");
		}

		else if (((PlayerInfoPanel) view.getPanels().get(1)).getCityButtons().contains(((JButton) e.getSource()))) {
			if (selectedCity != null) {
				selectedCity.setBackground(Color.GRAY.brighter());
				citySound.stop();
			}
			citySound = getCitySound(e.getActionCommand());
			selectedCity = ((JButton) e.getSource());
			selectedCity.setBackground(Color.GREEN);
		}

		else if (e.getActionCommand().equals("profile")) {
			this.PlayerProfileView = new PlayerProfileView(this.theConquerer);
			this.PlayerProfileView.setVisible(true);
		} else if (e.getActionCommand().equals("Controlled Armies Status")) {
			initiateControlledArmiesView("ViewUnits");
		}

		else if (e.getActionCommand().equals("target")) {
			targetView.setVisible(true);
		}

		else if (targetView.getTargetButtons().contains((JButton) e.getSource())) {
			if(!Friendly_Fire_Exception(e)) {
			if (theConquerer.getPlayer().getControlledArmies().size() == 0)
				new ErrorPopUpWindow("You must initiate a new army first");
			else
				targetView.setVisible(false);
			this.initiateControlledArmiesView("" + ((JButton) e.getSource()).getText());
			}
			else
				new ErrorPopUpWindow("Freindly Fire");
				
		} else if (e.getActionCommand().equals("Auto Resolve")) {
			try {
//				this.NextAction.setVisible(false);
				theConquerer.autoResolve(battleView.getAttackingArmy(), battleView.getDefendingArmy());
				// System.out.println(this.attaking_army.getUnits().size() + "u");
				this.seiging = false;
				// if (this.target_is_reached == true && this.attaking_army.getUnits().size() ==
				// 0) {// (!this.theConquerer.isFinished_Battle()
				// &&) {
				// System.out.println(this.attaking_army.getUnits().size());
				this.target_is_reached = false;
				City temp = null;
				for (int i = 0; i < this.theConquerer.getAvailableCities().size(); i++) {
					if (this.theConquerer.getAvailableCities().get(i).getName()
							.equalsIgnoreCase(this.attaking_army.getCurrentLocation())) {
						temp = this.theConquerer.getAvailableCities().get(i);
						break;
					}
				}
				temp.setTurnsUnderSiege(0);
				temp.setUnderSiege(false);
				// this.theConquerer.setFinished_Battle(true);
				// }
			} catch (FriendlyFireException e1) {
				new ErrorPopUpWindow(e1.getMessage());
			}
		} else if (battleView != null && battleView.getPlayerUnits().contains((JButton) e.getSource())) {
			if ((JButton) e.getSource() == battleView.getAttackerUnit()) {
				if (battleView.getDefenderUnit() != null) {
					battleView.getDefenderUnit().setBackground(Color.WHITE);
					battleView.setDefenderUnit(null);
				}
				battleView.setType("");
				battleView.setCurrentSoldierCount(0);
				battleView.getAttackerUnit().setBackground(Color.WHITE);
				battleView.iterateInstructions(0);
				battleView.setAttackerUnit(null);
			} else {
				if (battleView.getAttackerUnit() == null) {
					battleView.setAttackerUnit((JButton) e.getSource());
				} else {
					battleView.getAttackerUnit().setBackground(Color.WHITE);
					battleView.setAttackerUnit((JButton) e.getSource());
				}
				battleView.getAttackerUnit().setBackground(Color.RED);
				battleView.setType(battleView.getAttackerUnit().getActionCommand().substring(0,
						battleView.getAttackerUnit().getActionCommand().length() - 3));
				battleView.setCurrentSoldierCount(battleView.getAttackingUnit().getCurrentSoldierCount());
				battleView.iterateInstructions(1);
			}
		} else if (battleView != null && battleView.getDefendingUnits().contains((JButton) e.getSource())) {
			if (battleView.getAttackerUnit() == null)
				new ErrorPopUpWindow("You must choose the attacker first");
			else if ((JButton) e.getSource() == battleView.getDefenderUnit()) {
				battleView.getDefenderUnit().setBackground(Color.WHITE);
				battleView.iterateInstructions(1);
				battleView.setDefenderUnit(null);
			} else {
				if (battleView.getDefenderUnit() == null)
					battleView.setDefenderUnit((JButton) e.getSource());
				else
					battleView.getDefenderUnit().setBackground(Color.WHITE);
				battleView.setDefenderUnit((JButton) e.getSource());
				battleView.getDefenderUnit().setBackground(Color.BLUE);
				battleView.iterateInstructions(2);
			}
		} else if (e.getActionCommand().equals("ATTTAAAAACK!!!!!!!!")) {
			battleView.getAttackingUnit().setUnitListener(this);
			battleView.getDefendingUnit().setUnitListener(this);
			try {
				battleView.getAttackingUnit().attack(battleView.getDefendingUnit());
			} catch (FriendlyFireException e1) {
				new ErrorPopUpWindow(e1.getMessage());
			}
			if (battleView.getPlayerUnits().size() != 0) {
				battleView.disableAll();
				initiateCounterAttack();
			}
			battleView.setType("");
			battleView.setCurrentSoldierCount(0);
			battleView.iterateInstructions(0);
		}

		if (theConquerer != null) {
			for (int i = 0; i < this.theConquerer.getAvailableCities().size(); i++) {
				if (this.theConquerer.getAvailableCities().get(i).isUnderSiege()
						&& this.theConquerer.getAvailableCities().get(i).getTurnsUnderSiege() == 3)
					NextAction.setVisible(true);
			}
		}
		city_view_activation(e);
		City_Buttons_View(e);
		units_view_activation(e);
		open_building_info(e);
		Initiate_Building(e);
		Upgrade_Building(e);
		Recruit_From_Building(e);
		if (e.getActionCommand().equals("manualAttack")) {
			this.NextAction.setVisible(false);
			if (this.target_is_reached == true
					&& (!this.theConquerer.isFinished_Battle() && this.attaking_army.getUnits().size() == 0)) {
				System.out.println(this.attaking_army.getUnits().size());
				this.target_is_reached = false;
				this.theConquerer.setFinished_Battle(true);
			}
			theConquerer.setGameListener(this);
			initiateBattleView(this.NextAction.getInitiator());
		} else if (e.getActionCommand().equals("relocateIntoDA")) {
			if (unitsInfo.countCheckedBoxes() == 0)
				new ErrorPopUpWindow("Please select a unit");
			else
				initiateRelocation(-1);
		} else if (e.getActionCommand().equals("Initiate Army")) {
			if (unitsInfo.countCheckedBoxes() == 0)
				new ErrorPopUpWindow("Please select a unit");
			else
				initiateNewArmy(unitsInfo.getIndexOfArmy());
		} else if (e.getActionCommand().equals("Relocate Unit")) {
			if (unitsInfo.countCheckedBoxes() == 0)
				new ErrorPopUpWindow("Please select a unit");
			else
				this.initiateControlledArmiesView("Relocate");
		} else if (controlledArmiesView != null && controlledArmiesView.getArmies().contains((JButton) e.getSource())) {
			switch (controlledArmiesView.getInitiatedBy()) {
			case ("Relocate"):
				initiateRelocation(controlledArmiesView.getArmies().indexOf((JButton) e.getSource()));
				break;
			case ("Rome"):
			case ("Sparta"):
			case ("Cairo"):
				setTargetForArmy(controlledArmiesView.getArmies().indexOf((JButton) e.getSource()));
				break;
			}
		}
		if (theConquerer != null) {
			for (int i = 0; i < this.theConquerer.getAvailableCities().size(); i++) {
				if (this.theConquerer.getAvailableCities().get(i).isUnderSiege()
						&& this.theConquerer.getAvailableCities().get(i).getTurnsUnderSiege() == 3)
					NextAction.setVisible(true);
			}
		}

		if (e.getActionCommand().equals("rules")) {
			playSound("sounds/SUCH A NOOB.wav");
			new RulesView();
		}
		if (e.getActionCommand().equals("Seige")) {
			try {
				int i = 0;
				for (i = 0; i < this.theConquerer.getAvailableCities().size(); i++)
					if (this.theConquerer.getAvailableCities().get(i).getName()
							.equals(this.attaking_army.getCurrentLocation()))
						break;
				City attacked = this.theConquerer.getAvailableCities().get(i);
				this.theConquerer.getPlayer().laySiege(this.attaking_army, attacked);
				this.target_is_reached = false;
				this.seiging = true;
				this.NextAction.setVisible(false);
			} catch (TargetNotReachedException | FriendlyCityException e1) {
				// TODO Auto-generated catch block
				new ErrorPopUpWindow(e1.getMessage());
			}
		}
		if (e.getActionCommand().equals("retreat")) {
			this.theConquerer.targetCity(attaking_army, this.selectedCity.getText());
			this.controlledArmiesView.updateArmyStatus(
					this.theConquerer.getPlayer().getControlledArmies().indexOf(attaking_army), "Marching");
			this.NextAction.setVisible(false);
			System.out.println(this.selectedCity.getText());
		}

		End_Turn(e);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox j = ((JCheckBox) e.getSource());
		Army a;
		City c = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getActionCommand()));
		if (unitsInfo.getIndexOfArmy() != -1)
			a = theConquerer.getPlayer().getControlledArmies().get(unitsInfo.getIndexOfArmy());
		else
			a = c.getDefendingArmy();
		ArrayList<Unit> chosenType = a.getUnitOfType(unitsInfo.getTypeString());
		int indexOfUnit = Integer.parseInt(j.getText().substring(9)) - 1;
		clearAllChecklistBGDs();
		// Display Unit's info in window
		if (e.getStateChange() == e.SELECTED) {
			Unit u = chosenType.get(indexOfUnit);
			j.setBackground(Color.GREEN);
			unitsInfo.setUnitInfo(u.getLevel(), u.getCurrentSoldierCount(), u.getMaxSoldierCount());
		} else if (e.getStateChange() == e.DESELECTED) {
			int prevUnitIndex = getPrevCheckedUnit();
			// The unchecked Box was the last checked one
			if (prevUnitIndex == -1) {
				unitsInfo.setUnitInfo(0, 0, 0);
			}
			// Display the last checked Box in the checklist
			else {
				Unit prevU = chosenType.get(getPrevCheckedUnit());
				unitsInfo.setUnitInfo(prevU.getLevel(), prevU.getCurrentSoldierCount(), prevU.getMaxSoldierCount());
			}
		}

	}

	private int getPrevCheckedUnit() {
		Component[] checkBoxes = unitsInfo.getCheckList().getComponents();
		for (int i = checkBoxes.length - 1; i >= 0; i--) {
			JCheckBox j = (JCheckBox) checkBoxes[i];
			if (j.isSelected()) {
				j.setBackground(Color.GREEN);
				return i;
			}
		}
		return -1;
	}

	private void clearAllChecklistBGDs() {
		Component[] checkBoxes = unitsInfo.getCheckList().getComponents();
		for (int i = checkBoxes.length - 1; i >= 0; i--) {
			JCheckBox j = (JCheckBox) checkBoxes[i];
			j.setBackground(Color.WHITE);
		}
	}

	private void initiateRelocation(int indexOfArmy) {
		Army sourceArmy = null;
		if (unitsInfo.getIndexOfArmy() == -1)
			sourceArmy = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getText()))
					.getDefendingArmy();
		else
			sourceArmy = theConquerer.getPlayer().getControlledArmies().get(unitsInfo.getIndexOfArmy());
		Army destinationArmy = null;
		Component[] checkBoxes = unitsInfo.getCheckList().getComponents();
		if (indexOfArmy == -1)
			destinationArmy = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getText()))
					.getDefendingArmy();
		else
			destinationArmy = theConquerer.getPlayer().getControlledArmies().get(indexOfArmy);
		if (sourceArmy == destinationArmy) {
			new ErrorPopUpWindow("Source army and destination army cannot be the same");
			return;
		}
		if (destinationArmy.getCurrentStatus() != Status.IDLE) {
			new ErrorPopUpWindow("Army must be idle to relocate");
			return;
		}
		int counter = 0;
		ArrayList<Unit> chosenType = sourceArmy.getUnitOfType(unitsInfo.getTypeString());
		for (int i = checkBoxes.length - 1; i >= 0; i--) {
			JCheckBox j = (JCheckBox) checkBoxes[i];
			if (j.isSelected()) {
				try {
					destinationArmy.relocateUnit(chosenType.get(i));
				} catch (MaxCapacityException e) {
					if (destinationArmy != theConquerer.getPlayer().getControlledCities()
							.get(getCity(selectedCity.getText())).getDefendingArmy())
						new ErrorPopUpWindow(counter + "units relocated. This Army is now at Max Capacity, "
								+ destinationArmy.getMaxToHold() + " units");
					// e.printStackTrace();
					break;
				}
				unitsInfo.getCheckList().remove(j);
				unitsInfo.revalidate();
				unitsInfo.repaint();
				counter++;
			}
		}
		loadChecklist(unitsInfo.getCheckList(), sourceArmy.getUnitOfType(unitsInfo.getTypeString()),
				unitsInfo.getTypeString());
		controlledArmiesView.setVisible(false);
	}

	private void initiateNewArmy(int indexOfArmy) {
		Army sourceArmy;
		if (indexOfArmy == -1)
			sourceArmy = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getText()))
					.getDefendingArmy();
		else {
			new ErrorPopUpWindow("Only the defending army can preform this action");
			return;
		}
		ArrayList<Unit> a = sourceArmy.getUnitOfType(unitsInfo.getTypeString());
		for (int i = unitsInfo.getCheckList().getComponentCount() - 1; i >= 0; i--) {
			if (((JCheckBox) unitsInfo.getCheckList().getComponent(i)).isSelected()) {
				Unit unit = a.get(i);
				a.remove(i);
				unitsInfo.getCheckList().remove(i);
				City c = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getText()));
				theConquerer.getPlayer().initiateArmy(c, unit);
				break;
			}
		}
		initiateRelocation(theConquerer.getPlayer().getControlledArmies().size() - 1);
	}

	private void setTargetForArmy(int indexOfArmy) {
		Army army = theConquerer.getPlayer().getControlledArmies().get(indexOfArmy);
		String target = controlledArmiesView.getInitiatedBy();
		if (army.getUnits().size() == 0) {
			new ErrorPopUpWindow("Cannot send an empty army!!");
		} else if (army.getCurrentLocation().equals(target)) {
			controlledArmiesView.setVisible(false);
			new ErrorPopUpWindow("You are already at this location: " + target);
			targetView.setVisible(true);
		} else if (army.getCurrentStatus() == Status.MARCHING) {
			new ErrorPopUpWindow("Cannot set a target while the army is marching; Distance to target: "
					+ army.getDistancetoTarget());
		} else {
			theConquerer.targetCity(army, target);
			controlledArmiesView.updateArmyStatus(indexOfArmy, "Marching");
		}
		controlledArmiesView.disableArmies();
	}

	private void setAccessable(String s) {
		switch (s) {
		case "choseRome":
			cityViews.get(0).setAccessable(true);
			((WorldMapView) view.getPanels().get(2)).getRome().setEnabled(true);
			break;
		case "choseSparta":
			cityViews.get(1).setAccessable(true);
			((WorldMapView) view.getPanels().get(2)).getSparta().setEnabled(true);
			break;
		case "choseCairo":
			cityViews.get(2).setAccessable(true);
			((WorldMapView) view.getPanels().get(2)).getCairo().setEnabled(true);
			break;
		}
	}

	private static Clip getCitySound(String cityName) {
		String path = "sounds/";
		switch (cityName) {
		case "choseRome":
			path += "Its' a me Mario.wav";
			break;
		case "choseSparta":
			path += "This Is Spartaa.wav";
			break;
		case "choseCairo":
			path += "arkab_audio.wav";
			break;
		}
		AudioInputStream audioInputStream;
		Clip clip = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clip.start();
		return clip;
	}

	public int getCity(String cityName) {
		for (int i = 0; i < this.theConquerer.getPlayer().getControlledCities().size(); i++)
			if (this.theConquerer.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase(cityName))
				return i;
		return -1;
	}

	public void loadChecklist(JPanel checklist, ArrayList<Unit> units, String type) {
		checklist.removeAll();
		checklist.repaint();
		checklist.revalidate();
		int noOfSpaces = 0;
		switch (type) {
		case "Archer":
			noOfSpaces = 3;
			break;
		case "Cavalry":
			noOfSpaces = 2;
			break;
		case "Infantry":
			noOfSpaces = 1;
			break;
		}
		for (int j = 0; j < noOfSpaces; j++)
			type += " ";

		for (int i = 0; i < units.size(); i++) {
			JCheckBox unit = new JCheckBox(type + (i + 1));
			unit.addItemListener(this);
			checklist.add(unit);
		}
	}

	public void units_view_activation(ActionEvent e) {
		City currentCity;
		Army army = null;
		if (unitsView != null && unitsView.getArmyIndex() != -1)
			army = theConquerer.getPlayer().getControlledArmies().get(unitsView.getArmyIndex());
		switch (e.getActionCommand()) {
		case "viewArcher":
			unitsInfo.setType("Archer");
			currentCity = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getText()));
			if (army != null)
				this.loadChecklist(unitsInfo.getCheckList(), army.getUnitOfType("Archer"), "Archer");
			else
				this.loadChecklist(unitsInfo.getCheckList(), currentCity.getDefendingArmy().getUnitOfType("Archer"),
						"Archer");
			unitsInfo.setVisible(true);
			break;
		case "viewCavalry":
			unitsInfo.setType("Cavalry");
			currentCity = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getText()));
			if (army != null)
				this.loadChecklist(unitsInfo.getCheckList(), army.getUnitOfType("Cavalry"), "Cavalry");
			else
				this.loadChecklist(unitsInfo.getCheckList(), currentCity.getDefendingArmy().getUnitOfType("Cavalry"),
						"Cavalry");
			unitsInfo.setVisible(true);
			break;
		case "viewInfantry":
			unitsInfo.setType("Infantry");
			currentCity = theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getText()));
			if (army != null)
				this.loadChecklist(unitsInfo.getCheckList(), army.getUnitOfType("Infantry"), "Infantry");
			else
				this.loadChecklist(unitsInfo.getCheckList(), currentCity.getDefendingArmy().getUnitOfType("Infantry"),
						"Infantry");
			unitsInfo.setVisible(true);
			break;
		}
		unitsView.revalidate();
		unitsView.repaint();
	}

	public void city_view_activation(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Rome":
			this.cityViews.get(0).setVisible(true);
			selectedCity = ((JButton) e.getSource());
			break;
		case "Sparta":
			this.cityViews.get(1).setVisible(true);
			selectedCity = ((JButton) e.getSource());
			break;
		case "Cairo":
			this.cityViews.get(2).setVisible(true);
			selectedCity = ((JButton) e.getSource());
			break;
		}
	}

	public void City_Buttons_View(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "EBuilding":
			EBuildingView.setVisible(true);
			break;
		case "MBuildingView":
			MBuildingView.setVisible(true);
			break;
		case "Defending Army":
			unitsView.setVisible(true);
			unitsView.setArmyIndex(-1);
			unitsInfo.setIndexOfArmy(-1);
		}
		if (controlledArmiesView != null) {
			if (controlledArmiesView.getArmies().contains((JButton) e.getSource())
					&& controlledArmiesView.getInitiatedBy().equals("ViewUnits")) {
				controlledArmiesView.setVisible(false);
				unitsView.setArmyIndex(Integer.parseInt("" + e.getActionCommand().substring(12, 13)) - 1);
				unitsInfo.setIndexOfArmy(Integer.parseInt("" + e.getActionCommand().substring(12, 13)) - 1);
				unitsView.setVisible(true);
			}
		}
	}

	public void open_building_info(ActionEvent e) { // this is just to open the views of the various buildings we have.
		switch (e.getActionCommand()) {
		case "Farm": {
			int index_in_building_array = index_of_Building("Farm");
			if (index_in_building_array == -1) {
				selectedBuilding = (JButton) e.getSource();
				BuildButton.getCost().setText("" + this.theConquerer.getPlayer().getCost("Farm"));
				BuildButton.setVisible(true);
				break;
			}
			Farm f = (Farm) this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
					.get(index_in_building_array);
			selectedBuilding = (JButton) e.getSource();
			EBuildingInfo.getBuildingName().setText("Farm");
			EBuildingInfo.getBuildingPic().setIcon(new ImageIcon("images/Farm.jpg"));
			EBuildingInfo.getUpgradecost().setText("" + f.getUpgradeCost());
			EBuildingInfo.getLevel().setText("" + f.getLevel());
			EBuildingInfo.setVisible(true);
			break;
		}
		case "Market": {
			int index_in_building_array = index_of_Building("Market");
			if (index_in_building_array == -1) {
				selectedBuilding = (JButton) e.getSource();
				BuildButton.getCost().setText("" + this.theConquerer.getPlayer().getCost("Market"));
				BuildButton.setVisible(true);
				break;
			}
			Market m = (Market) this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
					.get(index_in_building_array);
			selectedBuilding = (JButton) e.getSource();
			EBuildingInfo.getBuildingName().setText("Market");
			EBuildingInfo.getBuildingPic().setIcon(new ImageIcon("images/Market.jpg"));
			EBuildingInfo.getUpgradecost().setText("" + m.getUpgradeCost());
			EBuildingInfo.getLevel().setText("" + m.getLevel());
			EBuildingInfo.setVisible(true);
			break;
		}
		case "Stable": {
			int index_in_building_array = index_of_Building("Stable");
			if (index_in_building_array == -1) {
				selectedBuilding = (JButton) e.getSource();
				BuildButton.getCost().setText("" + this.theConquerer.getPlayer().getCost("Stable"));
				BuildButton.setVisible(true);
				break;
			}
			Stable S = (Stable) this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings().get(index_in_building_array);
			selectedBuilding = (JButton) e.getSource();
			MBuildingInfo.getBuildingName().setText("Stable");
			MBuildingInfo.getBuildingPic().setIcon(new ImageIcon("images/Stable.jpg"));
			MBuildingInfo.getUpgradecost().setText("" + S.getUpgradeCost());
			MBuildingInfo.getLevel().setText("" + S.getLevel());
			MBuildingInfo.getUpgradecost().setText("" + S.getUpgradeCost());
			MBuildingInfo.getRecruitmentcost().setText("" + S.getRecruitmentCost());
			MBuildingInfo.setVisible(true);
			break;
		}
		case "Archery Range": {
			int index_in_building_array = index_of_Building("ArcheryRange");
			if (index_in_building_array == -1) {
				selectedBuilding = (JButton) e.getSource();
				BuildButton.getCost().setText("" + this.theConquerer.getPlayer().getCost("ArcheryRange"));
				BuildButton.setVisible(true);
				break;
			}
			ArcheryRange a = (ArcheryRange) this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings().get(index_in_building_array);
			selectedBuilding = (JButton) e.getSource();
			MBuildingInfo.getBuildingName().setText("Archery Range");
			MBuildingInfo.getBuildingPic().setIcon(new ImageIcon("images/Archery Range.jpg"));
			MBuildingInfo.getUpgradecost().setText("" + a.getUpgradeCost());
			MBuildingInfo.getLevel().setText("" + a.getLevel());
			MBuildingInfo.getUpgradecost().setText("" + a.getUpgradeCost());
			MBuildingInfo.getRecruitmentcost().setText("" + a.getRecruitmentCost());
			MBuildingInfo.setVisible(true);
			break;
		}
		case "Barracks": {
			int index_in_building_array = index_of_Building("Barracks");
			if (index_in_building_array == -1) {
				// playSound("sounds/OH NOOOO.wav");
				selectedBuilding = (JButton) e.getSource();
				BuildButton.getCost().setText("" + this.theConquerer.getPlayer().getCost("Barracks"));
				BuildButton.setVisible(true);
				break;
			}
			Barracks b = (Barracks) this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings().get(index_in_building_array);
			selectedBuilding = (JButton) e.getSource();
			MBuildingInfo.getBuildingName().setText("Barracks");
			MBuildingInfo.getBuildingPic().setIcon(new ImageIcon("images/Barracks.jpg"));
			MBuildingInfo.getUpgradecost().setText("" + b.getUpgradeCost());
			MBuildingInfo.getLevel().setText("" + b.getLevel());
			MBuildingInfo.getUpgradecost().setText("" + b.getUpgradeCost());
			MBuildingInfo.getRecruitmentcost().setText("" + b.getRecruitmentCost());
			MBuildingInfo.setVisible(true);
			break;
		}
		}
	}

	public void Initiate_Building(ActionEvent e) {
		if (e.getActionCommand().equals("Build")) {
			switch (selectedBuilding.getActionCommand()) {
			case "Farm":
				try {
					this.theConquerer.getPlayer().build("Farm", this.selectedCity.getActionCommand());
					this.BuildButton.setVisible(false);
					playSound("sounds/Construction.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Market":
				try {
					this.theConquerer.getPlayer().build("Market", this.selectedCity.getActionCommand());
					this.BuildButton.setVisible(false);
					playSound("sounds/Construction.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Stable":
				try {
					this.theConquerer.getPlayer().build("Stable", this.selectedCity.getActionCommand());
					this.BuildButton.setVisible(false);
					playSound("sounds/Construction.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Archery Range":
				try {
					this.theConquerer.getPlayer().build("ArcheryRange", this.selectedCity.getActionCommand());
					this.BuildButton.setVisible(false);
					playSound("sounds/Construction.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Barracks":
				try {
					this.theConquerer.getPlayer().build("Barracks", this.selectedCity.getActionCommand());
					this.BuildButton.setVisible(false);
					playSound("sounds/Construction.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			}
		}
	}

	public void Upgrade_Building(ActionEvent e) {
		if (e.getActionCommand().equals("Upgrade")) {
			switch (selectedBuilding.getActionCommand()) {
			case "Farm":
				try {
					this.theConquerer.getPlayer()
							.upgradeBuilding(this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
									.get(index_of_Building("Farm")));
					EBuildingInfo.getUpgradecost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
									.get(index_of_Building("Farm")).getUpgradeCost());
					EBuildingInfo.getLevel()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
									.get(index_of_Building("Farm")).getLevel());
					EBuildingInfo.repaint();
					EBuildingInfo.revalidate();
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxLevelException t) {
					playSound("sounds/God Like - Sound Effect.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Market":
				try {
					this.theConquerer.getPlayer()
							.upgradeBuilding(this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
									.get(index_of_Building("Market")));
					EBuildingInfo.getUpgradecost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
									.get(index_of_Building("Market")).getUpgradeCost());
					EBuildingInfo.getLevel()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings()
									.get(index_of_Building("Market")).getLevel());
					EBuildingInfo.repaint();
					EBuildingInfo.revalidate();
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxLevelException t) {
					playSound("sounds/God Like - Sound Effect.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Archery Range":
				try {
					this.theConquerer.getPlayer()
							.upgradeBuilding(this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("ArcheryRange")));
					MBuildingInfo.getUpgradecost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("ArcheryRange")).getUpgradeCost());
					MBuildingInfo.getLevel()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("ArcheryRange")).getLevel());
					MBuildingInfo.getRecruitmentcost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("ArcheryRange")).getRecruitmentCost());
					MBuildingInfo.repaint();
					MBuildingInfo.revalidate();
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxLevelException t) {
					playSound("sounds/God Like - Sound Effect.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Barracks":
				try {
					this.theConquerer.getPlayer()
							.upgradeBuilding(this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Barracks")));
					MBuildingInfo.getUpgradecost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Barracks")).getUpgradeCost());
					MBuildingInfo.getLevel()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Barracks")).getLevel());
					MBuildingInfo.getRecruitmentcost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Barracks")).getRecruitmentCost());
					MBuildingInfo.repaint();
					MBuildingInfo.revalidate();
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxLevelException t) {
					playSound("sounds/God Like - Sound Effect.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Stable":
				try {
					this.theConquerer.getPlayer()
							.upgradeBuilding(this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Stable")));
					MBuildingInfo.getUpgradecost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Stable")).getUpgradeCost());
					MBuildingInfo.getLevel()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Stable")).getLevel());
					MBuildingInfo.getRecruitmentcost()
							.setText("" + this.theConquerer.getPlayer().getControlledCities()
									.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings()
									.get(index_of_Building("Stable")).getRecruitmentCost());
					MBuildingInfo.repaint();
					MBuildingInfo.revalidate();
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxLevelException t) {
					playSound("sounds/God Like - Sound Effect.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			}
		}
	}

	public void Recruit_From_Building(ActionEvent e) {
		if (e.getActionCommand().equals("Recruit")) {
			switch (selectedBuilding.getActionCommand()) {
			case "Archery Range":
				try {
					this.theConquerer.getPlayer().recruitUnit("Archer", this.theConquerer.getPlayer()
							.getControlledCities().get(getCity(selectedCity.getActionCommand())).getName());
					playSound("sounds/Trumpet.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxRecruitedException t) {
					playSound("sounds/Chill-chill-SOUND-EFFECT.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Stable":
				try {
					this.theConquerer.getPlayer().recruitUnit("Cavalry", this.theConquerer.getPlayer()
							.getControlledCities().get(getCity(selectedCity.getActionCommand())).getName());
					playSound("sounds/Trumpet.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxRecruitedException t) {
					playSound("sounds/Chill-chill-SOUND-EFFECT.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			case "Barracks":
				try {
					this.theConquerer.getPlayer().recruitUnit("Infantry", this.theConquerer.getPlayer()
							.getControlledCities().get(getCity(selectedCity.getActionCommand())).getName());
					playSound("sounds/Trumpet.wav");
				} catch (NotEnoughGoldException t) {
					playSound("sounds/OH NOOOO.wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (BuildingInCoolDownException t) {
					playSound("sounds/JEEZ! Jesus Christ (Vine Meme) - Sound Effect for editing (mp3cut.net).wav");
					new ErrorPopUpWindow(t.getMessage());
				} catch (MaxRecruitedException t) {
					playSound("sounds/Chill-chill-SOUND-EFFECT.wav");
					new ErrorPopUpWindow(t.getMessage());
				}
				break;
			}
		}
	}

	public int index_of_Building(String type) { // this simply inform me whether or not the building exists
		switch (type) {
		case "Farm":
			for (int i = 0; i < this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings().size(); i++)
				if (this.theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getActionCommand()))
						.getEconomicalBuildings().get(i) instanceof Farm)
					return i;
			return -1;
		case "Market":
			for (int i = 0; i < this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getEconomicalBuildings().size(); i++)
				if (this.theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getActionCommand()))
						.getEconomicalBuildings().get(i) instanceof Market)
					return i;
			return -1;
		case "Stable":
			for (int i = 0; i < this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings().size(); i++)
				if (this.theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getActionCommand()))
						.getMilitaryBuildings().get(i) instanceof Stable)
					return i;
			return -1;
		case "ArcheryRange":
			for (int i = 0; i < this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings().size(); i++)
				if (this.theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getActionCommand()))
						.getMilitaryBuildings().get(i) instanceof ArcheryRange)
					return i;
			return -1;
		case "Barracks":
			for (int i = 0; i < this.theConquerer.getPlayer().getControlledCities()
					.get(getCity(selectedCity.getActionCommand())).getMilitaryBuildings().size(); i++)
				if (this.theConquerer.getPlayer().getControlledCities().get(getCity(selectedCity.getActionCommand()))
						.getMilitaryBuildings().get(i) instanceof Barracks) {
					return i;
				}
			return -1;
		}
		return -1;
	}

	public void playSound(String filePath) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private void initiateControlledArmiesView(String initiatedBy) {
		controlledArmiesView = new ControlledArmies(theConquerer.getPlayer().getControlledArmies());
		controlledArmiesView.enableArmies();
		controlledArmiesView.setInitiatedBy(initiatedBy);
		for (int i = 0; i < controlledArmiesView.getArmies().size(); i++) {
			controlledArmiesView.getArmies().get(i).addActionListener(this);
		}
		controlledArmiesView.getInitiateArmy().addActionListener(this);
		controlledArmiesView.getDefendingArmy().addActionListener(this);
		if (initiatedBy.equals("Relocate")) {
			controlledArmiesView.getInitiateArmy().setEnabled(true);
			controlledArmiesView.getDefendingArmy().setEnabled(true);
		}
	}

	public Army getArmyByLocation(JButton location, ArrayList<Army> c) {
		Army a = null;
		for (int i = 0; i < c.size(); i++)
			if (c.get(i).getCurrentLocation() == location.getName())
				return a;
		return a;

	}

	public void End_Turn(ActionEvent e) {
		if (e.getActionCommand().equals("End Turn")) {
			this.EBuildingInfo.setVisible(false);
			this.EBuildingView.setVisible(false);
			this.MBuildingInfo.setVisible(false);
			this.MBuildingView.setVisible(false);
			this.NextAction.setVisible(false);
			this.targetView.setVisible(false);
			this.unitsView.setVisible(false);
			this.unitsInfo.setVisible(false);
			if (battleView != null)
				battleView.setVisible(false);
			if (controlledArmiesView != null)
				this.controlledArmiesView.setVisible(false);
			this.PlayerProfileView.setVisible(false); // only works if only the latest view of it was initalized
			for (int i = 0; i < this.cityViews.size(); i++)
				this.cityViews.get(i).setVisible(false);
			System.out.println(target_is_reached);
			if (Exeeded_max_seige_Turn() == -1 && !target_is_reached) {// && this.theConquerer.isFinished_Battle()
				this.theConquerer.endTurn();
				for (int i = 0; i < this.theConquerer.getAvailableCities().size(); i++)
					if (this.theConquerer.getAvailableCities().get(i).isUnderSiege()) {
						this.NextAction.getRetreat().setEnabled(false);
						this.NextAction.getLaySeige().setEnabled(false);
						this.NextAction.getManualAttack().setEnabled(false);
						this.NextAction.setVisible(true);
						this.NextAction.getText().setText("You are still sieging");
						this.NextAction.getRetreat().setEnabled(true);
						this.NextAction.getLaySeige().setEnabled(false);
						this.NextAction.getManualAttack().setEnabled(true);
					}
				boolean ishome = false;
				for (int i = 0; i < this.theConquerer.getPlayer().getControlledArmies().size(); i++) {
					if (this.theConquerer.getPlayer().getControlledArmies().get(i) != null
							&& theConquerer.getPlayer().getControlledArmies().get(i).getDistancetoTarget() == 0
							&& this.theConquerer.getPlayer().getControlledArmies().get(i).isTragetReached() == true) {
						this.theConquerer.getPlayer().getControlledArmies().get(i).setTragetReached(false);

						for (int k = 0; k < this.theConquerer.getPlayer().getControlledCities().size(); k++)
							if (this.attaking_army != null && this.attaking_army.getCurrentLocation().equalsIgnoreCase(
									this.theConquerer.getPlayer().getControlledCities().get(k).getName()))
								ishome = true;
						System.out.println(ishome + "2");
						System.out.println(this.theConquerer.getPlayer().getControlledCities().size());
						// if (this.target_is_reached
						// &&this.attaking_army.getCurrentLocation().equalsIgnoreCase(this.theConquerer.getPlayer().getControlledCities().get(i).getName())
						// ) {//&&
						// this.theConquerer.getPlayer().getControlledArmies().get(i).isWasMarching() ==
						// true
						if (ishome && this.theConquerer.getPlayer().getControlledCities().size() != 2) {
							System.out.println("youre home");
							playSound("sounds/yt1s.com - Marhab Ya Hilal.wav");
							new ErrorPopUpWindow("Army has reached home successfully");
							this.theConquerer.getPlayer().getControlledArmies().get(i).setWasMarching(false);
						} else {
							this.NextAction.getRetreat().setEnabled(false);
							this.NextAction.getLaySeige().setEnabled(false);
							this.NextAction.getManualAttack().setEnabled(false);
							this.NextAction.setVisible(true);
							this.NextAction.getText().setText("You have reached your target");
							this.NextAction.getManualAttack().setEnabled(true);
							this.NextAction.getLaySeige().setEnabled(true);
							this.NextAction.setInitiator(this.theConquerer.getPlayer().getControlledArmies().get(i));
							this.target_is_reached = true;
							this.theConquerer.setFinished_Battle(false);
							this.attaking_army = this.theConquerer.getPlayer().getControlledArmies().get(i);
						}
					}
				}
			} else {
				if (target_is_reached && !this.seiging) {// && !this.theConquerer.isFinished_Battle()
					playSound("sounds/Balabizo.wav");
					this.NextAction.getRetreat().setEnabled(false);
					this.NextAction.getLaySeige().setEnabled(false);
					this.NextAction.getManualAttack().setEnabled(false);
					this.NextAction.setVisible(true);
					this.NextAction.getText().setText("You have reached your target");
					this.NextAction.getManualAttack().setEnabled(true);
					this.NextAction.getLaySeige().setEnabled(true);
					this.NextAction.setInitiator(this.attaking_army);
				} else {
					if (this.seiging && Exeeded_max_seige_Turn() != -1) {
						playSound("sounds/Balabizo.wav");
						NextAction.setVisible(true);
						this.NextAction.getRetreat().setEnabled(false);
						this.NextAction.getLaySeige().setEnabled(false);
						this.NextAction.getManualAttack().setEnabled(false);
						this.NextAction.getManualAttack().setEnabled(true);
						this.NextAction.setInitiator(this.theConquerer.getPlayer().getControlledArmies()
								.get(this.theConquerer.getPlayer().getControlledArmies().indexOf(this.attaking_army)));
						this.NextAction.getText()
								.setText("You have seiged a city for 3 turns you need to take an action");
						this.NextAction.getLaySeige().setEnabled(false);
						this.NextAction.getRetreat().setEnabled(true);
					}
				}
			}
		}
	}

	public int Exeeded_max_seige_Turn() {
		for (int i = 0; i < this.theConquerer.getAvailableCities().size(); i++) {
			if (this.theConquerer.getAvailableCities().get(i).isUnderSiege()
					&& this.theConquerer.getAvailableCities().get(i).getTurnsUnderSiege() == 3)
				return i;
		}
		return -1;
	}

	private void initiateBattleView(Army attackingArmy) {
		Army defendingArmy = null;
		for (int i = 0; i < theConquerer.getAvailableCities().size(); i++) {
			if (attackingArmy.getCurrentLocation().equals(theConquerer.getAvailableCities().get(i).getName())) {
				defendingArmy = theConquerer.getAvailableCities().get(i).getDefendingArmy();
			}
		}
		battleView = new BattleView(defendingArmy, attackingArmy);
		battleView.getAttack().addActionListener(this);
		battleView.getAuto().addActionListener(this);
		for (int i = 0; i < battleView.getDefendingUnits().size(); i++) {
			battleView.getDefendingUnits().get(i).addActionListener(this);
		}
		for (int i = 0; i < battleView.getPlayerUnits().size(); i++) {
			battleView.getPlayerUnits().get(i).addActionListener(this);
		}
		battleView.setVisible(true);
	}

	public void onDeadUnit(Unit deadUnit) {
		if (battleView.getAttackingArmy() == (deadUnit.getParentArmy())) {
			battleView.updateBattleLog("\n Attacker's unit is dead!!");
			for (int i = 0; i < 256; i++) {
				battleView.getAttackerUnit().setBackground(new Color(255, i, i));
			}
			battleView.removeFromPPlayerArmy(battleView.getAttackerUnit());
			battleView.setPlayerUnits();
			if (battleView.getPlayerUnits().size() == 0)
				onBattleResult(false);
		} else {
			battleView.updateBattleLog("\n Defender's unit is dead");
			for (int i = 0; i < 256; i++)
				battleView.getDefenderUnit().setBackground(new Color(i, i, 255));
			battleView.removeFromPDefendingArmy(battleView.getDefenderUnit());
			battleView.setDefendingUnits();
			if (battleView.getDefendingUnits().size() == 0)
				onBattleResult(true);
		}
	}

	public void onGetDead(int dead, Unit target) {
		if (battleView.getAttackingArmy().getUnits().contains(target)) {
			battleView.updateBattleLog("\n Attacker's unit lost " + dead + " soldiers");
		} else {
			battleView.updateBattleLog("\n Defender's unit lost " + dead + " soldiers");
		}
	}

	public void onBattleResult(boolean result) {
		String cityName = "";
		City c = null;
		for (int i = 0; i < theConquerer.getAvailableCities().size(); i++)
			if (theConquerer.getAvailableCities().get(i).getDefendingArmy() == battleView.getDefendingArmy()) {
				cityName = theConquerer.getAvailableCities().get(i).getName();
				c = theConquerer.getAvailableCities().get(i);
			}
		if (result) {

			theConquerer.occupy(battleView.getAttackingArmy(), cityName);
			battleView.updateBattleLog("\nThe attacking army won!! You now control: " + cityName);
			switch (cityName) {
			case "Rome":
				((WorldMapView) view.getPanels().get(2)).getRome().setEnabled(true);
				break;
			case "Sparta":
				((WorldMapView) view.getPanels().get(2)).getSparta().setEnabled(true);
				break;
			case "Cairo":
				((WorldMapView) view.getPanels().get(2)).getCairo().setEnabled(true);
				break;
			}
		} else {
			c.setTurnsUnderSiege(-1);
			c.setUnderSiege(true);
			battleView.updateBattleLog("\nThe attacking army has been defeated...");
		}
		theConquerer.getPlayer().getControlledArmies().remove(battleView.getAttackingArmy());
		battleView.disableAll();
	}

	private void initiateCounterAttack() {

		battleView.getDefenderUnit().setBackground(Color.WHITE);
		battleView.getAttackerUnit().setBackground(Color.WHITE);
		battleView.setAttackerUnit(null);
		battleView.setDefenderUnit(null);
		int attackerUnitIndex = (int) ((Math.random() * battleView.getDefendingUnits().size()));
		int defenderUnitIndex = (int) ((Math.random() * battleView.getPlayerUnits().size()));
		battleView.setAttackerUnit(battleView.getPlayerUnits().get(defenderUnitIndex));
		battleView.getAttackerUnit().setBackground(Color.RED);
		battleView.setDefenderUnit(battleView.getDefendingUnits().get(attackerUnitIndex));
		battleView.getDefenderUnit().setBackground(Color.BLUE);
		Unit attacker = battleView.getDefendingUnit();
		Unit defender = battleView.getAttackingUnit();
		attacker.setUnitListener(this);
		defender.setUnitListener(this);
		try {
			attacker.attack(defender);
		} catch (FriendlyFireException e) {
		}
		battleView.getDefenderUnit().setBackground(Color.WHITE);
		battleView.getAttackerUnit().setBackground(Color.WHITE);
		battleView.setAttackerUnit(null);
		battleView.setDefenderUnit(null);
		battleView.enableAll();
	}
	public boolean Friendly_Fire_Exception(ActionEvent e) {
		for(int i=0;i<this.theConquerer.getPlayer().getControlledCities().size();i++) {
			if(this.theConquerer.getPlayer().getControlledCities().get(i).getName().equalsIgnoreCase(((JButton) e.getSource()).getText()))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Controller gameController = new Controller();
	}
}