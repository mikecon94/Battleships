package com.futuresailors.battleships.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GridTest {

	@Test
	public void testGridsize() {
		Grid g = new Grid(4);
		assertEquals(10,g.getRows());
		fail("Not yet implemented");
	}
	
	

}
