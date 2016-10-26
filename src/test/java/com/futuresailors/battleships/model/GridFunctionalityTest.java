package com.futuresailors.battleships.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

import junit.framework.TestCase;
/**
 * The test class which will test the functionality for the grid class.
 * @author rlowers
 *
 */
public class GridFunctionalityTest extends TestCase {
	//Init member variables
	private boolean x = true;
	private Grid g;
	private Ship s;
	private Point p;
	
	@Before
	public void setUp(){
		
		//Init your objects here etc
	}
	
	/**
	 * Tests that the default grid size is used if the value entered is lower than 5
	 * or greater than 30.
	 */
	@Test
	public void testDefaultGridsizeMin() {
		g = new Grid(4);
		assertEquals(10,g.getRows());
		assertEquals(10,g.getColumns());
		g = new Grid(31);
		assertEquals(10,g.getRows());
		assertEquals(10,g.getColumns());
	}
	
	/**
	 * Tests that grid ends is created to the correct size when an integer between 5 and 30 is entered
	 * as a parameter.
	 */
	@Test
	public void testGridsize() {
		int a = 5;
		int b = 6;
		int c = 15;
		int d = 29;
		int e = 30;
		g = new Grid(a);
		assertEquals(5,g.getRows());
		assertEquals(5,g.getColumns());
		g = new Grid(b);
		assertEquals(6,g.getRows());
		assertEquals(6,g.getColumns());
		g = new Grid(c);
		assertEquals(15,g.getRows());
		assertEquals(15,g.getColumns());
		g = new Grid(d);
		assertEquals(29,g.getRows());
		assertEquals(29,g.getColumns());
		g = new Grid(e);
		assertEquals(30,g.getRows());
		assertEquals(30,g.getColumns());
	}
	
	/**
	 * Tests that when a new grid is created all of the grid tiles are set to empty automatically
	 */
	@Test
	public void testNewGridTiles() {
		g = new Grid (5);
		for(int i=0; i<g.getColumns();i++){
			for(int j=0;j<g.getRows();j++){
				assert (g.getGrid()[i][j] == GridTile.EMPTY);
			}
		}
	}
	/**
	 * Test the method which is responsible for placing ships.
	 */
	@Test
	public void testPlaceShip() {
		g = new Grid(10);
		s = new Ship(1,6, "1.png");
		p = new Point(3,4);
		g.placeShip(p, s);
		
		
	}
	
	/**
	 * Tests that when a bomb is dropped that the game registers that this space has attacked
	 */
	@Test
	public void testDropBomb() {
		
	}
	
}
