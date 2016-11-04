package com.futuresailors.battleships.controller;

import java.awt.Point;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.multiplayer.ConnectionComms;
import com.futuresailors.battleships.view.multiplayer.FindPlayerListener;
import com.futuresailors.battleships.view.multiplayer.HostClientPanel;

public class MultiPlayerController implements GameTypeController {

	private JFrame window;
	private HostClientPanel panel;

	private Server server;
	private Kryo kryo;

	public MultiPlayerController(JFrame window) {
		this.window = window;
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

		// Begin the server.
		// - Only allow one connection
		initialiseServer();

		// Show the user they are waiting for a connection + the private ip
		// their friend needs to enter
		try {
			InetAddress inet = InetAddress.getLocalHost();
			System.out.println(inet.getHostName());
		} catch (UnknownHostException e) {
			System.out.println("Unable to get Local Address");
			e.printStackTrace();
		}

		// When someone connects change the panel to the place ships screen.

	}

	private void initialiseServer() {
		server = new Server();
		kryo = server.getKryo();
		kryo.register(ConnectionComms.class);

		server.start();
		try {
			server.bind(54555, 54777);
			System.out.println("Server started and listening on ports 54555 & 54777");
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
						}
					} else {
						connection.close();
					}
				}
			});
		} catch (IOException e) {
			System.out.println("Unable to start server");
			e.printStackTrace();
		}
	}

	public void connect() {
		final Client client = new Client();
		Kryo kryo = client.getKryo();
		kryo.register(ConnectionComms.class);
		ConnectionComms request = new ConnectionComms();

		client.start();

		try {
			client.connect(5000, panel.getConnectIP(), 54555, 54777);
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		}

		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof ConnectionComms) {
					ConnectionComms response = (ConnectionComms) object;
					System.out.println("Response: " + response.text);
				}
			}
		});

		new Thread(){
			public void run(){
				int count = 0;
				while(true){
					request.text = "Hello, world!" + count;
					client.sendTCP(request);
					try {
						Thread.sleep(500);
						Thread.yield();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
			}
		}.start();
	}

	@Override
	public void returnToMenu() {
		if(server != null){
			server.close();
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
}
