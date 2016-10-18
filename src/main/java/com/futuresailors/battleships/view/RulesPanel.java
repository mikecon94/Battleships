package com.futuresailors.battleships.view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		JLabel rulesLabel = new JLabel("Rules", JLabel.CENTER);
		rulesLabel.setName("RuleLabel");
		rulesLabel.setSize(500,300);
		rulesLabel.setLocation((WIDTH)/2 - 200, (int) (HEIGHT - (HEIGHT * 0.95)));
		String labelContent = 
				"<html><b>Rules</b><BR>"
				+ "<BR>"
				+ "<b>Before the Game Begins</b><BR>"
				+ "<ul>"
				+ "<li>At the start of the game every player chooses a selection of five ships.</li><BR>"
				+ "<li>Both players can choose the map they want and then a random number generator decides which player gets their chosen map.</li><BR>"
				+ "<li>Both players place their five ships on their respective playing fields, ships can be oriented either vertically or horizontally.</li><BR>"
				+ "<li>Players choose one power up to employ in the game.</li><BR>"
				+ "</ul>"
				+ "<BR>"
				+ "<BR>"
				+ "<b>During Gameplay</b>"
				+ "</html>";
		rulesLabel.setText(labelContent);
		rulesLabel.setBorder(border);
		rulesLabel.setOpaque(true);
		add(rulesLabel);
		
		JButton exitBut = new JButton("Return");
		exitBut.setName("Return");
		exitBut.setSize(150, 75);
		exitBut.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.30)));
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
