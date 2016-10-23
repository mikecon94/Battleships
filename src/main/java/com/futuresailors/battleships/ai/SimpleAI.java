package com.futuresailors.battleships.ai;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

public class SimpleAI implements AI {

	private Grid grid;
	private Ship[] ships;
	
	public SimpleAI(Grid grid, Ship[] ships){
		this.grid = grid;
		this.ships = ships;
	}
	
	@Override
	public void placeShips() {
		for(Ship ship : ships){
			int x;
			int y;
			do {
				y = ThreadLocalRandom.current().nextInt(0, grid.getRows() + 1);
				x = ThreadLocalRandom.current().nextInt(0, grid.getColumns() + 1);
				
			} while (grid.checkValidPlace(x, y, ship) == false);
				Point pos = new Point(x,y);
				ship.placeShip(pos);
				grid.placeShip(x, y, ship);
		}
	}
	
	//This should have a variable one it with the grid.
	//It should read the grid and return a value based off of that.
	@Override
	public Point takeMove(){
		//Will need to look at the closer to check gridHeight + 1 can't cause an
		//array out of bounds exception.
		int y = ThreadLocalRandom.current().nextInt(0, grid.getRows() + 1);
		int x = ThreadLocalRandom.current().nextInt(0, grid.getColumns() + 1);
		return new Point(x,y);
	}
}
	//The moderate AI should probably randomly guess also until it hits then it should
	//follow some sort of strategy like trying the top,left,right and bottom tiles around the hit
	//and proceeding in that direction should it score another hit until it no longer scores a hit