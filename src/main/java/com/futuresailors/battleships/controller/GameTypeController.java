package com.futuresailors.battleships.controller;

/**
 * An interface that will be used by the controllers that allow the game to
 * be started.
 * @author Michael Conroy
 */
public interface GameTypeController extends Controller{
	
	/**
	 * Will be called by the PlaceShipsController to display the game window and start the actual game.
	 */
	public void startGame();
}
