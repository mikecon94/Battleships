package com.futuresailors.battleships.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.futuresailors.battleships.controller.PlaceShipsController;

public class PlaceShipsListener {

	private PlaceShipsController controller;
	private JPanel panel;
	
	public PlaceShipsListener(JPanel panel, PlaceShipsController controller){
		this.controller = controller;
		this.panel = panel;
		addListeners();
	}
	
	/*
	 * Adds the appropriate mouse listeners to the buttons on the Menu Screen.
	 * When clicked it will alert the controller to then perform the
	 * appropriate action.
	 */
	private void addListeners(){
		//1 is the Start Game Button.
		panel.getComponent(0).addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent e) {
				//Check which button was clicked:
				//0 = Start Game
				System.out.println("Back Button Clicked.");
				controller.returnToMenu();
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
		});
		
		panel.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				controller.mouseMoved(e.getX(), e.getY());
			}
		});
	}
}
