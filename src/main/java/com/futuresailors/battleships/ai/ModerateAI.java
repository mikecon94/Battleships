package com.futuresailors.battleships.ai;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This implementation of the AI Interface is the medium difficulty. It randomly places it's ships
 * still but when it drops bombs it will detect whether it got a hit and hit adjacent tiles.
 * 
 * @author Michael Conroy
 */
public class ModerateAI implements AI {

    private Grid myGrid;
    private Grid oppGrid;
    private Ship[] ships;
    
    /**
     * Constructor for the Moderate AI.
     * @param   myGrid      player grid.
     * @param   oppGrid     AI Grid.
     * @param   ships       Array of ships.
     */
    public ModerateAI(Grid myGrid, Grid oppGrid, Ship[] ships) {
        this.myGrid = myGrid;
        this.oppGrid = oppGrid;
        this.ships = ships;
    }
    
    /**
     * Moderate AI places its ships.
     */
    public void placeShips() {
        // Randomly places ships like the Simple AI.
        Point pos = new Point(0, 0);
        for (Ship ship : ships) {
            do {
                pos.x = ThreadLocalRandom.current().nextInt(0, myGrid.getColumns());
                pos.y = ThreadLocalRandom.current().nextInt(0, myGrid.getRows());
                if (ThreadLocalRandom.current().nextInt(0, 2) == 1) {
                    ship.rotateShip();
                }
            } while (!myGrid.checkValidPlace(pos, ship));
            ship.placeShip(pos);
            myGrid.placeShip(pos, ship);
        }
    }
    
    /**
     * Moderate AI takes its move.
     */
    public Point takeMove() {
        Point target = checkForTarget();
        if (target.x == -1) {
            // Since there are no targets we choose randomly where to bomb
            // Like the Simple AI.
            do {
                int x = ThreadLocalRandom.current().nextInt(0, oppGrid.getColumns());
                int y = ThreadLocalRandom.current().nextInt(0, oppGrid.getRows());
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