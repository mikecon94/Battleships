package com.futuresailors.battleships.ai;

import java.awt.Point;

import com.futuresailors.battleships.model.Grid;

public interface AI {
	public Point takeMove();
	public void placeShips();
}
