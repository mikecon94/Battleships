package com.futuresailors.battleships.model;

import java.awt.Point;

/**
 * This object is used to represent the tiles
 * of a ship. It holds their location on the grid
 * and whether they have been hit or not. This is required
 * to track what Ships have sunk. The Grid model only stores
 * the ships itself that is on that tile but not which ship is there.
 * @author Joe Baldwin
 *
 */
public class Tile {
	
	private Point position;
	private boolean hit = false;
	
	public Tile(Point position){
		this.position = position;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setPostion(Point position){
		this.position = position;
	}
	
	public boolean isHit(){
		return hit;
	}
	
	public void setHit(){
		this.hit = true;
	}
}
