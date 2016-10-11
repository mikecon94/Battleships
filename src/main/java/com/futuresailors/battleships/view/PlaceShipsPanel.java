package com.futuresailors.battleships.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;

@SuppressWarnings("serial")
public class PlaceShipsPanel extends JPanel {
	
	private final int WIDTH;
	private final int HEIGHT;
	private final int GRID_WIDTH = 550;
	private final int GRID_HEIGHT = 550;
	private final int GRID_X = 100;
	private final int GRID_Y = 80;
	
	//Placeholder variable for POC on tile hovering
	private Color gridColor = new Color(255, 255, 255);
	//We probably want an array here to represent the tiles in the grid.
	//The paintComponent method can then loop round it and draw the
	//appropriate image for each tile.
	//We can also define a variable named tile size which is
	//essentially GRID_X / Number of columns.
	//Duplicated for Y.
	
	
	public PlaceShipsPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public PlaceShipsPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
		createPanel();
	}
	
	private void createPanel(){
		setSize(WIDTH, HEIGHT);
		JButton backBut = new JButton("Return");
		backBut.setSize(100, 50);
		backBut.setLocation(10, 10);
		backBut.setLayout(null);
		add(backBut);
		System.out.println("PlaceShipsPanel Created.");
	}
	
	public void hoverTile(int x, int y){
		//Check if the mouse was moved in the grid area.
		//We can probably do some clever maths with the X & Y to detect
		//which tile is being hovered over.
		//eg. tile 1 may go from pixel 0 to 5 (x)
		//Dividing the mouses values by 5 will tell us the tile location.
		//To check tile 1 the truncated value would be 0 when dividing the mouses pixel values by 5.
		//The right hand border will be counted as the next tile (though this is not necessarily a problem).
		//The same logic can be used for calculating where bombs were dropped etc.
		if(x > GRID_X && x < GRID_X + GRID_WIDTH
			&& y < GRID_Y + GRID_HEIGHT && y > GRID_Y){
			System.out.println("Mouse Moved: " + x + ", " + y);
			gridColor = new Color(255, 255, 255);
		} else {
			gridColor = new Color(0, 0, 0);
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon gridImage = UIHelper.resizeImage("src/main/resources/background2.jpg", WIDTH, HEIGHT);
        g.drawImage(gridImage.getImage(), 0, 0, this);
	    g.setFont(new Font("Garamond", Font.PLAIN , 40));
	    g.setColor(new Color(255, 255, 255));
	    g.drawChars("Place your ships.".toCharArray(), 0, 16, (WIDTH / 2) - 120, 50);
	    g.setColor(gridColor);
        g.fillRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        g.setColor(new Color(255));
        g.drawRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
	}
}
