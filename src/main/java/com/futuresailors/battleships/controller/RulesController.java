package com.futuresailors.battleships.controller;

import java.awt.Point;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.Controller;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.RulesPanel;

public class RulesController implements Controller {
	private JFrame window;
	private RulesPanel panel;
	
	public RulesController(JFrame window){
		window.getContentPane().removeAll();
		panel = new RulesPanel(UIHelper.getWidth(), UIHelper.getHeight());
		window.add(panel);
		window.repaint();
		GameListener listener = new GameListener(panel, this);
		this.window = window;
	}
	
	public void returnToMenu(){
		MainMenuController main = new MainMenuController(window);
		main.showMenu();
	}

	@Override
	public void mouseClicked(Point pos) {}

	@Override
	public void mouseMoved(Point pos) {}
}
