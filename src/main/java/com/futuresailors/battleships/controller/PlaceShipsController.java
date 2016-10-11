package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.PlaceShipsListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;

public class PlaceShipsController {
	
	private JFrame window;
	private PlaceShipsPanel panel;
	
	public PlaceShipsController(JFrame window){
		window.getContentPane().removeAll();
		panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight());
		window.add(panel);
		window.repaint();
		PlaceShipsListener listener = new PlaceShipsListener(panel, this);
		this.window = window;
	}
	
	public void mouseMoved(int newX, int newY){
		panel.hoverTile(newX, newY);
	}
	
	public void returnToMenu(){
		BattleShipsController main = new BattleShipsController(window);
		main.showMenu();
	}
}