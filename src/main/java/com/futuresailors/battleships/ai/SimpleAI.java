package com.futuresailors.battleships.ai;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This implementation of the AI Interface is the most basic level, it randomly guesses where to hit
 * and randomly assigns ships.
 * 
 * @author Joe Baldwin
 */
public class SimpleAI implements AI {

    private Grid myGrid;
    private Grid oppGrid;
    private Ship[] ships;
    
    /**
     * Constructor for the Simple AI.
     * @param   myGrid      player grid.
     * @param   oppGrid     AI Grid.
     * @param   ships       Array of ships.
     */
    public SimpleAI(Grid myGrid, Grid oppGrid, Ship[] ships) {
        this.myGrid = myGrid;
        this.oppGrid = oppGrid;
        this.ships = ships;
    }
    
    /**
     * Determines the coordinates of where ships will be placed by generating random numbers
     * and provided they are in a suitable position then they placed on the board.
     */
    
    public void placeShips() {
        Point pos = new Point(0, 0);
        for (Ship ship : ships) {
            do {
                //X and Y coordinates for placing the ship are generated at random
                pos.x = ThreadLocalRandom.current().nextInt(0, myGrid.getColumns());
                pos.y = ThreadLocalRandom.current().nextInt(0, myGrid.getRows());
                //rotates the ship if the number generated is equal to 1
                if (ThreadLocalRandom.current().nextInt(0, 2) == 1) {
                    ship.rotateShip();
                }
            //If the the ship is in a valid position then it is placed    
            } while (!myGrid.checkValidPlace(pos, ship));
            ship.placeShip(pos);
            myGrid.placeShip(pos, ship);
        }
    }

    /**
     * Uses a random number to generate coordinates then attacks that point on the grid.
     */
    public Point takeMove() {
        Point target;
        // Random number is generated for X and Y coordinates. They are used to instantiate a target
        do {
            int x = ThreadLocalRandom.current().nextInt(0, oppGrid.getColumns());
            int y = ThreadLocalRandom.current().nextInt(0, oppGrid.getRows());
            target = new Point(x, y);
            //Process is repeated for as long as the target is either a hit, miss or land tile
        } while (oppGrid.getTile(target) == GridTile.MISS
                || oppGrid.getTile(target) == GridTile.HIT
                || oppGrid.getTile(target) == GridTile.LAND);
        return target;
    }
}