package com.futuresailors.battleships.controller;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.ai.AI;
import com.futuresailors.battleships.ai.SimpleAI;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlayPanel;

/**
 * Controller for the main game in single player mode. This creates the ships for the opponent
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
	AI opp;
	
	private boolean myTurn = false;
	
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
		opp = new SimpleAI(aiGrid, aiShips);
		opp.placeShips();
		chooseFirstPlayer();
		addGamePanel();	
		if(myTurn == false){
			opponentMove();
		}
	}
	
	/**
	 * Randomly chooses which player will go first.
	 */
	private void chooseFirstPlayer(){
		myTurn = ThreadLocalRandom.current().nextBoolean();
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
		//Hover the tile if it is the users turn and the mouse is over the AIs grid.
		Point gridPos = new Point(panel.getTileXUnderMouse(pos.x), panel.getTileYUnderMouse(pos.y));
		
		if(myTurn && panel.overGridSpace(pos.x, pos.y) 
				&& aiGrid.getTile(gridPos) != GridTile.MISS && aiGrid.getTile(gridPos) != GridTile.HIT){
			System.out.println("User: " + aiGrid.getTile(gridPos));
			if(aiGrid.dropBomb(gridPos)){
				System.out.println("User Hit A Ship.");
			} else {
				myTurn = false;
				opponentMove();
			}
		}
		panel.repaint();
	}
	
	private void opponentMove(){
		while(myTurn == false){
			Point target;
			do{
				target = opp.takeMove();
			} while(myGrid.getTile(target) == GridTile.MISS || myGrid.getTile(target) == GridTile.HIT);
			System.out.println("AI Move: " + target.x + ", " + target.y);
			if(myGrid.dropBomb(target)){
				
			} else {
				System.out.println("AI Missed.");
				myTurn = true;
			}
		}	
		panel.repaint();
	}

	@Override
	public void mouseMoved(Point pos) {
		//Hover the tile if it is the users turn and the mouse is over the AIs grid.
		if(myTurn && panel.overGridSpace(pos.x, pos.y)){
			aiGrid.hoverBomb(new Point(panel.getTileXUnderMouse(pos.x), panel.getTileYUnderMouse(pos.y)));
		} else {
			aiGrid.clearHoverTiles();
		}
		panel.repaint();
	}
}