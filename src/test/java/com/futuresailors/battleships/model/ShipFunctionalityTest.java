package com.futuresailors.battleships.model;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import com.futuresailors.battleships.model.Ship;

import junit.framework.TestCase;

public class ShipFunctionalityTest extends TestCase {
    // Init member variables
    private Ship ship;
    private Point pos;
    private int x = 1;
    private int y = 1;

    @Before
    public void setUp() {
        ship = new Ship(1, 5, "src/main/resources/images/ships/1.png");
        pos = new Point(x, y);
    }

    @Test
    public void testShipPlacement() {
        ship.placeShip(pos);
        assert (ship.getPlaced() == true);
        assert (ship.getPos() == pos);
    }

    //TODO Update this test so it hits each tile of the ship instead of calling sink.
//    @Test
    public void testShipSinking() {
      Point p = new Point(1, 1);
      Grid g = new Grid(10);
      g.placeShip(pos, ship);
      for(int i=1;i<ship.getHeight()+1;i++){
          Point target = new Point(1,i);
          g.dropBomb(target);
          System.out.println(g.getTile(target));
      }
      System.out.println(g.getTile(p));
      //assert (ship.isSunk() == true);
    }
}
