package com.futuresailors.battleships.controller;

import java.awt.Point;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;

/**
 * This controller adds a PlaceShipsPanel to the window and is used
 * to allow users to choose the tiles they wish to place their
 * ships in.
 * @author Michael Conroy
 */
public class PlaceShipsController implements Controller{
	
	private JFrame window;
	private PlaceShipsPanel panel;
	//Maybe make this configurable going forwards
	//Pass the number of possible ships in constructor.
	//Maybe it should be in a model called ShipsHandler (?)
	//That handles placing of the ships amongst other things.
	private Ship[] ships = new Ship[5];
	private int currentShip = 0;
	private Grid grid;
	private boolean allShipsPlaced = false;
	
	/**
	 * Creates the panel, adds it to the window and adds the listener.
	 * @param window - The JFrame to add the panel to.
	 */
	public PlaceShipsController(JFrame window){
		this.window = window;
		grid = new Grid(10);
		addPanel();
		createships();
		panel.updateCurrentShip(currentShip);
	}
	
	/**
	 * Defines the ships that will be used in this game.
	 */
	//TODO Move this into the game type controller.
	private void createships(){
		ships[0] = new Ship(5, 1, "src/main/resources/images/ships/1.png");
		ships[1] = new Ship(4, 1, "src/main/resources/images/ships/2.png");
		ships[2] = new Ship(3, 1, "src/main/resources/images/ships/3.png");
		ships[3] = new Ship(1, 3, "src/main/resources/images/ships/5.png");
		ships[4] = new Ship(1, 2, "src/main/resources/images/ships/5.png");
	}
	
	/**
	 * Adds the view to the window to allow the user to interact with
	 * this controller.
	 */
	private void addPanel(){
		window.getContentPane().removeAll();
		panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight(), grid, ships);
		window.add(panel);
		window.repaint();
		@SuppressWarnings("unused")
		GameListener listener = new GameListener(panel, this);
	}
	
	/**
	 * Called by the listener to tell the mouse has been clicked.
	 * If all the ships have been placed then the user can click anywhere and move onto the actual gameplay itself.
	 * If the ships haven't been placed it will check whether the position clicked in is a valid tile to place
	 * a ship and if so it will place it, move onto the next ship, and update the view.
	 */
	public void mouseClicked(Point pos){
		if(!allShipsPlaced){
			Point newPos = new Point(panel.getTileXUnderMouse(pos.x), panel.getTileYUnderMouse(pos.y));
			if(grid.checkValidPlace(newPos, ships[currentShip])){
				grid.placeShip(newPos, ships[currentShip]);
				currentShip++;
				if(currentShip == ships.length){
					allShipsPlaced = true;
				}
				panel.updateCurrentShip(currentShip);
			}
		} else {
			//Move onto the Main Game
			@SuppressWarnings("unused")
			SinglePlayerController game = new SinglePlayerController(grid, ships, window);
		}
	}
	
	/**
	 * Called by the listener when the mouse is moved. Detects the state of the board and if all the ships haven't
	 * been placed it will display what tiles the ship will take up if the user clicks to place it.
	 */
	public void mouseMoved(Point pos){
		if(!allShipsPlaced){
			if(panel.overGridSpace(pos.x, pos.y)){
				Point newPos = new Point(panel.getTileXUnderMouse(pos.x), panel.getTileYUnderMouse(pos.y));
				grid.hover(newPos, ships[currentShip]);
			} else {
				grid.clearHoverTiles();
			}
			//Repaint the panel since the model has been updated.
			panel.repaint();
		}
	}
	
	/**
	 * Returns the user to the Main Menu by passing creating a new MainMenuController and calling the showMenu
	 * method.
	 */
	public void returnToMenu(){
		MainMenuController main = new MainMenuController(window);
		main.showMenu();
	}
}