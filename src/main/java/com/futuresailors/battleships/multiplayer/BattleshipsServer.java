package com.futuresailors.battleships.multiplayer;

import com.futuresailors.battleships.controller.MultiPlayerController;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.esotericsoftware.kryonet.Server;

public class BattleshipsServer implements BattleshipsConnection {

    private Server server;
    private Kryo kryo;
    private MultiPlayerController controller;

    public BattleshipsServer(MultiPlayerController controller) {

        this.controller = controller;

        server = new Server();
        kryo = server.getKryo();
        kryo.register(ConnectionComms.class);
        kryo.register(Grid.class);
        kryo.register(GridTile[][].class);
        kryo.register(GridTile[].class);
        kryo.register(GridTile.class);
        
        addListener();
        server.start();

        // These port numbers were chosen as the 16/09/2013 is when we joined Capgemini.
        try {
            server.bind(16913, 16914);
            System.out.println("Server started and listening on ports 16913 & 16914");
        } catch (IOException e) {
            System.out.println("Unable to start server.");
            JOptionPane.showMessageDialog(null, "Are you already running a server on port 16913?",
                    "Unable to start server", JOptionPane.INFORMATION_MESSAGE);
            controller.returnToMenu();
        }

    }

    private void addListener() {
        server.addListener(new ThreadedListener(new Listener() {
            public void received(Connection connection, Object object) {
                // We only want one user connecting.
                if (connection.getID() == 1) {
                    controller.messageReceived(object);
                } else {
                    connection.close();
                }
            }

            public void disconnected(Connection connection) {
                if (connection.getID() == 1) {
                    controller.oppDisconnected();
                }
            }
        }));
    }

    public void close() {
        server.close();
    }

    public void sendMessage(Object object) {
        server.sendToTCP(1, object);
    }
}
