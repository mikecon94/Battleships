package com.futuresailors.battleships.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

import junit.framework.TestCase;

public class GridFunctionalityTest extends TestCase {
	//Init member variables
	private boolean x = true;
	
	@Before
	public void setUp(){
		//Init your objects here etc
	}
	@Test
	public void testGridsize() {
		Grid g = new Grid(4);
		assertEquals(10,g.getRows());
		fail("Not yet implemented");
	}
}
