package com.futuresailors.battleships.controller;

import java.awt.Point;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.multiplayer.HostClientPanel;

public class MultiPlayerController implements GameTypeController{

	private JFrame window;
	private HostClientPanel panel;
	
	public MultiPlayerController(JFrame window){
		this.window = window;
		addPanel();
	}
	
	private void addPanel(){
		window.getContentPane().removeAll();
		panel = new HostClientPanel(UIHelper.getWidth(), UIHelper.getHeight());
		window.add(panel);
		window.repaint();
		@SuppressWarnings("unused")
		GameListener listener = new GameListener(panel, this);
	}
	
	@Override
	public void returnToMenu() {
		MainMenuController main = new MainMenuController(window);
		main.showMenu();
	}

	@Override
	public void mouseClicked(Point pos) {
	}

	@Override
	public void mouseMoved(Point pos) {
	}
	
	@Override
	public void startGame() {
	}
}
