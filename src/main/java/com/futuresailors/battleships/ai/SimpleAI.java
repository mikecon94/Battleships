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

    public SimpleAI(Grid myGrid, Grid oppGrid, Ship[] ships) {
        this.myGrid = myGrid;
        this.oppGrid = oppGrid;
        this.ships = ships;
    }
    
    public void placeShips() {
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

    public Point takeMove() {
        Point target;
        do {
            int x = ThreadLocalRandom.current().nextInt(0, oppGrid.getColumns());
            int y = ThreadLocalRandom.current().nextInt(0, oppGrid.getRows());
            target = new Point(x, y);
        } while (oppGrid.getTile(target) == GridTile.MISS
                || oppGrid.getTile(target) == GridTile.HIT);
        return target;
    }
}