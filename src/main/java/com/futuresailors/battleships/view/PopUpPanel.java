package com.futuresailors.battleships.view;

import javax.swing.JOptionPane;


public class PopUpPanel {
	
	//Creates a pop up object that displays yes or no
	public PopUpPanel(String message){
		final JOptionPane optionPane = new JOptionPane(message,
			    JOptionPane.QUESTION_MESSAGE,
			    JOptionPane.YES_NO_OPTION);
	}
}
