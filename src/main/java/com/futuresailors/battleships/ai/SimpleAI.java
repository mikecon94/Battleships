package com.futuresailors.battleships.ai;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

/**
 * This implementation of the AI Interface is the most
 * basic level, it randomly guesses where to hit and randomly
 * assigns ships.
 * @author Joe Baldwin, Michael Conroy
 */
public class SimpleAI implements AI {

	private Grid grid;
	private Ship[] ships;
	
	public SimpleAI(Grid grid, Ship[] ships){
		this.grid = grid;
		this.ships = ships;
	}
	
	@Override
	public void placeShips() {
		Point pos = new Point(0,0);
		for(Ship ship : ships){
			do {
				pos.x = ThreadLocalRandom.current().nextInt(0, grid.getColumns());
				pos.y = ThreadLocalRandom.current().nextInt(0, grid.getRows());
			} while (grid.checkValidPlace(pos, ship) == false);
			ship.placeShip(pos);
			grid.placeShip(pos, ship);
		}
	}
	
	@Override
	public Point takeMove(){
		int x = ThreadLocalRandom.current().nextInt(0, grid.getColumns());
		int y = ThreadLocalRandom.current().nextInt(0, grid.getRows());
		return new Point(x,y);
	}
}