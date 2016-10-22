package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.MainPlayListener;
import com.futuresailors.battleships.view.MainPlayPanel;

public class SinglePlayerController {
	
	private JFrame window;
	private MainPlayPanel panel;
	private Ship ships[];
	private Grid oppGrid;
	private Grid myGrid;
	
	public SinglePlayerController(Grid grid, Ship ships[], JFrame window){
		this.ships = ships;
		this.window = window;
		myGrid = grid;
		addPanel();
	}
	
	private void addPanel(){
		window.getContentPane().removeAll();
		panel = new MainPlayPanel(UIHelper.getWidth(), UIHelper.getHeight(), myGrid, myGrid,ships);
		window.add(panel);
		window.repaint();
		MainPlayListener playListener = new MainPlayListener(panel, this);
	}

	public void returnToMenu() {
		MainMenuController main = new MainMenuController(window);
		main.showMenu();
		//We should do some sort of check here to where a popup window prompts the player to ask if they are sure they want to leave the game
	}
}
