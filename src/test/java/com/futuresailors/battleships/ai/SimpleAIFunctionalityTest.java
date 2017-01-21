package com.futuresailors.battleships.ai;

import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class SimpleAIFunctionalityTest extends TestCase{
    //Human
    private Grid player1 = new Grid(10);
    //AI
    private Grid player2 = new Grid(10);
    private Ship[] ships;
    private SimpleAI ai;
    
    @Before 
    public void setUp(){
        //Init the ships & AI
        ships = new Ship[5];
        ships[0] = new Ship(5, 1, "/images/ships/Horizontal/1.png");
        ships[1] = new Ship(4, 1, "/images/ships/Horizontal/2.png");
        ships[2] = new Ship(3, 1, "/images/ships/Horizontal/3.png");
        ships[3] = new Ship(3, 1, "/images/ships/Horizontal/5.png");
        ships[4] = new Ship(2, 1, "/images/ships/Horizontal/5.png");
        ai = new SimpleAI(player1, player2, ships);
    }
    
    @Test
    public void testRandomMove(){
        ai.placeShips();
        Point pos1 = ai.takeMove();
        player2.dropBomb(pos1);
        Point pos2 = ai.takeMove();
        player2.dropBomb(pos2);
        assert(!pos1.equals(pos2));
    }
    
    //Not sure how to test this.
    @Test
    public void testPlaceShips(){
        
        ai.placeShips();
        System.out.println(ships[0].getPos());
        System.out.println(ships[0].getPos());
        System.out.println(ships[2].getPos());
        System.out.println(ships[3].getPos());
        System.out.println(ships[4].getPos());
        
        //assert(ships[0].getPos().equals(ships[1].getPos()));
        
    }
}
