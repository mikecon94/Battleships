package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.PlaceShipsListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;

/**
 * This controller adds a PlaceShipsPanel to the window and is used
 * to allow users to chooose the tiles they wish to place their
 * ships in.
 * @author Michael Conroy
 *
 */
public class PlaceShipsController {
	
	private JFrame window;
	private PlaceShipsPanel panel;
	
	/**
	 * Creates the panel, adds it to the window and adds the listener.
	 * @param window - The JFrame to add the panel to.
	 */
	public PlaceShipsController(JFrame window){
		window.getContentPane().removeAll();
		panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight());
		window.add(panel);
		window.repaint();
		PlaceShipsListener listener = new PlaceShipsListener(panel, this);
		this.window = window;
	}
	
	/**
	 * Called by the panel listener when the mouse is moved. Will be used
	 * for tile hovering.
	 * @param newX - X Coordinate the mouse is now in.
	 * @param newY - Y Coordinate the mouse is now in.
	 */
	public void mouseMoved(int newX, int newY){
		panel.hoverTile(newX, newY);
	}
	
	/**
	 * Creates a new controller passing it the window and then tells it
	 * to display the menu again.
	 */
	public void returnToMenu(){
		BattleShipsController main = new BattleShipsController(window);
		main.showMenu();
	}
}