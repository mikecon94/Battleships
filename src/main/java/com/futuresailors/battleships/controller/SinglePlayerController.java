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
 * @author Joe Baldwin, Michael Conroy
 */
public class SinglePlayerController implements Controller{
	
	private JFrame window;
	private PlayPanel panel;
	
	private Ship[] myShips;
	private Grid myGrid;
	
	private Ship[] aiShips;
	private Grid aiGrid;

	public SinglePlayerController(Grid grid, Ship ships[], JFrame window){
		myShips = ships;
		this.window = window;
		myGrid = grid;
		aiGrid = new Grid(grid.getRows());
		createOppShips();
		
		//Creates AI and tells it to place the ships
		//These needs changing later to accomodate each level AI
		AI opp = new SimpleAI(aiGrid, aiShips);
		opp.placeShips();
		addPanel();
	}
	
	/**
	 * Creates the initial array of the opponents ships without placing them
	 */
	private void createOppShips(){
		aiShips = new Ship[myShips.length];
		//Ensure both players have the same ship types.
		//This should probably be changed eventually so the ship types are defined before place ships
		//by the controller.
		for(int i = 0; i < myShips.length; i++){
			aiShips[i] = new Ship(myShips[i].getWidth(), myShips[i].getHeight(), myShips[i].getImagePath());
			System.out.println(i);
		}
	}
	
	/**
	 * Creates the single player panel
	 */
	private void addPanel(){
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
		//TODO We should do some sort of check here to where a popup window prompts the player to ask if they are sure they want to leave the game
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
