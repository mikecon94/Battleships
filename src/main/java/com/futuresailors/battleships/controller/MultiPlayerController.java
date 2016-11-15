package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.multiplayer.ConnectionComms;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;
import com.futuresailors.battleships.view.multiplayer.FindPlayerListener;
import com.futuresailors.battleships.view.multiplayer.HostClientPanel;
import com.futuresailors.battleships.view.multiplayer.WaitingNetworkPanel;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.esotericsoftware.kryonet.Server;

import java.awt.Point;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MultiPlayerController implements GameTypeController {

    private JFrame window;
    private HostClientPanel panel;

    private Server server;
    private Client client;
    private Kryo kryo;

    // This is used to determine whether the user purposefully dropped the connection or not.
    // It gets set to true when they have and will therefore not alert the user the connection has
    // been dropped.
    private boolean connectionClosed = false;

    private boolean oppReady = false;
    private boolean imReady = false;

    private Ship[] myShips;
    private Grid myGrid;

    public MultiPlayerController(JFrame window) {
        this.window = window;
        myGrid = new Grid(10);
        addPanel();
    }

    private void addPanel() {
        window.getContentPane().removeAll();
        panel = new HostClientPanel(UIHelper.getWidth(), UIHelper.getHeight());
        window.add(panel);
        window.repaint();
        @SuppressWarnings("unused")
        FindPlayerListener listener = new FindPlayerListener(panel, this);
    }

    public void startServer() {
        // TODO When start server button is clicked the next option panel should
        // appear asking whether classic game or reloaded with map size/shape
        // etc.

        try {
            // Begin the server.
            initialiseServer();
            // Grab the machines hostname to display it to the user.
            InetAddress inet = InetAddress.getLocalHost();
            WaitingNetworkPanel panel = new WaitingNetworkPanel(inet.getHostAddress(),
                    UIHelper.getWidth(), UIHelper.getHeight());

            // We don't want this to run if the server can't start.
            window.getContentPane().removeAll();
            @SuppressWarnings("unused")
            GameListener listener = new GameListener(panel, this);
            window.add(panel);
            window.repaint();
        } catch (UnknownHostException e) {
            System.out.println("Unable to get Local Address");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to start server.");
            JOptionPane.showMessageDialog(null, "Are you already running a server on port 16913?",
                    "Unable to start server", JOptionPane.INFORMATION_MESSAGE);
        }

        // When someone connects change the panel to the place ships screen.
        server.addListener(new ThreadedListener(new Listener() {
            public void received(Connection connection, Object object) {
                // We only want one user connecting.
                if (connection.getID() == 1) {
                    if (object instanceof ConnectionComms) {
                        ConnectionComms message = (ConnectionComms) object;
                        System.out.println("Message From Client: " + message.text);
                        if (message.text.equals("Connected")) {
                            // Reply with the same message so the client knows we have connected.
                            server.sendToTCP(1, message);
                            displayPlaceShipsPanel();
                        } else if (message.text.equals("Ships placed")) {
                            oppReady = true;
                            if (imReady) {
                                // Both of us are ready -> move to play panel.
                                System.out.println("Ready to start game");
                            }
                        }
                    }
                } else {
                    connection.close();
                }
            }

            public void disconnected(Connection connection) {
                if (connection.getID() == 1 && connectionClosed == false) {
                    JOptionPane.showMessageDialog(null,
                            "The connection to the opposition has been lost.",
                            "Opponent Disconnected", JOptionPane.INFORMATION_MESSAGE);
                    returnToMenu();
                }
            }
        }));
    }

    @SuppressWarnings("unused")
    private void displayPlaceShipsPanel() {
        createShips();

        window.getContentPane().removeAll();
        PlaceShipsPanel panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight(),
                myGrid, myShips);
        window.add(panel);
        window.repaint();
        GameListener listener = new GameListener(panel, this);
        PlaceShipsController placeShips = new PlaceShipsController(myGrid, myShips, this, window);
    }

    private void initialiseServer() throws IOException {
        server = new Server();
        kryo = server.getKryo();
        kryo.register(ConnectionComms.class);
        kryo.register(Grid.class);
        server.start();

        // These port numbers were chosen as the 16/09/2013 is when we joined Capgemini.
        server.bind(16913, 16914);
        System.out.println("Server started and listening on ports 16913 & 16914");
    }

    public void connect() {
        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(ConnectionComms.class);
        kryo.register(Grid.class);
        ConnectionComms request = new ConnectionComms();

        client.start();

        try {
            client.connect(5000, panel.getConnectIP(), 16913, 16914);
            // If successful take them to the placeships panel.
        } catch (IOException e) {
            System.out.println("Unable to connect to host: " + e);
            JOptionPane.showMessageDialog(null, "Are you sure the IP Address is correct?",
                    "Unable to connect to server", JOptionPane.INFORMATION_MESSAGE);
        }

        client.addListener(new ThreadedListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof ConnectionComms) {
                    ConnectionComms message = (ConnectionComms) object;
                    System.out.println("Message From Server: " + message.text);
                    if (message.text.equals("Connected")) {
                        displayPlaceShipsPanel();
                    } else if (message.text.equals("Ships placed")) {
                        oppReady = true;
                        if (imReady) {
                            // Both of us are ready -> move to play panel.
                            System.out.println("Ready to start game");
                        }
                    }
                }
            }

            public void disconnected(Connection connection) {
                if (connection.getID() == 1 && connectionClosed == false) {
                    JOptionPane.showMessageDialog(null,
                            "The connection to the opposition has been lost.",
                            "Opponent Disconnected", JOptionPane.INFORMATION_MESSAGE);
                    returnToMenu();
                }
            }
        }));

        request.text = "Connected";
        client.sendTCP(request);
    }

    public void returnToMenu() {
        connectionClosed = true;
        if (server != null) {
            server.close();
        }

        if (client != null) {
            client.close();
        }

        MainMenuController main = new MainMenuController(window);
        main.showMenu();
    }

    public void mouseClicked(Point pos) {
    }

    public void mouseMoved(Point pos) {
    }

    public void startGame() {
        // Check the user hasn't already clicked once.
        // TODO Update the panel with a message so the user knows they are waiting for the opponent.
        if (imReady == true) {
            ConnectionComms readyMessage = new ConnectionComms();
            readyMessage.text = "Ships placed";
            if (server != null) {
                server.sendToTCP(1, readyMessage);
            } else if (client != null) {
                client.sendTCP(readyMessage);
            }

            // In this case the opposition has already told us that their ships are placed.
            if (oppReady) {
                System.out.println("Ready To Start Game.");
            }
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