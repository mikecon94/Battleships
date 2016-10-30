package com.futuresailors.battleships.model;

import java.awt.Point;

/**
 * This object is used to represent the tiles
 * of a ship. It holds their location on the grid
 * and whether they have been hit or not. This is required
 * to track what Ships have sunk. The Grid model only stores
 * the ships itself that is on that tile but not which ship is there.
 * @author Joe Baldwin
 */
public class ShipTile {
	
	private Point position;
	private boolean hit = false;
	
	public ShipTile(Point position){
		this.position = position;
	}
	/**
	 * @return Returns Tiles position as a Point
	 */
	public Point getPosition(){
		return position;
	}
	/**
	 * Allows the position of the tile on the grid to be set
	 * @param position - Tiles position as a point
	 */
	public void setPostion(Point position){
		this.position = position;
	}
	/**
	 * @return If Tile has been hit as a boolean
	 */
	public boolean isHit(){
		return hit;
	}
	/**
	 * Sets the tile as hit
	 */
	public void setHit(){
		this.hit = true;
	}
}
