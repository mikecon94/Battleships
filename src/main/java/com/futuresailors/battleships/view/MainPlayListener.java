package com.futuresailors.battleships.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.controller.SinglePlayerController;

public class MainPlayListener implements ActionListener{

	private SinglePlayerController controller;
	private JPanel panel;
	
	public MainPlayListener(JPanel panel, SinglePlayerController controller){
		this.controller = controller;
		this.panel = panel;
		addListeners();
	}

	/**
	 * Loops round all the components on the main menu panel
	 * and adds this listener to all of the buttons.
	 */
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
		if("Return".equals(e.getActionCommand())){
			System.out.println("Return Clicked.");
			controller.returnToMenu();
		}
	}
}
