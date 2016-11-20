package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.ai.AI;
import com.futuresailors.battleships.ai.AdvancedAI;
import com.futuresailors.battleships.ai.ModerateAI;
import com.futuresailors.battleships.ai.SimpleAI;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.DifficultySelectionListener;
import com.futuresailors.battleships.view.DifficultySelectionPanel;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlayPanel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Controller for the main game in single player mode. This creates the ships for the opponent
 * ,creates the opponents grid and initialises the AI chosen by the player.
 * 
 * @author Michael Conroy, Joe Baldwin
 */
public class SinglePlayerController implements GameTypeController {

    private JFrame window;
    private PlayPanel panel;

    private Ship[] myShips;
    private Grid myGrid;

    private Ship[] aiShips;
    private Grid aiGrid;
    AI opp;

    private boolean gameOver = false;
    private boolean myTurn = false;

    private Timer timer;

    public SinglePlayerController(JFrame window) {
        this.window = window;
        // TODO Make 10 a configurable for different Grid sizes
        myGrid = new Grid(10);
        aiGrid = new Grid(10);
        // Initialises the ships and defines what ships will be used in this game.
        createShips();
        window.getContentPane().removeAll();
        DifficultySelectionPanel diffPanel = new DifficultySelectionPanel(UIHelper.getWidth(),
                UIHelper.getHeight());
        window.add(diffPanel);
        window.repaint();
        @SuppressWarnings("unused")
        DifficultySelectionListener diffListener = new DifficultySelectionListener(diffPanel, this);
    }

    /**
     * Defines the ships that will be used in this game.
     */
    private void createShips() {
        // TODO make the ships a configurable.
        myShips = new Ship[5];

        myShips[0] = new Ship(5, 1, "/images/ships/Horizontal/1.png");
        myShips[1] = new Ship(4, 1, "/images/ships/Horizontal/2.png");
        myShips[2] = new Ship(3, 1, "/images/ships/Horizontal/3.png");
        myShips[3] = new Ship(3, 1, "/images/ships/Horizontal/5.png");
        myShips[4] = new Ship(2, 1, "/images/ships/Horizontal/5.png");

        aiShips = new Ship[5];
        aiShips[0] = new Ship(5, 1, "/images/ships/Horizontal/1.png");
        aiShips[1] = new Ship(4, 1, "/images/ships/Horizontal/2.png");
        aiShips[2] = new Ship(3, 1, "/images/ships/Horizontal/3.png");
        aiShips[3] = new Ship(3, 1, "/images/ships/Horizontal/5.png");
        aiShips[4] = new Ship(2, 1, "/images/ships/Horizontal/5.png");
    }

    public void startGame() {
        myGrid.clearHoverTiles();
        aiGrid.clearHoverTiles();
        opp.placeShips();
        chooseFirstPlayer();
        addGamePanel();
        if (myTurn == false) {
            opponentMove(0);
        }
        panel.setMyTurn(myTurn);
    }

    @SuppressWarnings("unused")
    public void selectHardMode() {
        opp = new AdvancedAI(aiGrid, myGrid, aiShips);
        PlaceShipsController placeShips = new PlaceShipsController(myGrid, myShips, this, window);
    }

    @SuppressWarnings("unused")
    public void selectModerateMode() {
        opp = new ModerateAI(aiGrid, myGrid, aiShips);
        PlaceShipsController placeShips = new PlaceShipsController(myGrid, myShips, this, window);
    }

    @SuppressWarnings("unused")
    public void selectEasyMode() {
        opp = new SimpleAI(aiGrid, myGrid, aiShips);
        PlaceShipsController placeShips = new PlaceShipsController(myGrid, myShips, this, window);
    }

    /**
     * Randomly chooses which player will go first.
     */
    private void chooseFirstPlayer() {
        myTurn = ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Creates the game panel.
     */
    private void addGamePanel() {
        window.getContentPane().removeAll();
        panel = new PlayPanel(UIHelper.getWidth(), UIHelper.getHeight(), myGrid, aiGrid, myShips);
        window.add(panel);
        window.repaint();
        @SuppressWarnings("unused")
        GameListener listener = new GameListener(panel, this);
    }

    public void returnToMenu() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        MainMenuController main = new MainMenuController(window);
        main.showMenu();
        // TODO Display a JOptionPane asking if the user is sure they wish to
        // return to the menu.
    }

    public void mouseClicked(Point pos) {
        if (!gameOver) {
            // Hover the tile if it is the users turn and the mouse is over the
            // AIs grid.
            Point gridPos = new Point(panel.getTileXUnderMouse(pos.x),
                    panel.getTileYUnderMouse(pos.y));
            if (myTurn && panel.overGridSpace(pos.x, pos.y)
                    && aiGrid.getTile(gridPos) != GridTile.MISS
                    && aiGrid.getTile(gridPos) != GridTile.HIT) {
                System.out.println("My Move: " + gridPos);
                if (aiGrid.dropBomb(gridPos)) {
                    checkGameOver();
                    panel.repaint();
                } else {
                    myTurn = false;
                    panel.setMyTurn(myTurn);
                    aiGrid.clearHoverTiles();
                    panel.repaint();
                    opponentMove(0);
                }
            }
        }
    }

    private void opponentMove(final int moveNum) {
        // Don't delay the AIs move on their first go (ie. only after they hit something).
        if (moveNum > 0) {
            // Add an artificial delay to prevent the AI appearing to drop a load of bombs at once
            // if it hits a ship.
            //TODO Update this so it is initialised only once instead of every time it's the 
            //opponents move.
            timer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    Point target = opp.takeMove();
                    System.out.println("Opp Move: " + target);
                    if (myGrid.dropBomb(target)) {
                        checkGameOver();
                        panel.setMyTurn(myTurn);
                        panel.repaint();
                        opponentMove(moveNum + 1);
                    } else {
                        myTurn = true;
                        panel.setMyTurn(myTurn);
                        panel.repaint();
                    }
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            Point target = opp.takeMove();
            System.out.println("Opp Move: " + target);
            if (myGrid.dropBomb(target)) {
                checkGameOver();
                panel.repaint();
                opponentMove(moveNum + 1);
            } else {
                myTurn = true;
                panel.setMyTurn(myTurn);
                panel.repaint();
            }
        }
    }

    private void checkGameOver() {
        if (myTurn) {
            if (aiGrid.checkGameOver()) {
                System.out.println("Game Over: Player Wins.");
                gameOver = true;
                panel.showWinner(myTurn);
                returnToMenu();
            }
        } else {
            if (myGrid.checkGameOver()) {
                System.out.println("Game Over: AI Wins.");
                gameOver = true;
                panel.showWinner(myTurn);
                returnToMenu();
            }
        }
    }

    public void mouseMoved(Point pos) {
        // Hover the tile if it is the users turn and the mouse is over the AIs
        // grid.
        if (!gameOver && myTurn && panel.overGridSpace(pos.x, pos.y)) {
            aiGrid.hoverBomb(
                    new Point(panel.getTileXUnderMouse(pos.x), panel.getTileYUnderMouse(pos.y)));
        } else {
            aiGrid.clearHoverTiles();
        }
        panel.repaint();
    }
}