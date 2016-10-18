package com.futuresailors.battleships.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.controller.BattleShipsController;

public class MainMenuListener implements ActionListener{

	private BattleShipsController controller;
	private JPanel panel;
	
	public MainMenuListener(JPanel panel, BattleShipsController controller){
		this.controller = controller;
		this.panel = panel;
		addListeners();
	}
	
	/**
	 * Loops round all the components on the main menu panel
	 * and adds this listener to all of the buttons.
	 */
	private void addListeners(){
		Component[] comps = panel.getComponents();
		for(Component comp : comps){
			if(comp instanceof JButton){
				JButton button = (JButton) comp;
				button.addActionListener(this);
			}
		}
	}

	/**
	 * Detects which button has been clicked and performs the appropriate
	 * action.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if("Start Game".equals(e.getActionCommand())){
			System.out.println("Start Game Clicked.");
			controller.startSinglePlayer();
		} else if("Rules".equals(e.getActionCommand())){
			System.out.println("Rules Clicked.");
			controller.showRules();
		} else if("Exit Game".equals(e.getActionCommand())){
			System.out.println("Exit Game Clicked.");
			controller.exit();
		}
	}
}
