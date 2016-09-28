package com.futuresailors.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattleShips {
	
	//Window constants set to allow quick changes.
	private static final int WIDTH = 853;
	private static final int HEIGHT = 480;
	private static final String TITLE = "Noughts & Crosses";
	
	private static JFrame window = new JFrame(TITLE);


	public static void main(String[] args){
		setUpWindow();
		createMenu();
	}
	
	private static void startGame(){
		System.out.println("clicked");
		//Start a new game.
		//Replace the current panel with a new one.

		window.getContentPane().removeAll();
		JPanel testPan = new JPanel();
		testPan.setBackground(new Color(255, 255, 255));
		testPan.setSize(WIDTH, HEIGHT);
		testPan.setVisible(true);
		window.add(testPan);
		window.revalidate();
		window.repaint();		
	};
	
	private static void createMenu(){

		JButton startGameBut = createCustomButton("src/main/resources/icon.png", 100, 50);
		startGameBut.setSize(100, 50);
		startGameBut.setLocation((WIDTH / 2) - 50, (HEIGHT / 2) - 50);
	
		startGameBut.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    startGame();
				  } 
		} );
		startGameBut.setVisible(true);
		window.add(startGameBut);
		window.revalidate();
		window.repaint();	
	}
	
	//This method can be called from the other panels when they have finished.
	private static JButton createCustomButton(String imagePath, int width, int height){
		ImageIcon img = new ImageIcon("src/main/resources/icon.png");
	    window.setIconImage(img.getImage());    
	    window.setContentPane(new JLabel(resizeImage("src/main/resources/background.jpg", WIDTH, HEIGHT)));
	    
		ImageIcon image = resizeImage(imagePath, width, height);
		JButton button = new JButton(image);
		button.setBorder(null);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);

		//Use these to control the other states of the buttons.
		//button.setRolloverIcon(myIcon2);
		//button.setPressedIcon(myIcon3);
		//button.setDisabledIcon(myIcon4);
		
		return button;
	}

	private static void setUpWindow(){
		window.setSize(WIDTH, HEIGHT);
		
		//Centres the window
		window.setLocationRelativeTo(null);
		//If the above line doesn't work on all platforms we can use the following lines.
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
	    
		window.setResizable(false);
		
		//End the program on close.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	//Helper method for resizing a given Image. Used on the background and for buttons.
	private static ImageIcon resizeImage(String imagePath, int width, int height){
		ImageIcon bg = new ImageIcon(imagePath);
		return new ImageIcon(bg.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}	
}