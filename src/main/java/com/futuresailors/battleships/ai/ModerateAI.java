package com.futuresailors.battleships.ai;

import java.awt.Point;

/**
 *
 * This implementation of the AI Interface is the medium difficulty.
 * It randomly places it's ships still but when it drops bombs it will detect whether it got a hit
 * and hit adjacent tiles.
 * @author Michael Conroy
 */
public class ModerateAI implements AI {

	@Override
	public void placeShips() {
		// TODO Calculate where to place ships.
	}

	@Override
	public Point takeMove() {
		// TODO Calculate best move.
		return null;
	}
}
