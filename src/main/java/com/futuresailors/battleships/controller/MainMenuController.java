package com.futuresailors.battleships.controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.MainMenuListener;
import com.futuresailors.battleships.view.MainMenuPanel;

/**
 * The Main/First Game Controller which creates & sets up the JFrame Window.
 * It then creates the MainMenuPanel object and adds it to the window.
 * A MainMenuListener is then added to this panel which alerts this controller
 * when a button is clicked via appropriate methods.
 * @author Michael Conroy
 */
public class MainMenuController {
	
	private final String TITLE = "Battleships";
	private JFrame window;
	
	public MainMenuController(){
		window = new JFrame(TITLE);
	}
	
	public MainMenuController(JFrame window){
		this.window = window;
	}
	
	/**
	 * This method should be ran after the object has been instantiated and tells the controller
	 * to set up the window and display the main menu.
	 */
	public void start(){
		//Create the JFrame
		setUpWindow();
		showMenu();
	}
	
	/**
	 * Instantiates the menu panel and adds it to the window after
	 * removing all other components. This will be called by other controllers
	 * when they are finished and the menu needs to be displayed again.
	 */
	public void showMenu(){
		window.getContentPane().removeAll();
		JPanel menuPanel = new MainMenuPanel(UIHelper.getWidth(), UIHelper.getHeight());
		menuPanel.setVisible(true);
		@SuppressWarnings("unused")
		MainMenuListener menuListener = new MainMenuListener(menuPanel, this);
		window.add(menuPanel);
		window.repaint();
	}
	
	/**
	 * Starts the Single Player Game type. It replaces the window panel 
	 * & instantiates the appropriate controller for that game type.
	 */
	public void startGameSelection(){
		GameTypeMenuController controller = new GameTypeMenuController(window);
	}
	public void startMultiplayer(){
		//MultiPlayerController game = new MultiPlayerController(window)
	}
	/**
	 * Opens the rules panel. It replaces the window and instantiates the
	 * correct controller.
	 */
	public void showRules(){
		System.out.println("BattleShipsController is displaying the rules.");
		@SuppressWarnings("unused")
		RulesController controller = new RulesController(window);
	}
	
	/**
	 * Closes the window and exits the application.
	 */
	public void exit(){
		window.dispose();
	}
	
	/**
	 * Sets the JFrame up.
	 */
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
		ImageIcon img = new ImageIcon("src/main/resources/images/background.jpg");
	    window.setIconImage(img.getImage());   
		window.setVisible(true);
	}
}