package com.futuresailors.battleships.ai;

import java.awt.Point;

public interface AI {
	public Point takeMove(int gridHeight, int gridWidth);
	public void placeShips();
}
