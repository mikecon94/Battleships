package com.futuresailors.battleships.model;

import java.awt.Point;

/**
 * This object represents a ship on the board
 * and its respective tiles. It also contains the ships
 * icons filepath and methods that change the state of the
 * ship in the game.
 * @author Joe Baldwin, Michael Conroy
 *
 */

public class Ship {
	
	private final int WIDTH;
	private final int HEIGHT;
	//Whether the ship has been sunk
	private boolean sunk = false;
	//Tiles the ship occupies
	private ShipTile tiles[];
	private String imagePath;
	//If the ship has been placed on the board already
	private boolean placed = false;
	//Ship location represented as a Point
	Point pos;
	
	//Constructor
	public Ship(int width, int height, String imagePath){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.imagePath = imagePath;
		//Init tile array with the amount of tiles the ship occupys
		tiles = new ShipTile[height*width];
		System.out.println("New Ship Created, Height: " + height + " Width: " + width + " Tiles: " + tiles.length);
	}
	
	/**
	 * Checks if the opponent scored a hit on a ships tile
	 * @param location - A Point object for the tile location that was hit
	 * @return Whether the player has scored a hit as a boolean
	 */
	public boolean hit(Point location) {
		for(ShipTile tile : tiles){
			if(tile.getPosition() == location){
				tile.setHit();
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Checks if any of the tiles have been hit and if they have all been hit returns true
	 * @return Whether ship is sunk as boolean
	 */
	public boolean checkIfSunk(){
		//Loops through each tile
		for(ShipTile tile : tiles){
			//If a tile is not hit
			if(tile.isHit() != true){
				return false;
			}
		}
		//Sets ship to sunk
		sink();
		return true;
	}
	
	/**
	 * Allows the ship to be marked sunk
	 */
	public void sink(){
		sunk = true;
	}
	/**
	 * @return If ship is sunk as boolean
	 */
	public boolean isSunk() {
		return sunk;
	}
	/**
	 * @return If ship is placed as boolean
	 */
	public boolean getPlaced(){
		return placed;
	}
	
	/**
	 * Initialises the tiles array based on the top left hand tile
	 * @param pos - A Point representing the top left hand tile of the ship
	 */
	public void placeShip(Point pos){
		int index = 0;
		for(int i=0; i < HEIGHT; i++){
			for(int j=0; j < WIDTH;j++) {
				Point point = new Point(pos.x+ j,pos.y +i);
				tiles[index] = new ShipTile(point);
				index++;
			}
		}
		placed = true;
		this.pos = pos;
	}
	/**
	 * @return Location as Point
	 */
	public Point getPos(){
		return pos;
	}
	/**
	 * @return X as int
	 */
	public int getX(){
		return (int) pos.getX();
	}
	/**
	 * @return Y as int
	 */
	public int getY(){
		return (int) pos.getY();
	}

	/**
	 * @return Width as int
	 */
	public int getWidth(){
		return WIDTH;
	}
	/**
	 * @return Height as int
	 */
	public int getHeight(){
		return HEIGHT;
	}
	/**
	 * @return imagePath as String
	 */
	public String getImagePath() {
		return imagePath;
	}
}