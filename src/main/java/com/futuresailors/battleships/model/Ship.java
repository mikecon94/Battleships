package com.futuresailors.battleships.model;

import java.awt.Point;

/**
 * This object represents a ship on the board
 * and its respective tiles. It also contains the ships
 * icons filepath and methods that change the state of the
 * ship in the game.
 * @author Joe Baldwin - Mike Conroy
 *
 */

public class Ship {
	//Width of the ship in number of tiles
	private final int WIDTH;
	//Height of the ship in number of tiles
	private final int HEIGHT;
	//Whether the ship has been sunk
	private boolean sunk = false;
	//Tiles the ship occupys
	private Tile tiles[];
	//Image path
	private String imagePath;
	
	//Constructor
	public Ship(int width, int height, String imagePath){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.imagePath = imagePath;
		//Init tile array with the amount of tiles the ship occupys
		tiles = new Tile[height*width];
		System.out.println("New Ship Created, Height: " + height + " Width: " + width + " Tiles: " + tiles.length);
	}
	
	/**
	 * Checks if the opponent scored a hit on a ships tile
	 * @param location - A Point object for the tile location that was hit
	 */
	public boolean hit(Point location) {
		for(Tile tile : tiles){
			if(tile.getPosition() == location){
				tile.setHit();
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Checks if any of the tiles have been hit and if they have all been hit returns true
	 */
	public boolean checkIfSunk(){
		//Loops through each tile
		for(Tile tile : tiles){
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
	 * Checks if the ship is sunk
	 */
	public boolean isSunk() {
		return sunk;
	}
	
	/**
	 * Initialises the tiles array based on the top left hand tile
	 * @param pos - A Point representing the top left hand tile of the ship
	 */
	public void createTiles(Point pos){
		System.out.println("Number of Tiles: " + tiles.length);	

		int index = 0;
		for(int i=0; i < HEIGHT; i++){
			System.out.println("Height Loop " + i);
			for(int j=0; j < WIDTH;j++) {
				Point point = new Point(pos.x+ j,pos.y +i);
				tiles[index] = new Tile(point);
				index++;
			}
		}
	}
	/**
	 * Returns width for use in the view
	 */
	public int getWidth(){
		return WIDTH;
	}
	/**
	 * Returns width for use in the view
	 */
	public int getHeight(){
		return HEIGHT;
	}
	/**
	 * Returns Image Location
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * Returns the tiles array
	 */
	public Tile[] getTiles(){
		return tiles;	
	}
}