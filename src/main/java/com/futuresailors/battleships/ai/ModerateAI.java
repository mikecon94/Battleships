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
	
	private Grid myGrid;
	private Grid oppGrid;
	private Ship[] ships;
	private Point lastGuess = new Point(-1,-1);
	private boolean flag;
	
	public ModerateAI(Grid myGrid, Grid oppGrid, Ship[] ships){
		this.myGrid = myGrid;
		this.oppGrid = oppGrid;
		this.ships = ships;
	}

	@Override
	public void placeShips() {
		Point pos = new Point(0,0);
		for(Ship ship : ships){
			do {
				pos.x = ThreadLocalRandom.current().nextInt(0, myGrid.getColumns());
				pos.y = ThreadLocalRandom.current().nextInt(0, myGrid.getRows());
			} while (myGrid.checkValidPlace(pos, ship) == false);
			ship.placeShip(pos);
			myGrid.placeShip(pos, ship);
		}
	}

	@Override
	public Point takeMove() {
		Point target = checkForHits();
		//Generate Guess
		int x = ThreadLocalRandom.current().nextInt(0, myGrid.getColumns());
		int y = ThreadLocalRandom.current().nextInt(0, myGrid.getRows());
		//Check if this is the first guess
		if(lastGuess.x == -1 && lastGuess.y == -1){
			lastGuess.x = x;
			lastGuess.y = y;
			return new Point(x,y);
		}else{//If not the first guess, check if the last guess was a hit
			if(myGrid.getTile(lastGuess) == GridTile.HIT){
				//Hit tile to the left after checking if that is a valid move
				//Set flag to identify a guess by the strategy has already been made?
				if(flag == true){
					//try to hit next adjacent tile as long as it is valid
					//Maybe have a switch here to set the behaviour based on a variable holding the directions that have tried already?
				}
			}else{
				lastGuess.x = x;
				lastGuess.y = y;
				return new Point(x,y);
			}
		}
		//Placeholder return to prevent errors
		return new Point(1,1);
	}
	
	private Point checkForHits(){
		for(int y = 0; y < oppGrid.getRows(); y++){
			for(int x = 0; x < oppGrid.getColumns(); x++){
				if(oppGrid.getTile(new Point(x, y)) == GridTile.HIT){
					return new Point(x, y);
				}
			}
		}
		return new Point(-1, -1);
	}
}
