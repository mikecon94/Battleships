package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.MainPlayPanel;

public class SinglePlayerController {
	
	private JFrame window;
	private MainPlayPanel panel;
	private Ship ships[];
	private Grid aiGrid;
	private Grid grid = new Grid(10);
	
	public SinglePlayerController(Ship ships[], JFrame window){
		this.ships = ships;
		this.window = window;
		addPanel();
		placeAIShips();
	}
	
	private void addPanel(){
		panel = new MainPlayPanel(UIHelper.getWidth(), UIHelper.getHeight(), grid, grid);
		panel.setVisible(true);
		window.add(panel);
		window.repaint();
	}
	
	private void placeAIShips(){
		//This method should generate the AI's ships and randomly place them on the grid
	}

}
