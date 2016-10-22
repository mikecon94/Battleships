package com.futuresailors.battleships.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;

/**
 * This is the panel where the main game will be played.
 * The controllers for each game mode will create an instance
 * of this panel and control the logic as needed.
 * @author Joe Baldwin & Michael Conroy
 */

@SuppressWarnings("serial")
public class MainPlayPanel extends JPanel {

	private final int WIDTH;
	private final int HEIGHT;
	private final int GRID_WIDTH = 550;
	private final int GRID_HEIGHT = 550;
	//Left Side Grid
	private final int GRID_X = 70;
	private final int GRID_Y = 80;
	//Right Side Grid
	private final int GRID_2_X = 650;
	private final int GRID_2_Y = 80;
	//All tiles are square.
	private int tileSize;
	//Client grid - Left
	private Grid grid;
	//Opponent Grid - Right
	private Grid oppGrid;
	//Client Ships - Left
	private Ship ships[];
	
	public MainPlayPanel(int width, int height, Grid grid1, Grid grid2, Ship ships[]){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.grid = grid1;
		this.oppGrid = grid2;
		this.ships = ships;
		grid.getRows();
		createPanel();
	}
	
	public int getTileXUnderMouse(int x){
		return (x - GRID_X) / tileSize;
	}
	
	public int getTileYUnderMouse(int y){
		return (y - GRID_Y) / tileSize;
	}
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		JButton backBut = new JButton("Return");
		backBut.setName("Return");
		backBut.setSize(100, 50);
		backBut.setLocation(10, 10);
		backBut.setLayout(null);
		add(backBut);
		//Will be configurable at a later date.
		tileSize = 550 / grid.getColumns();
		System.out.println("MainPlayPanel Created.");
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon gridImage = UIHelper.resizeImage("src/main/resources/images/background2.jpg", WIDTH, HEIGHT);
        g.drawImage(gridImage.getImage(), 0, 0, this);
	    g.setFont(new Font("Garamond", Font.PLAIN , 40));
	    g.setColor(new Color(255, 255, 255));
	    g.drawChars("Lets Play Battleships".toCharArray(), 0, 16, (WIDTH / 2) - 120, 50);
    
	    g.fillRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        drawTiles(g);
        g.setColor(new Color(255, 255, 255));
        g.drawRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        drawShips(g);
        
        g.fillRect(GRID_2_X, GRID_2_Y, GRID_WIDTH, GRID_HEIGHT);
        drawTiles(g);
        g.drawRect(GRID_2_X, GRID_2_Y, GRID_WIDTH, GRID_HEIGHT);
	}
	
	private void drawShips(Graphics g){
		for(Ship ship : ships){
			if(ship.getPlaced()){
				ImageIcon shipImage = UIHelper.resizeImage(ship.getImagePath(), ship.getWidth() * tileSize - 2, ship.getHeight() * tileSize - 2);
				g.drawImage(shipImage.getImage(), GRID_X + (ship.getX() * tileSize), GRID_Y + (ship.getY() * tileSize), this);
		
			}
		}
	}
	
	
	
	private void drawTiles(Graphics g){
        for(int row = 0; row < grid.getRows(); row++){
			for(int column = 0; column < grid.getColumns(); column++){
				if(grid.getTile(column, row) == ' '){
			        g.setColor(new Color(0, 0, 0));
					g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else if(grid.getTile(column, row) == 'H'){
					//H is hover.
			        g.setColor(new Color(0, 255, 255));
					g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else {
					//ImageIcon tileImage = UIHelper.resizeImage(grid.getTile(column, row), tileSize, tileSize);
					//g.drawImage(tileImage.getImage(), GRID_X + (column * tileSize), GRID_Y + (row * tileSize), this);
//					System.out.println("Drawing image: " + grid.getTile(column, row));
				}
		        g.setColor(new Color(0, 0, 0));
				g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);	
			}
		}
        for(int row = 0; row < oppGrid.getRows(); row++){
			for(int column = 0; column < oppGrid.getColumns(); column++){
				if(oppGrid.getTile(column, row) == ' '){
			        g.setColor(new Color(0, 0, 0));
					g.drawRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
				} else if(oppGrid.getTile(column, row) == 'H'){
					//H is hover.
			        g.setColor(new Color(0, 255, 255));
					g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
				} else {
					//ImageIcon tileImage = UIHelper.resizeImage(grid.getTile(column, row), tileSize, tileSize);
					//g.drawImage(tileImage.getImage(), GRID_X + (column * tileSize), GRID_Y + (row * tileSize), this);
//					System.out.println("Drawing image: " + oppGrid.getTile(column, row));
				}
		        g.setColor(new Color(0, 0, 0));
				g.drawRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);	
			}
		}
    }

}
