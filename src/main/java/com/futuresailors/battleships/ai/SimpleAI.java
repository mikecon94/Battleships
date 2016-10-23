package com.futuresailors.battleships.ai;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

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
		//Calculate the appropriate move.
		Point hit = new Point(x,y);
		return hit;
	}
	
	//The moderate AI should probably randomly guess also until it hits then it should
	//follow some sort of strategy like trying the top,left,right and bottom tiles around the hit
	//and proceeding in that direction should it score another hit until it no longer scores a hit

	@Override
	public void placeShips() {
		
		
	}

}
