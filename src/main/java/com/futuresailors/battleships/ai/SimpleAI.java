package com.futuresailors.battleships.ai;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import com.futuresailors.battleships.model.Grid;

public class SimpleAI implements AI {

	private Grid grid;
	
	public SimpleAI(Grid grid){
		this.grid = grid;
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

	@Override
	public void placeShips() {
		
		
	}

}
