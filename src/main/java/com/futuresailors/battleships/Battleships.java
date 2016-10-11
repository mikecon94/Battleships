package com.futuresailors.battleships;

import com.futuresailors.battleships.controller.BattleShipsController;

/**
 * The entry point to the program which creates a new instance
 * of the BattleShipsController which starts the game.
 * @author Michael Conroy
 * 
 */
public class Battleships {
	public static void main(String[] args){
		BattleShipsController game = new BattleShipsController();
		game.start();
	}
}