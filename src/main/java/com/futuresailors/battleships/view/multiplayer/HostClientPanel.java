package com.futuresailors.battleships.view.multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.futuresailors.battleships.UIHelper;

public class HostClientPanel extends JPanel{

	private static final long serialVersionUID = -161318141413754470L;
	
	private final int WIDTH;
	private final int HEIGHT;
	JTextField ipField;
	
	public HostClientPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public String getConnectIP(){
		return ipField.getText();
	}
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		JButton backBut = new JButton("Main Menu");
		backBut.setSize(100, 50);
		backBut.setLocation(10, 10);
		backBut.setLayout(null);
		
		JButton serverBut = new JButton("Start Server");
		serverBut.setSize(150, 75);
		serverBut.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.75)));
		serverBut.setLayout(null);
		
		JButton connectBut = new JButton("Connect");
		connectBut.setSize(150, 75);
		connectBut.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.30)));
		connectBut.setLayout(null);
		
		ipField = new JTextField("Hostname");
		ipField.setSize(400, 30);
		ipField.setLocation((WIDTH / 2) - 200, (int) (HEIGHT - (HEIGHT * 0.40)));
		ipField.setHorizontalAlignment(JTextField.CENTER);
		ipField.setFont(new Font("Garamond", Font.PLAIN, 30));
		ipField.setLayout(null);
		
		add(backBut);
		add(serverBut);
		add(connectBut);
		add(ipField);
	}
		
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    ImageIcon gridImage = UIHelper.resizeImage("/images/background1.jpg", WIDTH, HEIGHT);
        g.drawImage(gridImage.getImage(), 0, 0, this);;
		g.setFont(new Font("Garamond", Font.BOLD, 50));
		g.setColor(new Color(255, 17, 0));
	    g.drawChars("Multiplayer".toCharArray(), 0, 11, (WIDTH / 2) - 120, 50);
	    
	    g.setFont(new Font("Garamond", Font.PLAIN, 30));
	    g.drawChars("Or connect to a server:".toCharArray(), 0, 23, (WIDTH / 2) - 145, HEIGHT / 2);
	}
}
