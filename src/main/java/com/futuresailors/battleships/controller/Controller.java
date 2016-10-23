package com.futuresailors.battleships.controller;

import java.awt.Point;

/**
 * Implemented by the controllers and used so a single Listener
 * can be used across each of the panels/controllers and call 
 * the appropriate methods.
 * @author Michael Conroy
 */
public interface Controller {
	
	/**
	 * Will return the window to the menu.
	 */
	//TODO Make this an abstract class and give a concrete implementation of this method.
	public void returnToMenu();
	
	/**
	 * Tells the controller the mouse was clicked. This
	 * method will then need to calculate what is appropriate
	 * and update the view accordingly.
	 * @param pos - Position the mouse was clicked.
	 */
	public void mouseClicked(Point pos);
	
	/**
	 * Used by the controller to update the view to display
	 * the mouse hovering over a tile for example.
	 * @param pos - Position the mouse has moved to.
	 */
	public void mouseMoved(Point pos);
	
}
