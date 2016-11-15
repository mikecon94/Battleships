package com.futuresailors.battleships.model;

import java.awt.Point;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * The test class which will test the functionality for the grid class.
 * 
 * @author Ryan Lowers
 */
public class GridFunctionalityTest extends TestCase {
    // Init member variables
    private Grid g;
    private Ship s;
    private Ship s2;
    private Point p;

    /**
     * Tests that the default grid size is used if the value entered is lower than 5 or greater than
     * 30.
     */
    @Test
    public void testDefaultGridsizeMin() {
        g = new Grid(4);
        assertEquals(10, g.getRows());
        assertEquals(10, g.getColumns());
        g = new Grid(31);
        assertEquals(10, g.getRows());
        assertEquals(10, g.getColumns());
    }

    /**
     * Tests that grid ends is created to the correct size when an integer between 5 and 30 is
     * entered as a parameter.
     */
    @Test
    public void testGridsize() {
        int a = 5;
        int b = 6;
        int c = 15;
        int d = 29;
        int e = 30;
        g = new Grid(a);
        assertEquals(5, g.getRows());
        assertEquals(5, g.getColumns());
        g = new Grid(b);
        assertEquals(6, g.getRows());
        assertEquals(6, g.getColumns());
        g = new Grid(c);
        assertEquals(15, g.getRows());
        assertEquals(15, g.getColumns());
        g = new Grid(d);
        assertEquals(29, g.getRows());
        assertEquals(29, g.getColumns());
        g = new Grid(e);
        assertEquals(30, g.getRows());
        assertEquals(30, g.getColumns());
    }

    /**
     * Tests that when a new grid is created all of the grid tiles are set to empty automatically
     */
    @Test
    public void testNewGridTiles() {
        g = new Grid(5);
        for (int xPoint = 0; xPoint < g.getColumns(); xPoint++) {
            for (int yPoint = 0; yPoint < g.getRows(); yPoint++) {
                assert (g.getGrid()[yPoint][xPoint] == GridTile.EMPTY);
            }
        }
    }

    /**
     * Test the method which is responsible for placing ships.
     */
    @Test
    public void testPlaceShip() {
        p = new Point(3, 4);
        g = new Grid(10);
        s = new Ship(1, 6, "/images/ships/1.png");
        g.placeShip(p, s);

        for (int xPoint = p.x; xPoint < (s.getWidth() + p.x); xPoint++) {
            for (int yPoint = p.y; yPoint < (s.getHeight() + p.y); yPoint++) {
                assert (g.getGrid()[yPoint][xPoint] == GridTile.SHIP);
            }
        }

        p = new Point(0, 4);
        s = new Ship(4, 1, "/images/ships/1.png");
        g.placeShip(p, s);

        for (int xPoint = p.x; xPoint < (s.getWidth() + p.x); xPoint++) {
            for (int yPoint = p.y; yPoint < (s.getHeight() + p.y); yPoint++) {
                assert (g.getGrid()[yPoint][xPoint] == GridTile.SHIP);
            }
        }

        p = new Point(9, 3);
        s = new Ship(1, 2, "/images/ships/1.png");
        g.placeShip(p, s);

        for (int xPoint = p.x; xPoint < (s.getWidth() + p.x); xPoint++) {
            for (int yPoint = p.y; yPoint < (s.getHeight() + p.y); yPoint++) {
                assert (g.getGrid()[yPoint][xPoint] == GridTile.SHIP);
            }
        }
    }

    /**
     * Tests that when a Tile is hovered over to drop a bomb, the tile state changes to hover.
     */
    @Test
    public void testHoverDropBomb() {
        p = new Point(0, 4);
        g = new Grid(10);
        g.hoverBomb(p);
        assert (g.getGrid()[p.y][p.x] == GridTile.HOVER);
        p = new Point(1, 4);
        g.hoverBomb(p);
        assert (g.getGrid()[4][0] == GridTile.EMPTY);
    }

    /**
     * Test the hover ship method to make sure that the correct tiles are set to hover
     */
    @Test
    public void testHoverShip() {
        p = new Point(0, 4);
        g = new Grid(10);
        s = new Ship(4, 1, "/images/ships/1.png");
        g.hoverShip(p, s);
        System.out.println(8);
        for (int xPoint = p.x; xPoint < (s.getWidth() + p.x); xPoint++) {
            for (int yPoint = p.y; yPoint < (s.getHeight() + p.y); yPoint++) {
                assert (g.getGrid()[yPoint][xPoint] == GridTile.HOVER);
            }
        }
    }

    /**
     * Tests that the checking method which determines if a ship has been placed in a valid spot
     */
    @Test
    public void testCheckInput() {
        p = new Point(0, 10);
        g = new Grid(10);
        s = new Ship(4, 1, "/images/ships/1.png");
        assert (g.checkValidPlace(p, s) == false);

        p = new Point(0, 4);
        g.placeShip(p, s);
        s2 = new Ship(1, 4, "/images/ships/1.png");
        assert (g.checkValidPlace(p, s2) == false);
    }

    /**
     * Tests the drop bomb method to make sure tiles are set to the correct status upon a hit or a
     * miss
     */
    @Test
    public void testDropBomb() {
        p = new Point(0, 4);
        g = new Grid(10);
        s = new Ship(4, 1, "/images/ships/1.png");
        g.placeShip(p, s);
        Point t = new Point(7, 7);
        g.dropBomb(p);
        g.dropBomb(t);
        assert (g.getGrid()[p.y][p.x] == GridTile.HIT);
        assert (g.getGrid()[t.y][t.x] == GridTile.MISS);
    }
}
