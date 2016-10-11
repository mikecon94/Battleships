package com.futuresailors.battleships.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.futuresailors.battleships.controller.BattleShipsController;

public class MainMenuListener {

	private BattleShipsController controller;
	private JPanel panel;
	
	public MainMenuListener(JPanel panel, BattleShipsController controller){
		this.controller = controller;
		this.panel = panel;
		addListeners();
	}
	
	/*
	 * Adds the appropriate mouse listeners to the buttons on the Menu Screen.
	 * When clicked it will alert the controller to then perform the
	 * appropriate action.
	 */
	private void addListeners(){
		//0 is the Start Game Button.
		panel.getComponent(0).addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//Check which button was clicked:
				//1 = Start Game
				System.out.println("Start Game Clicked.");
				controller.startSinglePlayer();
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
		});
		
		//1 is the Start Game Button.
		panel.getComponent(1).addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//Check which button was clicked:
				//1 = Start Game
				System.out.println("Exit Game Clicked.");
				controller.exit();
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
		});
	}
	
}
