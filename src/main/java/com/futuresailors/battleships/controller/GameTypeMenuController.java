package com.futuresailors.battleships.controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.GameTypeMenuListener;
import com.futuresailors.battleships.view.GameTypeMenuPanel;

public class GameTypeMenuController{
	
	private JFrame window;
	private final String TITLE = "Game Type Selection";
	

	public GameTypeMenuController(JFrame window){
		this.window = window;
		start();
		System.out.println("Created window");
	}
	
	public void start(){
		showMenu();
	}
	
	public void showMenu(){
		window.getContentPane().removeAll();
		JPanel GameTypePanel = new GameTypeMenuPanel(UIHelper.getWidth(), UIHelper.getHeight());
		GameTypePanel.setVisible(true);
		GameTypeMenuListener menuListener = new GameTypeMenuListener(GameTypePanel, this);
		window.add(GameTypePanel);
		window.repaint();
	}


	public void startReloadedMode() {
		System.out.println("Reloaded");
		
	}

	public void startClassicMode() {
		GameTypeController controller = new SinglePlayerController(window);
		
	}

}
