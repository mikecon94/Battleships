package com.futuresailors.battleships.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;

public class MainMenu {
	
	//Window constants set to allow quick changes.
	private final int WIDTH = 853;
	private final int HEIGHT = 480;
	private final String TITLE = "Battleships";
	
	private JFrame window = new JFrame(TITLE);

	public MainMenu(){
		setUpWindow();
		createMenu();
	}
	
	@SuppressWarnings("unused")
	private void startGame(){
		System.out.println("clicked");
		//Start a new game.
		//Replace the current panel with a new one.

		window.getContentPane().removeAll();
		JPanel testPan = new PlaceShipsPanel();
		testPan.repaint();
		testPan.setVisible(true);
		window.add(testPan);
		window.revalidate();
		window.repaint();		
	};
	
	private void createMenu(){

		ImageIcon img = new ImageIcon("src/main/resources/icon.png");
	    window.setIconImage(img.getImage());    
	    window.setContentPane(new JLabel(UIHelper.resizeImage("src/main/resources/background.jpg", WIDTH, HEIGHT)));
	    
		//JButton startGameBut = UIHelper.createCustomButton("src/main/resources/icon.png", 100, 50);
		JButton startGameBut = new JButton("Start Game");
	    startGameBut.setSize(100, 50);
		startGameBut.setLocation((WIDTH / 2) - 50, (HEIGHT / 2) - 50);
			
		startGameBut.setVisible(true);
		window.add(startGameBut);
		window.revalidate();
		window.repaint();	
	}

	private void setUpWindow(){
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
	
	public JFrame getWindow(){
		return window;
	}
	
}