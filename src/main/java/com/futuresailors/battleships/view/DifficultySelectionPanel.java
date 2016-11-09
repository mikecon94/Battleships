package com.futuresailors.battleships.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
/**
 * Panel for selection of difficulty for the single player games
 * @author Joe Baldwin
 */
public class DifficultySelectionPanel extends JPanel{

	private final int WIDTH;
	private final int HEIGHT;

	public DifficultySelectionPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public DifficultySelectionPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
		createPanel();
	}
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		
		JButton easy = new JButton("Easy");
	    easy.setSize(150, 75);
		easy.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.65)));
		add(easy);
		
		JButton moderate = new JButton("Moderate");
		moderate.setSize(150, 75);
		moderate.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.50)));
		add(moderate);
		
		JButton hard = new JButton("Hard");
		hard.setSize(150, 75);
		hard.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.35)));
		add(hard);
		
		setName("DifficultySelectionPanel");
		System.out.println("DifficultySelectionPanel Created.");
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
