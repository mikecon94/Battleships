package com.futuresailors.battleships.controller;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.PlaceShipsListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;

public class PlaceShipsController {
	
	private JFrame window;
	
	public PlaceShipsController(JFrame window){
		window.getContentPane().removeAll();
		JPanel panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight());
		window.add(panel);
		window.repaint();
		PlaceShipsListener listener = new PlaceShipsListener(panel, this);
		this.window = window;
		System.out.println("PlaceShipsPanel Created");
	}
	
	public void returnToMenu(){
		BattleShipsController main = new BattleShipsController(window);
		main.showMenu();
	}
}
