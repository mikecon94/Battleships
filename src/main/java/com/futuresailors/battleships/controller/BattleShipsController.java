package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.view.MainMenu;

public class BattleShipsController {
	
	public void start(){
		//Creates the window and adds the menu screen to it.
		MainMenu menu = new MainMenu();
		
		//We can use this to update what is displayed on the window.
		//eg. change to a new panel as the game moves along.
		JFrame window = menu.getWindow();
		
		
	}
}
