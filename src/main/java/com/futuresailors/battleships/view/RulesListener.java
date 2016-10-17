package com.futuresailors.battleships.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.futuresailors.battleships.controller.BattleShipsController;


public class RulesListener implements ActionListener {

	private BattleShipsController controller;
	private JPanel panel;
	
	public RulesListener(JPanel panel, BattleShipsController controller){
		this.controller = controller;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
