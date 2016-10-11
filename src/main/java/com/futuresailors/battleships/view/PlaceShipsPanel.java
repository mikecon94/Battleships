package com.futuresailors.battleships.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;

@SuppressWarnings("serial")
public class PlaceShipsPanel extends JPanel {
	
	private final int WIDTH;
	private final int HEIGHT;
	
	public PlaceShipsPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public PlaceShipsPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
		createPanel();
	}
	
	private void createPanel(){
		setSize(WIDTH, HEIGHT);
		JButton backBut = new JButton("Return");
		backBut.setSize(100, 50);
		backBut.setLocation(10, 10);
		backBut.setLayout(null);
		add(backBut);
		System.out.println("PlaceShipsPanel Created.");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon gridImage = UIHelper.resizeImage("src/main/resources/background2.jpg", WIDTH, HEIGHT);
        g.drawImage(gridImage.getImage(), 0, 0, this);
	    g.setFont(new Font("Garamond", Font.PLAIN , 40));
	    g.setColor(new Color(255, 255, 255));
	    g.drawChars("Place your ships.".toCharArray(), 0, 16, (WIDTH / 2) - 120, 50);
        g.fillRect(100, 80, 550, 550);
        g.setColor(new Color(255));
        g.drawRect(100, 80, 550, 550);
	}
}
