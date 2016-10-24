package com.futuresailors.battleships.ai;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

/**
 *
 * This implementation of the AI Interface is the medium difficulty.
 * It randomly places it's ships still but when it drops bombs it will detect whether it got a hit
 * and hit adjacent tiles.
 * @author Michael Conroy
 */
public class ModerateAI implements AI {
	
	private Grid grid;
	private Ship[] ships;
	
	public ModerateAI(Grid grid, Ship[] ships){
		this.grid = grid;
		this.ships = ships;
	}

	@Override
	public void placeShips() {

	}

	@Override
	public Point takeMove() {
		int x = ThreadLocalRandom.current().nextInt(0, grid.getColumns());
		int y = ThreadLocalRandom.current().nextInt(0, grid.getRows());
		return new Point(x,y);
	}
}
