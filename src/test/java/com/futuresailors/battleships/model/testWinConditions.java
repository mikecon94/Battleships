package com.futuresailors.battleships.model;

import com.futuresailors.battleships.ai.SimpleAI;

import java.awt.Point;

import junit.framework.TestCase;

public class testWinConditions extends TestCase {
    
    //AI
    private Grid player1 = new Grid(10);
    private Grid player2 = new Grid(10);
    private Ship[] ships;
    private SimpleAI ai;
    
    public void setUp(){
        //Init the ships & AI
        ships = new Ship[5];
        ships[0] = new Ship(5, 1, "/images/ships/Horizontal/1.png");
        ships[1] = new Ship(4, 1, "/images/ships/Horizontal/2.png");
        ships[2] = new Ship(3, 1, "/images/ships/Horizontal/3.png");
        ships[3] = new Ship(3, 1, "/images/ships/Horizontal/5.png");
        ships[4] = new Ship(2, 1, "/images/ships/Horizontal/5.png");
        ai = new SimpleAI(player1, player2, ships);
        ai.placeShips();
    }
    
    public void testWin() {
        for (int y = 0; y < player1.getGrid().length; y++) {
            for (int x = 0; x < player1.getGrid()[y].length; x++) {
                Point p = new Point(y,x);
                System.out.println(p);
                player1.dropBomb(p);
                System.out.println(player1.getTile(p));
            }
        }
        //assert(player1.checkGameOver()==true);
    }
    
    
    

}
