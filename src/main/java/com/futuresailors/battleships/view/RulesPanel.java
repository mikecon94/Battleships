package com.futuresailors.battleships.view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.controller.BattleShipsController;


public class RulesPanel extends JPanel {

	private final int WIDTH;
	private final int HEIGHT;
	
	public RulesPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public RulesPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
		createPanel();
	}
	
    Border border = LineBorder.createGrayLineBorder();
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		//JButton startGameBut = UIHelper.createCustomButton("src/main/resources/icon.png", 100, 50);
		JLabel rulesLabel = new JLabel("Rules", JLabel.CENTER);
		rulesLabel.setName("RuleLabel");
		rulesLabel.setSize(300,75);
		rulesLabel.setLocation((WIDTH)/2 - 75, (int) (HEIGHT - (HEIGHT * 0.70)));
		rulesLabel.setText("willyt");
		rulesLabel.setBorder(border);
		add(rulesLabel);
		JButton showRulesBut = new JButton("Rules");
		showRulesBut.setName("StartGameButton");
		showRulesBut.setSize(150, 75);
		showRulesBut.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.35)));
		add(showRulesBut);
		JButton exitBut = new JButton("Exit Game");
		exitBut.setName("ExitGameButton");
		exitBut.setSize(150, 75);
		exitBut.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.50)));
		add(exitBut);
		setName("RulesPanel");
		System.out.println("RulesPanel Created.");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    ImageIcon bg = UIHelper.resizeImage("src/main/resources/background2.jpg", WIDTH, HEIGHT);
	    g.drawImage(bg.getImage(), 0, 0, null);
	}
}
