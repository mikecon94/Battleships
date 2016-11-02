package com.futuresailors.battleships.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

public class FindPlayerPanel extends JPanel{

	private static final long serialVersionUID = -161318141413754470L;
	
	private final int WIDTH;
	private final int HEIGHT;
	
	public FindPlayerPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		JButton backBut = new JButton("Main Menu");
		backBut.setSize(100, 50);
		backBut.setLocation(10, 10);
		backBut.setLayout(null);
		add(backBut);
	}
		
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon gridImage = UIHelper.resizeImage("/images/background1.jpg", WIDTH, HEIGHT);
        g.drawImage(gridImage.getImage(), 0, 0, this);;
		g.setFont(new Font("Garamond", Font.BOLD, 50));
		g.setColor(new Color(255, 17, 0));
	    g.drawChars("Find Player".toCharArray(), 0, 11, (WIDTH / 2) - 120, 50);
	}
	
}
