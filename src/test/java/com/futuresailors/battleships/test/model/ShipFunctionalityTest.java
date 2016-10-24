package com.futuresailors.battleships.test.model;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import com.futuresailors.battleships.model.Ship;

import junit.framework.TestCase;

public class ShipFunctionalityTest extends TestCase {
	//Init ship
	private Ship ship;
	private Point pos;
	private int x = 1;
	private int y = 1;
	
	@Before
	public void setUp(){
		ship = new Ship(1,5,"src/main/resources/images/ships/1.png");
		pos = new Point(x,y);
	}
	@Test
	public void testShipPlacement(){
		ship.placeShip(pos);
		assert(ship.getPlaced() == true);
	}
	@Test
	public void testShipSinking(){
		ship.sink();
		assert(ship.isSunk() == true);
	}
	@Test
	public void testHitDetection(){
		//Waiting for removal of tile class
		assert(1 == 1);
	}
}
