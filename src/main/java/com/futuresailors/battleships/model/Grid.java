package com.futuresailors.battleships.model;

public class Grid {
	
	//Shall we use chars or an ENUM to represent the cells??
	private char[][] grid;
	private int defSize = 10;
	
	public Grid(){
		//Create new 2D array for the grid using standard sizes.
		grid = new char[defSize][defSize];
	}
	
	//Assumes square for now but may need adapting in future to support shapes.
	public Grid(int size){
		//Create grid with given sizes.
		//Use default if size is less than 5 or greater than 30.
		if(size < 5 || size > 30){
			grid = new char[defSize][defSize];
		} else {
			grid = new char[size][size];
		}
	}

	public char[][] getGrid(){
		return grid;
	}
	
	public void placeShip(int x, int y){
		//Translate the given x & y into actual coordinates of the grid
		//ie. x = 0 & y = 0 should be bottom left and working up from there.
	}
	
	public boolean bomb(){
		//Update the grid to represent that this cell has been bombed and return true if there was a ship there.
		return false;
	}
}
