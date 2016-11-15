package com.futuresailors.battleships.view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.futuresailors.battleships.controller.Controller;

/**
 * This adds the appropriate listeners to the panels.
 * @author Michael Conroy
 */
public class GameListener {

	private Controller controller;
	private JPanel panel;
	
	public GameListener(JPanel panel, Controller controller){
		this.controller = controller;
		this.panel = panel;
		addListeners();
	}
	
	private void addListeners(){
		//0 is the Start Game Button.
		panel.getComponent(0).addMouseListener(new MouseListener(){
			public void mouseReleased(MouseEvent e) {
				//Check which button was clicked:
				//0 = Start Game
				controller.returnToMenu();
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
		});
		
		panel.addMouseListener(new MouseListener(){
			public void mouseReleased(MouseEvent e) {
				controller.mouseClicked(new Point(e.getX(), e.getY()));
			}			
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
		});
		
		panel.addMouseMotionListener(new MouseMotionListener(){
			public void mouseMoved(MouseEvent e) {
				controller.mouseMoved(new Point(e.getX(), e.getY()));
			}
			public void mouseDragged(MouseEvent e) {
			}
		});
	}
}
