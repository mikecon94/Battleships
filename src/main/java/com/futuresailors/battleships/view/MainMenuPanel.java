package com.futuresailors.battleships.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {

	private final int WIDTH;
	private final int HEIGHT;

	public MainMenuPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public MainMenuPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
		createPanel();
	}
	
	private void createPanel(){
		setSize(WIDTH, HEIGHT);
		add(new JLabel(UIHelper.resizeImage("src/main/resources/background.jpg", WIDTH, HEIGHT)));
		//JButton startGameBut = UIHelper.createCustomButton("src/main/resources/icon.png", 100, 50);
		JButton startGameBut = new JButton("Start Game");
		startGameBut.setName("StartGameButton");
	    startGameBut.setSize(100, 50);
		startGameBut.setLocation((WIDTH / 2) - 50, (HEIGHT / 2) - 50);
		add(startGameBut);
		setName("MainMenuPanel");
		System.out.println("MainMenuPanel Created");
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon bg = UIHelper.resizeImage("src/main/resources/background.jpg", WIDTH, HEIGHT);
        g.drawImage(bg.getImage(), 0, 0, null);
	}
	
}