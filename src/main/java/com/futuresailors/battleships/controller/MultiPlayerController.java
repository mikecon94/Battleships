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
import com.futuresailors.battleships.view.PlayPanel;
import com.futuresailors.battleships.view.multiplayer.AwaitingOpponentListener;
import com.futuresailors.battleships.view.multiplayer.AwaitingOpponentPanel;

import java.awt.Point;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

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

    // We first start the client to search for servers.
    private boolean imClient = true;

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

    // TODO Add the Reloaded mode to Multiplayer.
    
    /**
     * Constructor.
     * @param   window  JFrame of the window.
     */
    public MultiPlayerController(JFrame window) {
        this.window = window;
        myGrid = new Grid(10);
        addPanel();
        initialiseConnection();
    }
    
    /**
     * Initalises the connection between client and server.
     */
    private void initialiseConnection() {
        // Start client and discoverHost for server
        multiplayer = new BattleshipsClient(this);

        final SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                boolean isConnected = ((BattleshipsClient) multiplayer).attemptConnection();
                // publish(isConnected);
                return isConnected;
            }

            @Override
            protected void done() {
                try {
                    setConnection(this.get().booleanValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    // We need to wait for the attemptConnection method to finish running before we continue.
    // This method allows the attemptConnection to call back to this object for the Main thread to
    // continue processing.
    
    /**
     * Sets the connection up.
     * @param   connection  boolean as whether the connection is done.
     */
    public void setConnection(boolean connection) {
        if (connection) {
            // We are now connected to the server.
            imClient = true;
        } else {
            // Start the server
            // Wait for connection.
            imClient = false;
            multiplayer = new BattleshipsServer(this);
        }
    }
    
    /**
     * Handles any messages recieved.
     * @param   object  The message from the server.
     */
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
                System.out.println("Received opps grid.");

                // Client responds with their grid
                if (imClient) {
                    multiplayer.sendMessage(myGrid);
                }
            }
        }
    }
    
    /**
     * Return to the menu.
     */
    public void returnToMenu() {
        connectionClosed = true;

        multiplayer.close();

        MainMenuController main = new MainMenuController(window);
        main.showMenu();
    }
    
    /**
     * Handles mouse clicks.
     * @param   pos     Point of the click.
     */
    public void mouseClicked(Point pos) {
        if (started && !gameOver && myTurn && gridsInitialised
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
    
    /**
     * Initalises the game.
     */
    public void startGame() {
        // Check the user hasn't already clicked once.
        if (!imReady) {
            imReady = true;

            ConnectionComms readyMessage = new ConnectionComms();
            readyMessage.shipsPlaced = true;
            // The server decides who goes first randomly.
            if (!imClient) {
                myTurn = ThreadLocalRandom.current().nextBoolean();
                readyMessage.myTurn = myTurn;
            }
            multiplayer.sendMessage(readyMessage);

            // In this case the opposition has already told us that their ships are placed.
            // So we know we can begin playing.
            if (oppReady) {
                System.out.println("Ready To Start Game.");
                begin();
            }
        }
    }
    
    /**
     * Handles disconnection.
     */
    public void oppDisconnected() {
        if (!connectionClosed) {
            JOptionPane.showMessageDialog(null, "The connection to the opposition has been lost.",
                    "Opponent Disconnected", JOptionPane.INFORMATION_MESSAGE);
            returnToMenu();
        }
    }
    
    /**
     * Displays the place ships panel.
     */
    public void displayPlaceShipsPanel() {
        createShips();

        // PlaceShipsPanel panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight(),
        // myGrid, myShips);

        @SuppressWarnings("unused")
        PlaceShipsController placeShips = new PlaceShipsController(myGrid, myShips, this, window);

        // window.getContentPane().removeAll();
        // window.add(panel);
        // window.repaint();
    }
    
    /**
     * Handles mouse movement.
     * @param   pos     New point of the mouse.
     */
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
    
    /**
     * Begins the game.
     */
    private void begin() {
        System.out.println("Client: " + imClient + " Beginning game.");
        addGamePanel();
        playPanel.setMyTurn(myTurn);
        started = true;
        // Server will send the grid first.
        if (!imClient) {
            System.out.println("Server is sending grid.");
            multiplayer.sendMessage(myGrid);
        }
    }
    
    /**
     * Adds the awaiting opponent panel to game.
     */
    private void addPanel() {
        window.getContentPane().removeAll();
        connectPanel = new AwaitingOpponentPanel(UIHelper.getWidth(), UIHelper.getHeight());
        @SuppressWarnings("unused")
        AwaitingOpponentListener listener = new AwaitingOpponentListener(connectPanel, this);
        window.add(connectPanel);
        window.repaint();
    }
    
    /**
     * Adds the main game panel.
     */
    private void addGamePanel() {
        window.getContentPane().removeAll();
        playPanel = new PlayPanel(UIHelper.getWidth(), UIHelper.getHeight(), myGrid, oppGrid,
                myShips);
        window.add(playPanel);
        window.repaint();
        @SuppressWarnings("unused")
        GameListener listener = new GameListener(playPanel, this);
    }
    
    /**
     * Checks if the game is over.
     */
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
    
    /**
     * Creates the ships.
     */
    private void createShips() {
        myShips = new Ship[5];
        myShips[0] = new Ship(5, 1, "/images/ships/Horizontal/1.png");
        myShips[1] = new Ship(4, 1, "/images/ships/Horizontal/2.png");
        myShips[2] = new Ship(3, 1, "/images/ships/Horizontal/3.png");
        myShips[3] = new Ship(3, 1, "/images/ships/Horizontal/5.png");
        myShips[4] = new Ship(2, 1, "/images/ships/Horizontal/5.png");
    }
}