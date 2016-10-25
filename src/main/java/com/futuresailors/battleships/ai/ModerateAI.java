package com.futuresailors.battleships.ai;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
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
	private Point lastGuess = new Point(-1,-1);
	
	public ModerateAI(Grid grid, Ship[] ships){
		this.grid = grid;
		this.ships = ships;
	}

	@Override
	public void placeShips() {

	}

	@Override
	public Point takeMove() {
		//Generate Guess
		int x = ThreadLocalRandom.current().nextInt(0, grid.getColumns());
		int y = ThreadLocalRandom.current().nextInt(0, grid.getRows());
		//Check if this is the first guess
		if(lastGuess.x == -1 && lastGuess.y == -1){
			lastGuess.x = x;
			lastGuess.y = y;
			return new Point(x,y);
		}else{//If not the first guess, check if the last guess was a hit
			if(grid.getTile(lastGuess) == GridTile.HIT){
				//Work out strategy for selecting adjacent tiles
			}else{
				lastGuess.x = x;
				lastGuess.y = y;
				return new Point(x,y);
			}
		}
		return new Point(1,1);
	}
	
	//We should maybe have a method here called public Point calculateNextMove(Boolean wasHit, Point lastMove)
	//This should, based on whether the last move was a hit decide whether to proceed with a strategy
	//like hitting adjecent tiles or it should make another random guess.
}
