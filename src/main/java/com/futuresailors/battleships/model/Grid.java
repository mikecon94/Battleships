package com.futuresailors.battleships.model;

public class Grid {
	
	//Shall we use chars or an ENUM to represent the cells??
	private char[][] grid;
	private int defSize = 10;
	
	public Grid(){
		//Create new 2D array for the grid using standard sizes.
		grid = new char[defSize][defSize];
		createNewGrid();
	}
	
	//Assumes square for now but may need adapting in future to support shapes.
	public Grid(int size){
		//Create grid with given sizes.
		//Use default if size is less than 5 or greater than 30.
		if(size < 5 || size > 30){
			grid = new char[defSize][defSize];
		} else {
			defSize = size;
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
		if(x < 0 || x > defSize || y < 0 || y > defSize
				|| (x + ship.getWidth()) > defSize || (y + ship.getHeight()) > defSize){
			return false;
		}
		return true;
	}
	
	public void placeShip(int x, int y){
		//Translate the given x & y into actual coordinates of the grid
		//ie. x = 0 & y = 0 should be bottom left and working up from there.
	}
	
	public void bomb(int x, int y){
		//Update the grid to represent that this cell has been bombed and return true if there was a ship there.
	}
}