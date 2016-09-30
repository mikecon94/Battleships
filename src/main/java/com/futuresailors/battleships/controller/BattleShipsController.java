package com.futuresailors.battleships.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.futuresailors.battleships.view.MainMenu;
import com.futuresailors.battleships.view.PlaceShipsPanel;

public class BattleShipsController {
	
	@SuppressWarnings("unused")
	public void start(){
		//Creates the window and adds the menu screen to it.
		MainMenu menu = new MainMenu();
		
		//We can use this to update what is displayed on the window.
		//eg. change to a new panel as the game moves along.
		JFrame window = menu.getWindow();
		
		//0 is start game
		window.getContentPane().getComponent(0).addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Starting game.");
				window.getContentPane().removeAll();
				window.add(new PlaceShipsPanel());
				window.repaint();
			}
		});
	}
}
