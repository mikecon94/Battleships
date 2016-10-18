package com.futuresailors.battleships.model;

import java.awt.Point;

public class Tile {
	
	//Point object representing x&y
	private Point position;
	//If the tile has been hit
	private boolean hit = false;
	
	//Constructor
	public Tile(Point xy){
		this.position = xy;
	}
	//Returns the XY position of the Tile as a Point Object
	public Point getPosition(){
		return position;
	}
	//Sets the position
	public void setPostion(Point position){
		this.position = position;
	}
	//Returns whether the ship is hit or not
	public boolean isHit(){
		return hit;
	}
	//Sets the tile as hit - Hit cannot be set to false by the game only at init
	public void setHit(){
		this.hit = true;
	}
}
