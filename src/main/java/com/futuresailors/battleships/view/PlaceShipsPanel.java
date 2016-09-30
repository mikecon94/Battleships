package com.futuresailors.battleships.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;

@SuppressWarnings("serial")
public class PlaceShipsPanel extends JPanel {
	
	public PlaceShipsPanel(){
		this.setSize(853, 480);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon gridImage = UIHelper.resizeImage("src/main/resources/grid.png", 400, 480);
        g.drawImage(gridImage.getImage(), 0, 0, this);
	}
}
