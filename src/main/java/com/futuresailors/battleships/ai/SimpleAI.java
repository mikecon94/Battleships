package com.futuresailors.battleships.ai;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleAI implements AI {

	@Override
	public Point takeMove(int gridHeight, int gridWidth){
		int y = ThreadLocalRandom.current().nextInt(0, gridHeight + 1);
		int x = ThreadLocalRandom.current().nextInt(0, gridWidth + 1);
		Point guess = new Point(x,y);
		return guess;
	}

	@Override
	public void placeShips() {
		
		
	}

}
