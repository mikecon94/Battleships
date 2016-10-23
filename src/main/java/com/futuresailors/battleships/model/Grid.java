package com.futuresailors.battleships.model;

import java.awt.Point;

/**
 * Represents a grid that ships can be placed on.
 * @author Michael Conroy
 * 
 */
public class Grid {
	
	private char[][] grid;
	private int gridSize = 10;
	
	public Grid(){
		//Create new 2D array for the grid using standard sizes.
		grid = new char[gridSize][gridSize];
		createNewGrid();
	}
	
	//Assumes square for now but may need adapting in future to support shapes.
	public Grid(int size){
		//Create grid with given sizes.
		//Use default if size is less than 5 or greater than 30.
		if(size < 5 || size > 30){
			grid = new char[gridSize][gridSize];
		} else {
			gridSize = size;
			grid = new char[size][size];
		}
		createNewGrid();
	}

	public char getTile(Point pos){
		return grid[pos.x][pos.y];
	}
	
	public void hover(Point pos, Ship ship){
		clearHoverTiles();
		if(checkValidPlace(pos, ship)){
			for(int yIndex = 0; yIndex < ship.getHeight(); yIndex++){
				for(int xIndex = 0; xIndex < ship.getWidth(); xIndex++){
					grid[pos.y + yIndex][pos.x + xIndex] = 'H';
				}
			}
		}
	}
	
	public void clearHoverTiles(){
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[y].length; x++){
				if(grid[y][x] == 'H'){
					grid[y][x] = ' ';
				}
			}
		}
	}
	
	private void createNewGrid(){
		for(int y = 0; y < grid.length; y++){
			for(int x = 0; x < grid[y].length; x++){
				grid[y][x] = ' ';
			}
		}
	}
	
	public int getRows(){
		return grid.length;
	}
	
	public int getColumns(){
		return grid[0].length;
	}
	
	public char[][] getGrid(){
		return grid;
	}

	public boolean checkValidPlace(Point pos, Ship ship){
		
	//Check the ship isn't off the side of the grid.
		if(pos.x < 0 || pos.x > gridSize || pos.y < 0 || pos.y > gridSize
				|| (pos.x + ship.getWidth()) > gridSize || (pos.y + ship.getHeight()) > gridSize){
			return false;
		}
		
		//Loop round the width and height of the ship 
		//and check that there is nothing blocking a placement.
		for(int yIndex = 0; yIndex < ship.getHeight(); yIndex++){
			for(int xIndex = 0; xIndex < ship.getWidth(); xIndex++){
				if(grid[pos.y + yIndex][pos.x + xIndex] == 'S'){
					System.out.println("X: " + pos.x + " Y: " + pos.y);
					return false;
				}
			}
		}
		return true;
	}
	
	public void placeShip(Point pos, Ship ship){
		for(int yIndex = 0; yIndex < ship.getHeight(); yIndex++){
			for(int xIndex = 0; xIndex < ship.getWidth(); xIndex++){
				grid[pos.y + yIndex][pos.x + xIndex] = 'S';
			}
		}
		ship.placeShip(pos);
	}
	
	public void bomb(Point pos){
		//Update the grid to represent that this cell has been bombed and return true if there was a ship there.
	}
}