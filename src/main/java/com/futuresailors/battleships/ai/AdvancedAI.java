package com.futuresailors.battleships.ai;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;


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
    private final boolean targetEvenSquares;

    /**
     * @param myGrid - The AIs grid where it will place it's ships.
     * @param oppGrid - The AIs enemies grid where it will drop bombs.
     * @param ships - The AIs ships it needs to place.
     */
    public AdvancedAI(final Grid myGrid, Grid oppGrid, Ship[] ships) {
        this.myGrid = myGrid;
        this.oppGrid = oppGrid;
        this.ships = ships;
        targetEvenSquares = (ThreadLocalRandom.current().nextInt(0, 2) == 1) ? true : false;
    }
    
    /**
     * Hard AI Places its ships.
     */
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
    
    /**
     * Hard AI takes its move.
     */
    public Point takeMove() {
        Point target = checkForTarget();
        if (target.x == -1) {
            // Since there are no targets we choose randomly where to bomb
            // Like the Simple AI.
            do {
                int x = 0;
                int y = 0;
                if (targetEvenSquares) {
                    do {
                        x = ThreadLocalRandom.current().nextInt(0, oppGrid.getColumns());
                        y = ThreadLocalRandom.current().nextInt(0, oppGrid.getRows());
                    } while ((x + y) % 2 != 0);
                } else {
                    do {
                        x = ThreadLocalRandom.current().nextInt(0, oppGrid.getColumns());
                        y = ThreadLocalRandom.current().nextInt(0, oppGrid.getRows());
                    } while ((x + y) % 2 == 0);
                }
                target = new Point(x, y);
            } while (oppGrid.getTile(target) == GridTile.MISS
                    || oppGrid.getTile(target) == GridTile.HIT
                    || oppGrid.getTile(target) == GridTile.LAND);
            return target;
        } else {
            return target;
        }
    }

    /**
     * Loops round all the tiles in the opponents grid and if it finds one that has been hit. It
     * will then check the surrounding tiles and if any are empty it will throw a bomb at them.
     * 
     * @return Point - The target to throw the bomb. If it is -1, -1 then there is no good target.
     */
    private Point checkForTarget() {
        for (int y = 0; y < oppGrid.getRows(); y++) {
            for (int x = 0; x < oppGrid.getColumns(); x++) {
                Point checkHit = new Point(x, y);
                // If the tile is hit then we check its surroundings.
                if (oppGrid.getTile(checkHit) == GridTile.HIT) {
                    // Get a surrounding valid tile from around the HIT tile.
                    Point target = getTargetFromHit(checkHit);
                    // If this isn't -1 then a valid tile has been found.
                    if (target.x != -1) {
                        return target;
                    }
                }
            }
        }
        return new Point(-1, -1);
    }

    /**
     * Checks the surrounding tiles of a given tile and returns any valid tiles to target.
     * 
     * @param centreTile - The tile to check the surroundings of.
     * @return The Point to hit. -1, -1 if no valid tile.
     */
    private Point getTargetFromHit(Point centreTile) {
        // TODO Investigate bug in this code that means sometimes a tile next to a hit tile that is
        // not hit or miss won't get shot at.
        // 0 - UP, 1 - RIGHT, 2 - DOWN, 3 - LEFT
        // Choose a random direction to first check.
        int startDirection = ThreadLocalRandom.current().nextInt(0, 4);
        int direction = startDirection;
        do {
            // Get the tile in the chosen direction.
            Point potentialTarget = chooseDirection(direction, centreTile);
            // Check if it is a valid target (ie. on the grid).
            if (potentialTarget.x >= 0 && potentialTarget.x < oppGrid.getColumns()
                    && potentialTarget.y >= 0 && potentialTarget.y < oppGrid.getRows()) {
                // Check the tile hasn't already been bombed.
                if (oppGrid.getTile(potentialTarget) != GridTile.HIT
                        && oppGrid.getTile(potentialTarget) != GridTile.MISS
                        && oppGrid.getTile(potentialTarget) != GridTile.LAND) {
                    // Return this tile as it is next to a tile that has been hit.
                    return potentialTarget;
                }
            }
            // Move the direction clockwise.
            direction++;
            if (direction == 4) {
                // Reset the direction once it's gone round.
                direction = 0;
            }
            // When we get back to start direction we have checked all the surrounding tiles.
        } while (direction != startDirection);

        return new Point(-1, -1);
    }

    /**
     * Returns a tile to hit based on the centre tile and direction given.
     * 
     * @param direction - An int representing the direction. (0 - UP, 1 - RIGHT, 2 - DOWN, 3 -
     *            LEFT).
     * @param centre - A Point object representing the tile to move from.
     * @return The Point to target
     */
    private Point chooseDirection(int direction, Point centre) {
        Point target = new Point(-1, -1);
        switch (direction) {
            // 0 - UP
            case 0:
                target.setLocation(centre.x, centre.y - 1);
                break;
            // 1 - RIGHT
            case 1:
                target.setLocation(centre.x + 1, centre.y);
                break;
            // 2 - DOWN
            case 2:
                target.setLocation(centre.x, centre.y + 1);
                break;
            // 3 - LEFT
            case 3:
                target.setLocation(centre.x - 1, centre.y);
                break;
        }
        return target;
    }
}
