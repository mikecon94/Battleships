package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.ai.SimpleAI;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.MainPlayListener;
import com.futuresailors.battleships.view.MainPlayPanel;

public class SinglePlayerController {
	
	private JFrame window;
	private MainPlayPanel panel;
	private Ship[] myShips;
	private Grid myGrid;
	private Ship[] oppShips; 
	private Grid oppGrid;

	
	public SinglePlayerController(Grid grid, Ship ships[], JFrame window){
		myShips = ships;
		this.window = window;
		myGrid = grid;
		oppGrid = new Grid(grid.getRows());
		createOppShips();
		SimpleAI opp = new SimpleAI(oppGrid, oppShips);
		opp.placeShips();
		addPanel();
	}
	
	private void createOppShips(){
		oppShips = new Ship[myShips.length];
		//Ensure both players have the same ship types.
		//This should probably be changed eventually so the ship types are defined before place ships
		//by the controller.
		for(int i = 0; i < myShips.length - 1; i++){
			oppShips[i] = new Ship(myShips[i].getWidth(), myShips[i].getHeight(), myShips[i].getImagePath());
		}
	}
	
	private void addPanel(){
		window.getContentPane().removeAll();
		panel = new MainPlayPanel(UIHelper.getWidth(), UIHelper.getHeight(), myGrid, myGrid, myShips);
		window.add(panel);
		window.repaint();
		MainPlayListener listener = new MainPlayListener(panel, this);
	}

	public void returnToMenu() {
		MainMenuController main = new MainMenuController(window);
		main.showMenu();
		//We should do some sort of check here to where a popup window prompts the player to ask if they are sure they want to leave the game
	}
}
