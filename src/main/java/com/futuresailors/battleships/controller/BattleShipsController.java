package com.futuresailors.battleships.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.MainMenuListener;
import com.futuresailors.battleships.view.MainMenuPanel;

public class BattleShipsController {
	
	private final String TITLE = "Battleships";
	private JFrame window;
	
	public BattleShipsController(){
		window = new JFrame(TITLE);
	}
	
	public BattleShipsController(JFrame window){
		this.window = window;
	}
	
	public void start(){
		//Create the JFrame
		setUpWindow();
		showMenu();
	}
	
	public void showMenu(){
		window.getContentPane().removeAll();
		JPanel menuPanel = new MainMenuPanel(UIHelper.getWidth(), UIHelper.getHeight());
		menuPanel.setVisible(true);
		@SuppressWarnings("unused")
		MainMenuListener menuListener = new MainMenuListener(menuPanel, this);
		window.add(menuPanel);
		window.repaint();
	}
	
	//This gets called by the Menu Listener and starts the Single Player Game type.
	//ie. It replaces the window panel & instantiates the appropriate controller for that game type.
	public void startSinglePlayer(){
		System.out.println("BattleShipsController is starting a single player game.");
		PlaceShipsController controller = new PlaceShipsController(window);
	}
	
	private void setUpWindow(){
		window.setSize(UIHelper.getWidth(), UIHelper.getHeight());
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
		
//		window.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent ke) {  // handler
//				if(ke.getKeyCode() == ke.VK_ESCAPE) {
//					System.out.println("Escape Key Detected - Closing the window.");
//					window.dispose();
//				}
//			} 
//		});
	}
}