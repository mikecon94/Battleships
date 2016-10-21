package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
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
	//Maybe make this configurable going forwards
	//Pass the number of possible ships in constructor.
	//Maybe it should be in a model called ShipsHandler (?)
	//That handles placing of the ships amongst other things.
	private Ship[] placeableShips = new Ship[4];
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
		createPlaceableShips();
		panel.updateCurrentShip(placeableShips[currentShip]);
	}
	
	private void createPlaceableShips(){
		placeableShips[0] = new Ship(4, 1, "src/main/resources/images/ships/1.png");
		placeableShips[1] = new Ship(3, 1, "src/main/resources/images/ships/2.png");
		placeableShips[2] = new Ship(1, 1, "src/main/resources/images/ships/3.png");
		placeableShips[3] = new Ship(1, 5, "src/main/resources/images/ships/5.png");
	}
	
	private void addPanel(){
		window.getContentPane().removeAll();
		panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight(), grid);
		window.add(panel);
		window.repaint();
		PlaceShipsListener listener = new PlaceShipsListener(panel, this);
	}
	
	public void mouseClicked(int x, int y){
		if(!allShipsPlaced){
			//Both of these methods will return -1 or some other relevant value
			//if the mouse click wasn't on a tile / ship.
			if(grid.checkValidPlace(panel.getTileXUnderMouse(x), panel.getTileYUnderMouse(y), placeableShips[currentShip])){
				grid.placeShip(panel.getTileXUnderMouse(x), panel.getTileYUnderMouse(y), placeableShips[currentShip]);
				currentShip++;
				if(currentShip == placeableShips.length){
					allShipsPlaced = true;
				}
				panel.repaint();
			}
		}
				
		//Check if ship being placed for first time
		//Check what type of ship is being placed if destroyer do the below
//		Ship test = new Ship(1, 1, "src/main/resources/grid.png");
//		test.createTiles(new Point(panel.getTileXUnderMouse(x),panel.getTileYUnderMouse(y)));
//		Tile tiles[] = test.getTiles();
//		for(Tile tile : tiles){
//			System.out.println("X: " + tile.getPosition().x + " Y: " + tile.getPosition().y);
//		}
	}
	
	/**
	 * Called by the panel listener when the mouse is moved. Will be used
	 * for tile hovering.
	 * @param x - X Coordinate the mouse is now in.
	 * @param y - Y Coordinate the mouse is now in.
	 */
	public void mouseMoved(int x, int y){
		if(!allShipsPlaced){
			if(panel.overGridSpace(x, y)){
				grid.hover(panel.getTileXUnderMouse(x), panel.getTileYUnderMouse(y), placeableShips[currentShip]);
			} else {
				grid.clearHoverTiles();
			}
		} else {
			grid.clearHoverTiles();
		}
		panel.repaint();
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