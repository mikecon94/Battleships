package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.multiplayer.BattleshipsClient;
import com.futuresailors.battleships.multiplayer.BattleshipsConnection;
import com.futuresailors.battleships.multiplayer.BattleshipsServer;
import com.futuresailors.battleships.multiplayer.ConnectionComms;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;
import com.futuresailors.battleships.view.PlayPanel;
import com.futuresailors.battleships.view.multiplayer.AwaitingOpponentListener;
import com.futuresailors.battleships.view.multiplayer.AwaitingOpponentPanel;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This controller sets up the server and client connection to allow networked multiplayer to take
 * place. Upon connection it will then begin the game and allow 2 players to play against each
 * other.
 * 
 * @author Michael Conroy
 */
public class MultiPlayerController implements GameTypeController {

    private JFrame window;
    private AwaitingOpponentPanel connectPanel;
    private PlayPanel playPanel;

    private BattleshipsConnection multiplayer;

    private boolean imClient;

    // This is used to determine whether the user purposefully dropped the connection or not.
    // It gets set to true when they have and will therefore not alert the user the connection has
    // been dropped.
    private boolean connectionClosed = false;

    private boolean oppReady = false;
    private boolean imReady = false;
    private boolean started = false;
    private boolean gameOver = false;
    private boolean gridsInitialised = false;

    private boolean myTurn = false;

    private Ship[] myShips;
    private Grid myGrid;
    private Grid oppGrid = new Grid();

    // TODO Add the reloaded mode to Multiplayer.

    public MultiPlayerController(JFrame window) {
        this.window = window;
        myGrid = new Grid(10);
        addPanel();

        // Start client and discoverHost for server
        startClient();
        if (((BattleshipsClient) multiplayer).attemptConnection()) {
            // We are now connected to the server.
            imClient = true;
        } else {
            // Start the server
            // Wait for connection.
            imClient = false;
            startServer();
        }
    }

    public void startServer() {
        multiplayer = new BattleshipsServer(this);
    }

    private void startClient() {
        multiplayer = new BattleshipsClient(this);
    }

    public void messageReceived(Object object) {
        if (object instanceof ConnectionComms) {
            ConnectionComms message = (ConnectionComms) object;
            if (message.connected) {
                // Server needs to respond with the same object for confirmation
                // of connection
                if (!imClient) {
                    multiplayer.sendMessage(message);
                }
                displayPlaceShipsPanel();
            } else if (message.shipsPlaced) {
                // The opponents ships are placed.
                oppReady = true;
                // Server decides who goes first.
                if (imClient) {
                    myTurn = !message.myTurn;
                }
                if (imReady) {
                    // Both of us are ready -> move to play panel.
                    System.out.println("Ready to start the game");
                    begin();
                }
            } else if (started && !gameOver) {
                myTurn = !message.myTurn;
                playPanel.setMyTurn(myTurn);
            }
        } else if (object instanceof Grid) {
            if (started && gridsInitialised) {
                myGrid = (Grid) object;
                playPanel.setMyGrid(myGrid);
                playPanel.repaint();
                checkGameOver();
            } else if (started) {
                // Initialises the opponents grid.
                // May change this into a wrapper object containing the grid, ships and
                // Whether the turn is over.
                gridsInitialised = true;
                oppGrid = (Grid) object;
                playPanel.setOppGrid(oppGrid);
                System.out.println("Client has received opps grid.");
            }
        }
    }

    public void returnToMenu() {
        connectionClosed = true;

        multiplayer.close();

        MainMenuController main = new MainMenuController(window);
        main.showMenu();
    }

    public void mouseClicked(Point pos) {
        if (started && gridsInitialised && !gameOver && myTurn
                && playPanel.overGridSpace(pos.x, pos.y)) {

            Point gridPos = new Point(playPanel.getTileXUnderMouse(pos.x),
                    playPanel.getTileYUnderMouse(pos.y));

            if (oppGrid.getTile(gridPos) != GridTile.MISS
                    && oppGrid.getTile(gridPos) != GridTile.HIT
                    && oppGrid.getTile(gridPos) != GridTile.LAND) {
                System.out.println("My Move: " + gridPos);
                if (oppGrid.dropBomb(gridPos)) {
                    multiplayer.sendMessage(oppGrid);
                    checkGameOver();
                    playPanel.repaint();
                } else {
                    myTurn = false;
                    playPanel.setMyTurn(myTurn);
                    oppGrid.clearHoverTiles();
                    ConnectionComms setTurn = new ConnectionComms();
                    setTurn.myTurn = myTurn;
                    multiplayer.sendMessage(oppGrid);
                    multiplayer.sendMessage(setTurn);

                    playPanel.repaint();
                    checkGameOver();
                }
            }
        }
    }

    public void startGame() {
        // Check the user hasn't already clicked once.
        if (imReady != true) {
            imReady = true;
            ConnectionComms readyMessage = new ConnectionComms();
            readyMessage.shipsPlaced = true;
            if (imClient) {
                multiplayer.sendMessage(readyMessage);
            } else {
                // Server chooses the first player.
                myTurn = ThreadLocalRandom.current().nextBoolean();
                readyMessage.myTurn = myTurn;
                multiplayer.sendMessage(readyMessage);
            }

            // In this case the opposition has already told us that their ships are placed.
            if (oppReady) {
                System.out.println("Ready To Start Game.");
                begin();
            }
        }
    }

    public void oppDisconnected() {
        if (!connectionClosed) {
            JOptionPane.showMessageDialog(null, "The connection to the opposition has been lost.",
                    "Opponent Disconnected", JOptionPane.INFORMATION_MESSAGE);
            returnToMenu();
        }
    }

    public void displayPlaceShipsPanel() {
        createShips();

        window.getContentPane().removeAll();
        PlaceShipsPanel panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight(),
                myGrid, myShips);
        window.add(panel);
        window.repaint();
        GameListener listener = new GameListener(panel, this);
        PlaceShipsController placeShips = new PlaceShipsController(myGrid, myShips, this, window);
    }

    public void mouseMoved(Point pos) {
        if (started) {
            if (myTurn && !gameOver && playPanel.overGridSpace(pos.x, pos.y)) {
                oppGrid.hoverBomb(new Point(playPanel.getTileXUnderMouse(pos.x),
                        playPanel.getTileYUnderMouse(pos.y)));
                playPanel.repaint();
            } else {
                oppGrid.clearHoverTiles();
                playPanel.repaint();
            }
        }
    }

    private void begin() {
        addGamePanel();
        playPanel.setMyTurn(myTurn);
        started = true;
        multiplayer.sendMessage(myGrid);
    }

    private void addPanel() {
        window.getContentPane().removeAll();
        connectPanel = new AwaitingOpponentPanel(UIHelper.getWidth(), UIHelper.getHeight());
        window.add(connectPanel);
        window.repaint();
        @SuppressWarnings("unused")
        AwaitingOpponentListener listener = new AwaitingOpponentListener(connectPanel, this);
    }

    private void addGamePanel() {
        window.getContentPane().removeAll();
        playPanel = new PlayPanel(UIHelper.getWidth(), UIHelper.getHeight(), myGrid, oppGrid,
                myShips);
        window.add(playPanel);
        window.repaint();
        @SuppressWarnings("unused")
        GameListener listener = new GameListener(playPanel, this);
    }

    private void checkGameOver() {
        if (myTurn) {
            if (oppGrid.checkGameOver()) {
                System.out.println("Game Over: Player Wins.");
                gameOver = true;
                playPanel.showWinner(myTurn);
                returnToMenu();
            }
        } else if (myGrid.checkGameOver()) {
            System.out.println("Game Over: Opponent Wins.");
            gameOver = true;
            playPanel.showWinner(myTurn);
            returnToMenu();
        }
    }

    private void createShips() {
        myShips = new Ship[5];
        myShips[0] = new Ship(5, 1, "/images/ships/Horizontal/1.png");
        myShips[1] = new Ship(4, 1, "/images/ships/Horizontal/2.png");
        myShips[2] = new Ship(3, 1, "/images/ships/Horizontal/3.png");
        myShips[3] = new Ship(3, 1, "/images/ships/Horizontal/5.png");
        myShips[4] = new Ship(2, 1, "/images/ships/Horizontal/5.png");
    }
}