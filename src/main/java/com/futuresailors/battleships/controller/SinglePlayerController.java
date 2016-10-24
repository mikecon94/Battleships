package com.futuresailors.battleships.controller;

import java.awt.Point;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.ai.AI;
import com.futuresailors.battleships.ai.SimpleAI;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlayPanel;

/**
 * Controller for the main game in singleplayer mode. This creates the ships for the opponent
 * ,creates the opponents grid and initialises the AI chosen by the player.
 * @author Michael Conroy, Joe Baldwin
 */
public class SinglePlayerController implements GameTypeController{
	
	private JFrame window;
	private PlayPanel panel;
	
	private Ship[] myShips;
	private Grid myGrid;
	
	private Ship[] aiShips;
	private Grid aiGrid;

	public SinglePlayerController(JFrame window){
		this.window = window;
		//TODO Make 10 a configurable for different Grid sizes
		myGrid = new Grid(10);
		aiGrid = new Grid(10);
		//Initialises the ships and defines what ships will be used in this game.
		createShips();
		@SuppressWarnings("unused")
		PlaceShipsController placeShips = new PlaceShipsController(myGrid, myShips, this, window);	
	}
	
	/**
	 * Defines the ships that will be used in this game.
	 */
	private void createShips(){
		//TODO make number of ships a configurable.
		myShips = new Ship[5];
		myShips[0] = new Ship(5, 1, "src/main/resources/images/ships/1.png");
		myShips[1] = new Ship(4, 1, "src/main/resources/images/ships/2.png");
		myShips[2] = new Ship(3, 1, "src/main/resources/images/ships/3.png");
		myShips[3] = new Ship(1, 3, "src/main/resources/images/ships/5.png");
		myShips[4] = new Ship(1, 2, "src/main/resources/images/ships/5.png");

		aiShips = new Ship[5];
		aiShips[0] = new Ship(5, 1, "src/main/resources/images/ships/1.png");
		aiShips[1] = new Ship(4, 1, "src/main/resources/images/ships/2.png");
		aiShips[2] = new Ship(3, 1, "src/main/resources/images/ships/3.png");
		aiShips[3] = new Ship(1, 3, "src/main/resources/images/ships/5.png");
		aiShips[4] = new Ship(1, 2, "src/main/resources/images/ships/5.png");
	}
	
	public void startGame(){
		//Creates AI and tells it to place the ships
		//These needs changing later to accomodate each level AI
		AI opp = new SimpleAI(aiGrid, aiShips);
		opp.placeShips();
		addGamePanel();
	}
	
	/**
	 * Creates the single player panel
	 */
	private void addGamePanel(){
		window.getContentPane().removeAll();
		panel = new PlayPanel(UIHelper.getWidth(), UIHelper.getHeight(), myGrid, aiGrid, myShips);
		window.add(panel);
		window.repaint();
		@SuppressWarnings("unused")
		GameListener listener = new GameListener(panel, this);
	}
	
	@Override
	public void returnToMenu() {
		MainMenuController main = new MainMenuController(window);
		main.showMenu();
		//TODO Display a JOptionPane asking if the user is sure they wish to return to the menu.
	}

	@Override
	public void mouseClicked(Point pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(Point pos) {
		// TODO Auto-generated method stub
		
	}
}
