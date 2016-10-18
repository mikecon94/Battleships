package com.futuresailors.battleships.controller;

import java.awt.Point;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.model.Tile;
import com.futuresailors.battleships.view.PlaceShipsListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;

/**
 * This controller adds a PlaceShipsPanel to the window and is used
 * to allow users to choose the tiles they wish to place their
 * ships in.
 * @author Michael Conroy
 *
 */
public class PlaceShipsController {
	
	private JFrame window;
	private PlaceShipsPanel panel;
	
	//Creates All Possible Ships - Array
	Ship destroyer = new Ship(3,1,"src/main/resources/background2.jpg");
	
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
	
	public void mouseClicked(int x, int y){
		//This method will check whether a ship has been clicked
		//If so then it call getTile(x, y) on the panel to determine what
		//tile it should be placed on then place it there if possible.
		//If not then it call getShip(x, y) which will return the ship that has
		//been clicked, the controller then knows to select it.
		
		//Both of these methods will return -1 or some other relevant value
		//if the mouse click wasn't on a tile / ship.
		
		//Check if ship being placed for first time
		//Check what type of ship is being placed if destroyer do the below
		
		destroyer.createTiles(new Point(panel.getTileXUnderMouse(x),panel.getTileYUnderMouse(y)));
		Tile tiles[] = destroyer.getTiles();
		for(Tile tile : tiles){
			System.out.println("X: " + tile.getPosition().x);
			System.out.println("Y: " + tile.getPosition().y);
		}
	}
	
	/**
	 * Called by the panel listener when the mouse is moved. Will be used
	 * for tile hovering.
	 * @param x - X Coordinate the mouse is now in.
	 * @param y - Y Coordinate the mouse is now in.
	 */
	public void mouseMoved(int x, int y){
		panel.hoverTile(x, y);
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