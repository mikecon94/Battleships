package com.futuresailors.battleships.model;

public class Ship {
	
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
	}
	//Used to register a hit
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

	public int getGridX() {
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
	}

	public int getLength() {
		return length;
	}

	public boolean isSunk() {
		return sunk;
	}
}