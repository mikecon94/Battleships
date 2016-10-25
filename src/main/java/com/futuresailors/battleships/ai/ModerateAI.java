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
		Point target = checkForTarget();
		if(target.x == -1){
			//Since there are no targets we choose randomly where to bomb
			//Like the Simple AI.
			do {
				int x = ThreadLocalRandom.current().nextInt(0, oppGrid.getColumns());
				int y = ThreadLocalRandom.current().nextInt(0, oppGrid.getRows());
				target = new Point(x, y);
			} while(oppGrid.getTile(target) == GridTile.MISS || oppGrid.getTile(target) == GridTile.HIT);
			return target;
		} else {
			return target;
		}
	}
	
	/**
	 * Loops round all the tiles in the opponents grid and if it finds one that has been hit. It will then check the
	 * surrounding tiles and if any are empty it will throw a bomb at them.
	 * @return Point - The target to throw the bomb. If it is -1, -1 then there is no obvious target.
	 */
	private Point checkForTarget(){
		for(int y = 0; y < oppGrid.getRows(); y++){
			for(int x = 0; x < oppGrid.getColumns(); x++){
				Point checkHit = new Point(x, y);
				if(oppGrid.getTile(checkHit) == GridTile.HIT){
					Point target = getTargetFromHit(checkHit);
					if(target.x != -1){
						System.out.println("Found Target");
						return target;
					}
				}
			}
		}
		return new Point(-1, -1);
	}
	
	private Point getTargetFromHit(Point centreTile){
		int count = 0;
		
		do{
			int checkX = centreTile.x + 1;
			int checkY = centreTile.y;
			if(checkX > 0 && checkX < oppGrid.getColumns() && checkY > 0 && checkY < oppGrid.getRows()){
				Point potentialTarget = new Point(checkX, checkY);
				System.out.println("Checking Target: " + potentialTarget);
				if(oppGrid.getTile(potentialTarget) != GridTile.HIT && oppGrid.getTile(potentialTarget) != GridTile.MISS){
					return potentialTarget;
				}
			}
			count++;
		} while (count < 4);
		
		return new Point(-1, -1);
	}
}
