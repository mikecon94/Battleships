package com.futuresailors.battleships.ai;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

//This is a copy of moderate that needs modifying

/**
 * This is the implementation of the Hard difficulty. When placing it's ship it will try to place
 * them in positions that aren't adjacent to each other but still randomly overall. When firing
 * shots it will check a tile next to it hasn't been shot at already.
 * 
 * @author Michael Conroy
 */
public class AdvancedAI implements AI {

    private Grid myGrid;
    private Grid oppGrid;
    private Ship[] ships;

    /**
     * @param myGrid - The AIs grid where it will place it's ships.
     * @param oppGrid - The AIs enemies grid where it will drop bombs.
     * @param ships - The AIs ships it needs to place.
     */
    public AdvancedAI(final Grid myGrid, Grid oppGrid, Ship[] ships) {
        this.myGrid = myGrid;
        this.oppGrid = oppGrid;
        this.ships = ships;
    }

    public void placeShips() {
        // Randomly places ships like the Simple AI.
        Point pos = new Point(0, 0);
        // Loop round each ship and place it.
        for (Ship ship : ships) {
            // We want to try and a place ships that aren't adjacent to each but only want to try
            // and find a space that meets this criteria 3 times so sometimes it will place ships
            // adjacent.
            int count = 0;
            do {
                do {
                    pos.x = ThreadLocalRandom.current().nextInt(0, myGrid.getColumns());
                    pos.y = ThreadLocalRandom.current().nextInt(0, myGrid.getRows());
                    if (ThreadLocalRandom.current().nextInt(0, 2) == 1) {
                        ship.rotateShip();
                    }
                } while (!myGrid.checkValidPlace(pos, ship));
                count++;
                System.out.println(count);
                System.out.println("Checking position: " + pos + " Width: " + ship.getWidth()
                        + " Length:" + ship.getHeight());
            } while (count < 3 && adjacentToShip(pos, ship));
            System.out.println("Ship Placed: " + pos + " Width: " + ship.getWidth() + " Height:"
                    + ship.getHeight());
            myGrid.placeShip(pos, ship);
        }
    }

    /**
     * @param pos - Proposed position of the ship.
     * @param ship - The ship being placed
     * @return Boolean - True if the proposed position is touching another ship
     */
    private boolean adjacentToShip(Point pos, Ship ship) {
        // Loop round each tile checking each of the 4 tiles around it for another ship.
        // The ship hasn't been placed yet but the proposed position has been confirmed as valid
        // so we know the tiles of the proposed position will be empty which makes the algorithm
        // easier as we can check them and not have to worry about the fact that the ship will be
        // placed there.
        for (int width = 0; width < ship.getWidth(); width++) {
            for (int height = 0; height < ship.getHeight(); height++) {
                Point left = new Point((width + pos.x) - 1, (height + pos.y));
                Point right = new Point((width + pos.x) + 1, (height + pos.y));
                Point up = new Point((width + pos.x), (height + pos.y) - 1);
                Point down = new Point((width + pos.x), (height + pos.y) + 1);
                System.out.println(
                        "Left: " + left + " Right: " + right + " Up: " + up + "Down: " + down);
                if (left.x >= 0) {
                    if (myGrid.getTile(left) == GridTile.SHIP) {
                        return true;
                    }
                }

                if (right.x < myGrid.getColumns()) {
                    if (myGrid.getTile(right) == GridTile.SHIP) {
                        return true;
                    }
                }

                if (up.y >= 0) {
                    if (myGrid.getTile(up) == GridTile.SHIP) {
                        return true;
                    }
                }

                if (down.y < myGrid.getRows()) {
                    if (myGrid.getTile(down) == GridTile.SHIP) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Point takeMove() {
        Point target = new Point(0, 0);
        target.x = ThreadLocalRandom.current().nextInt(0, myGrid.getColumns());
        target.y = ThreadLocalRandom.current().nextInt(0, myGrid.getRows());
        // for (int y = 0; y < oppGrid.getColumns(); y++) {
        // for (int x = 0; x < oppGrid.getRows(); x++) {
        // if (oppGrid.getTile(new Point(x, y)) == GridTile.SHIP) {
        // target = new Point(x, y);
        // return target;
        // }
        // }
        // }
        return target;
    }
}
