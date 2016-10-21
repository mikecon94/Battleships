package com.futuresailors.battleships.model;

import java.awt.Point;

public class Grid {
	
	//Shall we use chars or an ENUM to represent the cells??
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

	public char getTile(int x, int y){
		return grid[y][x];
	}
	
	public void hover(int x, int y, Ship ship){
		clearHoverTiles();
		if(checkValidPlace(x, y, ship)){
			for(int yIndex = 0; yIndex < ship.getHeight(); yIndex++){
				for(int xIndex = 0; xIndex < ship.getWidth(); xIndex++){
					grid[y + yIndex][x + xIndex] = 'H';
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

	public boolean checkValidPlace(int x, int y, Ship ship){
		
		//Loop round the width and height of the ship 
		//and check whether the tiles exist (ie. not off the side of grid)
		//and that there is nothing else there.
		//Check nothing is off the side of the grid.
		if(x < 0 || x > gridSize || y < 0 || y > gridSize
				|| (x + ship.getWidth()) > gridSize || (y + ship.getHeight()) > gridSize){
			return false;
		}
		
		//System.out.println("X: " + x + " Y: " + y);
		for(int yIndex = 0; yIndex < ship.getHeight(); yIndex++){
			for(int xIndex = 0; xIndex < ship.getWidth(); xIndex++){
				if(grid[y + yIndex][x + xIndex] == 'S'){
					System.out.println("X: " + x + " Y: " + y);
					return false;
				}
			}
		}
		return true;
	}
	
	public void placeShip(int x, int y, Ship ship){
		for(int yIndex = 0; yIndex < ship.getHeight(); yIndex++){
			for(int xIndex = 0; xIndex < ship.getWidth(); xIndex++){
				grid[y + yIndex][x + xIndex] = 'S';
			}
		}
		ship.placeShip(new Point(x, y));
	}
	
	public void bomb(int x, int y){
		//Update the grid to represent that this cell has been bombed and return true if there was a ship there.
	}
}