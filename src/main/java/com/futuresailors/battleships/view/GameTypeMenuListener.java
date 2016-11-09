package com.futuresailors.battleships.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.controller.GameTypeMenuController;

public class GameTypeMenuListener implements ActionListener{
	
	private GameTypeMenuController controller;
	private JPanel panel;
	
	public GameTypeMenuListener(JPanel panel, GameTypeMenuController controller){
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
		if("Classic".equals(e.getActionCommand())){
			System.out.println("Start Game Clicked.");
			controller.startClassicMode();
		} else if("Reloaded".equals(e.getActionCommand())){
			System.out.println("Reloaded Mode Clicked");
			controller.startReloadedMode();
		}
	}
}
