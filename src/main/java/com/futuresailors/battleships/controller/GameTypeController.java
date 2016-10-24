package com.futuresailors.battleships.controller;

public interface GameTypeController extends Controller{
	
	/**
	 * Will be called by the PlaceShipsController to display the game window and start the actual game.
	 */
	public void startGame();
}
