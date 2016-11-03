package com.futuresailors.battleships.controller;

import java.awt.Point;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.multiplayer.FindPlayerListener;
import com.futuresailors.battleships.view.multiplayer.HostClientPanel;

public class MultiPlayerController implements GameTypeController {

	private JFrame window;
	private HostClientPanel panel;

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

		// Show the user they are waiting for a connection + the private ip
		// their friend needs to enter
		try {
			InetAddress inet = InetAddress.getLocalHost();
			System.out.println(inet.getHostAddress());
		} catch (UnknownHostException e) {
			System.out.println("Unable to get Local Address");
			e.printStackTrace();
		}

		// When someone connects change the panel to the place ships screen.

	}

	public void connect() {

	}

	@Override
	public void returnToMenu() {
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
