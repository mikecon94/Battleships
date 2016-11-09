package com.futuresailors.battleships.controller;

import java.awt.Point;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.multiplayer.ConnectionComms;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;
import com.futuresailors.battleships.view.multiplayer.FindPlayerListener;
import com.futuresailors.battleships.view.multiplayer.HostClientPanel;
import com.futuresailors.battleships.view.multiplayer.WaitingNetworkPanel;

public class MultiPlayerController implements GameTypeController {

	private JFrame window;
	private HostClientPanel panel;

	private Server server;
	private Client client;
	private Kryo kryo;

	private Ship[] myShips;
	private Grid myGrid;
	
	public MultiPlayerController(JFrame window) {
		this.window = window;
		myGrid = new Grid(10);
		createShips();
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
			//Grab the machines hostname to display it to the user.
			InetAddress inet = InetAddress.getLocalHost();
			WaitingNetworkPanel panel = new WaitingNetworkPanel(inet.getHostName(), UIHelper.getWidth(),
					UIHelper.getHeight());
			
			//We don't want this to run if the server can't start.
			window.getContentPane().removeAll();
			@SuppressWarnings("unused")
			GameListener listener = new GameListener(panel, this);
			window.add(panel);
			window.repaint();
		} catch (UnknownHostException e) {
			System.out.println("Unable to get Local Address");
			e.printStackTrace();
		} catch (IOException e){
			System.out.println("Unable to start server.");
			JOptionPane.showMessageDialog(null, "Are you already running a server on port 16913?",
					"Unable to start server", JOptionPane.INFORMATION_MESSAGE);
		}

		// When someone connects change the panel to the place ships screen.
		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				System.out.println("Server Connection: " + connection.getID());
				if(connection.getID() == 1){
					if (object instanceof ConnectionComms) {
						ConnectionComms request = (ConnectionComms) object;
						System.out.println("Connection: " + request.text);
						ConnectionComms response = new ConnectionComms();
						response.text = "Connected.";
						connection.sendTCP(response);
						displayPlaceShipsPanel();
					}
				} else {
					connection.close();
				}
			}
		});
	}
	
	private void displayPlaceShipsPanel(){
		window.getContentPane().removeAll();
		PlaceShipsPanel panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight(), myGrid, myShips);
		window.add(panel);
		window.repaint();
		@SuppressWarnings("unused")
		GameListener listener = new GameListener(panel, this);
		System.out.println("Displaying place ships.");
	}

	private void initialiseServer() throws IOException {
		server = new Server();
		kryo = server.getKryo();
		kryo.register(ConnectionComms.class);

		server.start();
	
		//These port numbers were chosen as the 16/09/2013 is when we joined Capgemini.
		server.bind(16913, 16914);
		System.out.println("Server started and listening on ports 16913 & 16914");
	}

	public void connect() {
		client = new Client();
		Kryo kryo = client.getKryo();
		kryo.register(ConnectionComms.class);
		ConnectionComms request = new ConnectionComms();

		client.start();

		try {
			client.connect(5000, panel.getConnectIP(), 16913, 16914);
			//If successful take them to the placeships panel.
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		}

		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				System.out.println("Tada");
				if (object instanceof ConnectionComms) {
					ConnectionComms response = (ConnectionComms) object;
					System.out.println("Response: " + response.text);
					displayPlaceShipsPanel();
				}
			}
		});
//
//		new Thread() {
//			public void run() {
//				int count = 0;
//				while (true) {
					request.text = "Display PlaceShips";
					client.sendTCP(request);
//					try {
//						Thread.sleep(500);
//						Thread.yield();
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					count++;
//				}
//			}
//		}.start();
	}

	@Override
	public void returnToMenu() {
		if (server != null) {
			server.close();
		}
		
		if(client != null){
			client.close();
		}
		
		MainMenuController main = new MainMenuController(window);
		main.showMenu();
	}

	@Override
	public void mouseClicked(Point pos) {
	}

	@Override
	public void mouseMoved(Point pos) {
	}

	@Override
	public void startGame() {
	}
	
	private void createShips() {
		// TODO make the ships a configurable.
		myShips = new Ship[5];

		myShips[0] = new Ship(5, 1, "/images/ships/horizontal/1.png");
		myShips[1] = new Ship(4, 1, "/images/ships/horizontal/2.png");
		myShips[2] = new Ship(3, 1, "/images/ships/horizontal/3.png");
		myShips[3] = new Ship(3, 1, "/images/ships/horizontal/5.png");
		myShips[4] = new Ship(2, 1, "/images/ships/horizontal/5.png");
	}
}