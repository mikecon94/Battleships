package com.futuresailors.battleships.view.multiplayer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.controller.MainMenuController;
import com.futuresailors.battleships.controller.MultiPlayerController;

public class FindPlayerListener implements ActionListener{

	private MultiPlayerController controller;
	private JPanel panel;
	
	public FindPlayerListener(JPanel panel, MultiPlayerController controller){
		this.controller = controller;
		this.panel = panel;
		addListeners();
	}
	
	private void addListeners(){
		Component[] comps = panel.getComponents();
		for(Component comp : comps){
			if(comp instanceof JButton){
				JButton button = (JButton) comp;
				button.addActionListener(this);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if("Start Server".equals(e.getActionCommand())){
			controller.startServer();
		} else if("Connect".equals(e.getActionCommand())){
			controller.connect();
		} else if("Main Menu".equals(e.getActionCommand())){
			System.out.println("Returning to Main Menu");
			controller.returnToMenu();
		}
	}
}
