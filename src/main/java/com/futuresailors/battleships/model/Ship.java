package com.futuresailors.battleships.model;

public class Ship {
	
	//Could we make this so the ship class is instantiated for each tile individually?
	//Or could the Ship object hold the XY of each tile comprising the ship in an array or tile object?
	
	//Hits taken
	private int hits = 0;
	//Length is more like how many hits until sunk, was hoping this could be set auto by an Enum or something
	private int length;
	//X Location on Grid
	private int gridX;
	//Y Location on Grid
	private int gridY;
	//Whether the ship has been sunk
	private boolean sunk;
	
	//Constructor
	public Ship(int length, int x, int y){
		this.length = length;
		this.gridX = x;
		this.gridY = y;
		System.out.println("New Ship Created - Length: " + length);
	};
	//Returns the number of times the ship has been hit, should be used to update the UI accordingly
	public int getHits() {
		return hits;
		//Should check how many tiles have been hit
	}
	//Used to register a hit
	//This needs to register a hit on the correct tile
	public void hit() {
		if(hits >= length){
			System.out.println("Ship Sunk");
			sunk = true;
		} else{
			hits++;
			System.out.println("Hit Registered");
		}
	}
	//Can be used to sink a ship without calling hit() several times e.g. in the case of a powerup
	public void sink(){
		sunk = true;
		hits = length;
	}

/*	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}*/

	public int getLength() {
		return length;
	}

	public boolean isSunk() {
		return sunk;
	}
}