package com.futuresailors.battleships.model;

import java.awt.Point;

public class Ship {
	//Width of the ship in number of tiles
	private final int width;
	//Height of the ship in number of tiles
	private final int height;
	//Whether the ship has been sunk
	private boolean sunk = false;
	//Tiles the ship occupys
	private Tile tiles[];
	//Image path
	private String imageLoc;
	
	//Constructor
	public Ship(int width, int height, String imageLoc){
		this.width = width;
		this.height = height;
		this.imageLoc = imageLoc;
		//Init tile array with the amount of tiles the ship occupys
		tiles = new Tile[height*width];
		System.out.println("New Ship Created");
	};
	//Used to register a hit
	public boolean hit(Point location) {
		for(Tile tile : tiles){
			if(tile.getPosition() == location){
				tile.setHit();
				return true;
			}
		}
		return false;
	}
	//Checks if any of the tiles have been hit and if they have all been hit returns true
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
	//Allows the ship to be marked sunk
	public void sink(){
		sunk = true;
	}
	//Returns true if the ship is sunk
	public boolean isSunk() {
		return sunk;
	}
	//Initialises the tiles array based on the top left hand tile
	public void createTiles(Point pos){
		for( int i=0; i < height; i++){
			for(int j =0; j < width;j++) {
				tiles[i+j].setPostion(new Point((int)pos.getX() + j,(int)pos.getY()+i));
			}
		}
	}
	//Returns width for use in the view
	public int getWidth(){
		return width;
	}
	//Returns width for use in the view
	public int getHeight(){
		return height;
	}
	//Returns Image Location
	public String getImageLoc() {
		return imageLoc;
	}
	//Returns the tiles array
	public Tile[] getTiles(){
		return tiles;	
	}
}