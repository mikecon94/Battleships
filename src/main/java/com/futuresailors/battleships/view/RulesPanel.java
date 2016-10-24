package com.futuresailors.battleships.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.futuresailors.battleships.UIHelper;

/**
 * A JPanel that displays the rules to the users.
 * @author Ryan Lowers, Michael Conroy
 */
public class RulesPanel extends JPanel {

	private static final long serialVersionUID = -6181012691286200640L;

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
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		
		JButton backBut = new JButton("Main Menu");
		backBut.setSize(100, 50);
		backBut.setLocation(10, 10);
		backBut.setLayout(null);
		add(backBut);
		
		JEditorPane rulesLabel = new JEditorPane();
		rulesLabel.setEditable(false);
		rulesLabel.setName("RulesLabel");
		rulesLabel.setSize(1000, 550);
		rulesLabel.setBorder(LineBorder.createGrayLineBorder());	
		String labelContent;
		//Create string builder for html file read
		StringBuilder html = new StringBuilder();
		//Read file in
		try {
		    BufferedReader in = new BufferedReader(new InputStreamReader(RulesPanel.class.getResourceAsStream("/rules.html")));
		    String str;
		    while ((str = in.readLine()) != null) {
		        html.append(str);
		    }
		    in.close();
		} catch (IOException e) {
			labelContent = "Unable to load rules";
		}
		//Set html file to label
		labelContent = html.toString();
		       
		rulesLabel.setContentType("text/html");
		rulesLabel.setText(labelContent);
		rulesLabel.setCaretPosition(0);
		JScrollPane scroll = new JScrollPane(rulesLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setSize(1000, 550);
		scroll.setLocation((WIDTH)/2 - 500, 100);
		add(scroll);

		setName("RulesPanel");
		System.out.println("RulesPanel Created.");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    ImageIcon bg = UIHelper.resizeImage("/images/background2.jpg", WIDTH, HEIGHT);
	    g.drawImage(bg.getImage(), 0, 0, null);
	    g.setFont(new Font("Garamond", Font.PLAIN , 40));
	    g.setColor(new Color(255, 255, 255));
	    g.drawChars("Rules".toCharArray(), 0, 5, (WIDTH / 2) - 50, 50);
	}
}