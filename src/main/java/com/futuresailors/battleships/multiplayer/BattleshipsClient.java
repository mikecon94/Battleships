package com.futuresailors.battleships.multiplayer;

import com.futuresailors.battleships.controller.MultiPlayerController;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

/**
 * Client class for battleships multiplayer.
 * @author Mike Conroy.
 */

public class BattleshipsClient implements BattleshipsConnection {

    private Client client;
    private MultiPlayerController controller;
    private ConnectionComms request;
    
    /**
     * Constructor.
     * @param   controller  MultiplayerController Object.
     */
    public BattleshipsClient(MultiPlayerController controller) {

        this.controller = controller;

        client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(ConnectionComms.class);
        kryo.register(Grid.class);
        kryo.register(GridTile[][].class);
        kryo.register(GridTile[].class);
        kryo.register(GridTile.class);

        client.start();
        addListener();
    }
    
    /**
     * Adds listener to listen for connection.
     */
    private void addListener() {
        client.addListener(new ThreadedListener(new Listener() {
            public void received(Connection connection, Object object) {
                controller.messageReceived(object);
            }

            public void disconnected(Connection connection) {
                controller.oppDisconnected();
            }
        }));
    }
    
    /**
     * Attempts to make a connection on the ports.
     * 
     * @return Boolean - True if it finds a server and connects otherwise false.
     */
    public boolean attemptConnection() {
        List<InetAddress> servers = client.discoverHosts(16914, 500);
        try {
            if (servers.size() > 0) {
                // Attempt to connect to all servers available on the network.
                // Once connected return true to maintain this connection.
                for (InetAddress serverIP : servers) {
                    client.connect(5000, serverIP, 16913, 16914);
                    request = new ConnectionComms();
                    request.connected = true;
                    client.sendTCP(request);
                    // If the server is already connected to a client it will drop this request
                    // So we check we are still connected and if so proceed.
                    Thread.sleep(50);
                    System.out.println("Still Connected: " + client.isConnected());
                    if (client.isConnected()) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to host: " + e);
        } catch (InterruptedException e) {
            System.out.println("Problem whilst sleeping thread: " + e);
        }

        return false;
    }
    
    /**
     * Closes the connection.
     */
    public void close() {
        client.close();
    }
    
    /**
     * Sends the message the server.
     */
    public void sendMessage(Object object) {
        client.sendTCP(object);
    }
}
