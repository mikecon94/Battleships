package com.futuresailors.battleships.controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.futuresailors.battleships.view.MainMenuListener;
import com.futuresailors.battleships.view.MainMenuPanel;
import com.futuresailors.battleships.view.PlaceShipsPanel;

public class BattleShipsController {
	
	private final int WIDTH = 1280;
	private final int HEIGHT = 720;
	private final String TITLE = "Battleships";
	private JFrame window = new JFrame(TITLE);
	
	public void start(){
		
		//Create the JFrame
		setUpWindow();
		JPanel menuPanel = new MainMenuPanel(WIDTH, HEIGHT);
		@SuppressWarnings("unused")
		MainMenuListener menuListener = new MainMenuListener(menuPanel, this);
		window.add(menuPanel);
		window.repaint();
		
		//0 is start game
		//window.getContentPane().getComponent(0).addMouseListener(new MainMenuListener(this));
	}
	
	//This gets called by the Menu Listener and starts the Single Player Game type.
	//ie. It replaces the window panel & instantiates the appropriate controller for that game type.
	public void startSinglePlayer(){
		System.out.println("BattleShipsController is starting a single player game.");
		window.getContentPane().removeAll();
		window.add(new PlaceShipsPanel(WIDTH, HEIGHT));
		window.repaint();
	}
	
	private void setUpWindow(){
		window.setSize(WIDTH, HEIGHT);
		//Centres the window
		window.setLocationRelativeTo(null);
		//If the above line doesn't work on all platforms we can use the following lines.
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
		window.setResizable(false);
		//End the program on close.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("src/main/resources/background.jpg");
	    window.setIconImage(img.getImage());   
		window.setVisible(true);
	}
	
}