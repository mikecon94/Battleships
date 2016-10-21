package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.PlaceShipsPanel;
import com.futuresailors.battleships.view.RulesListener;
import com.futuresailors.battleships.view.RulesPanel;

public class RulesController {
	private JFrame window;
	private RulesPanel panel;
	
	public RulesController(JFrame window){
		window.getContentPane().removeAll();
		panel = new RulesPanel(UIHelper.getWidth(), UIHelper.getHeight());
		window.add(panel);
		window.repaint();
		RulesListener listener = new RulesListener(panel, this);
		this.window = window;
	}
	
	public void returnToMenu(){
		BattleShipsController main = new BattleShipsController(window);
		main.showMenu();
	}
}
