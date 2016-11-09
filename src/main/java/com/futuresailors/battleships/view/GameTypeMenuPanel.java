package com.futuresailors.battleships.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;

public class GameTypeMenuPanel extends JPanel{

	private final int WIDTH;
	private final int HEIGHT;

	public GameTypeMenuPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public GameTypeMenuPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
		createPanel();
	}
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		//JButton startGameBut = UIHelper.createCustomButton("src/main/resources/icon.png", 100, 50);
		JButton classic = new JButton("Classic");
	    classic.setSize(150, 75);
		classic.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.65)));
		add(classic);
		
		JButton reloaded = new JButton("Reloaded");
		reloaded.setName("Reloaded Mode");
		reloaded.setSize(150, 75);
		reloaded.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.50)));
		add(reloaded);
		setName("GameTypeMenuPanel");
		System.out.println("GameTypeMenuPanel Created.");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    ImageIcon bg = UIHelper.resizeImage("/images/Background1.jpg", WIDTH, HEIGHT);
	    g.drawImage(bg.getImage(), 0, 0, null);
	    
	    //Draw the title.
//	    ImageIcon title = UIHelper.resizeImage("/images/Title.png", 1100, 150);
//	    g.drawImage(title.getImage(), (WIDTH / 2) - 550, 10, null);
	}
	
	
}
