package com.futuresailors.battleships.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

/**
 * This is the panel where the main game will be played. The controllers for
 * each game mode will create an instance of this panel and control the logic as
 * needed.
 * 
 * @author Michael Conroy, Joe Baldwin
 */
public class PlayPanel extends JPanel {

	private static final long serialVersionUID = 5959433312203394355L;

	private final int WIDTH;
	private final int HEIGHT;
	private final int GRID_WIDTH = 550;
	private final int GRID_HEIGHT = 550;
	// My Grid
	private final int GRID_X = 100;
	private final int GRID_Y = 80;
	// Opponents Grid
	private final int GRID_2_X = 670;
	private final int GRID_2_Y = 80;
	// All tiles are square.
	private int tileSize;
	// Client grid - Left
	private Grid myGrid;
	// Opponent Grid - Right
	private Grid oppGrid;
	// Client Ships - Left
	private Ship ships[];
	private JButton menuBut;

	public PlayPanel(int width, int height, Grid grid1, Grid grid2, Ship ships[]) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.myGrid = grid1;
		this.oppGrid = grid2;
		this.ships = ships;
		myGrid.getRows();
		createPanel();
	}

	public int getTileXUnderMouse(int x) {
		return (x - GRID_2_X) / tileSize;
	}

	public boolean overGridSpace(int x, int y) {
		if (x > GRID_2_X && x < GRID_2_X + GRID_WIDTH && y < GRID_2_Y + GRID_HEIGHT && y > GRID_2_Y) {
			return true;
		}
		return false;
	}

	public int getTileYUnderMouse(int y) {
		return (y - GRID_2_Y) / tileSize;
	}

	private void createPanel() {
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		menuBut = new JButton("Main Menu");
		menuBut.setSize(100, 50);
		menuBut.setLocation(10, 10);
		menuBut.setLayout(null);
		add(menuBut);
		// Will be configurable at a later date.
		tileSize = 550 / myGrid.getColumns();
		System.out.println("MainPlayPanel Created.");
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon gridImage = UIHelper.resizeImage("/images/Background1.jpg", WIDTH, HEIGHT);
		g.drawImage(gridImage.getImage(), 0, 0, this);
		g.setFont(new Font("Garamond", Font.BOLD, 50));
		g.setColor(new Color(255, 17, 0));
		g.drawChars("Classic Game".toCharArray(), 0, 12, (WIDTH / 2) - 120, 50);

		g.setColor(new Color(255, 255, 255));
		g.fillRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
		drawMyGrid(g);
		g.setColor(new Color(0, 0, 0));
		g.drawRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);

		g.setColor(new Color(255, 255, 255));
		g.fillRect(GRID_2_X, GRID_2_Y, GRID_WIDTH, GRID_HEIGHT);
		drawOppGrid(g);
		g.drawRect(GRID_2_X, GRID_2_Y, GRID_WIDTH, GRID_HEIGHT);
		// This must be called last as the ships need to be on top of the grid.
		drawShips(g);
		//Draw bombs - Must be done after drawing ships so the images are on top.
		drawBombs(myGrid, GRID_X, GRID_Y, g);
		drawBombs(oppGrid, GRID_2_X, GRID_2_Y, g);
	}
	
	private void drawBombs(Grid grid, int startX, int startY, Graphics g){
		for (int row = 0; row < grid.getRows(); row++) {
			for (int column = 0; column < grid.getColumns(); column++) {
				if(grid.getTile(new Point(column, row)) == GridTile.HIT){
					ImageIcon hit = UIHelper.resizeImage("/images/Hit.png", tileSize, tileSize);
					g.drawImage(hit.getImage(), startX + (column * tileSize), startY + (row * tileSize), null);
				}
			}
		}
	}

	private void drawShips(Graphics g) {
		for (Ship ship : ships) {
			if (ship.getPlaced()) {
				ImageIcon shipImage = UIHelper.resizeImage(ship.getImagePath(), ship.getWidth() * tileSize - 8,
						ship.getHeight() * tileSize - 8);
				g.drawImage(shipImage.getImage(), GRID_X + (ship.getX() * tileSize) + 4,
						GRID_Y + (ship.getY() * tileSize) + 4, this);
			}
		}
	}

	public void showWinner(boolean won) {
		repaint();
		if (won) {
			JOptionPane.showMessageDialog(null, "You Win! Click OK to return to the main menu.", "Winner.",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "You Lose. Better luck next time. Click OK to return to the main menu.",
					"Loser", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void drawOppGrid(Graphics g) {
		for (int row = 0; row < oppGrid.getRows(); row++) {
			for (int column = 0; column < oppGrid.getColumns(); column++) {
				Point pos = new Point(column, row);
				if (oppGrid.getTile(pos) == GridTile.EMPTY) {
					g.setColor(new Color(0, 0, 0));
					g.drawRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
				} else if (oppGrid.getTile(pos) == GridTile.HOVER || oppGrid.getTile(pos) == GridTile.HOVERSHIP) {
					// H is hover.
					g.setColor(new Color(0, 255, 255));
					g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
				} else if (oppGrid.getTile(pos) == GridTile.SHIP) {
					// TODO Remove before final game.
					// g.setColor(new Color(66, 134, 244));
					// g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
				} else if (oppGrid.getTile(pos) == GridTile.HIT) {
					g.setColor(new Color(222, 21, 21));
					g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
					ImageIcon hit = UIHelper.resizeImage("/images/Hit.png", tileSize, tileSize);
					g.drawImage(hit.getImage(), GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), null);
					// g.setColor(new Color(222, 21, 21));
					// g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y +
					// (row * tileSize), tileSize, tileSize);
				} else if (oppGrid.getTile(pos) == GridTile.MISS) {
					g.setColor(new Color(0, 191, 255));
					g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
					ImageIcon miss = UIHelper.resizeImage("/images/Missed.png", tileSize, tileSize);
					g.drawImage(miss.getImage(), GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), null);
				}
				g.setColor(new Color(0, 0, 0));
				g.drawRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize, tileSize);
			}
		}
	}

	private void drawMyGrid(Graphics g) {
		for (int row = 0; row < myGrid.getRows(); row++) {
			for (int column = 0; column < myGrid.getColumns(); column++) {
				Point pos = new Point(column, row);
				if (myGrid.getTile(pos) == GridTile.EMPTY) {
					g.setColor(new Color(0, 0, 0));
					g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else if (myGrid.getTile(pos) == GridTile.HOVER) {
					g.setColor(new Color(0, 255, 255));
					g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else if (myGrid.getTile(pos) == GridTile.SHIP) {
					g.setColor(new Color(50, 205, 50));
					g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else if (myGrid.getTile(pos) == GridTile.HIT) {
					g.setColor(new Color(222, 21, 21));
					g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else if (myGrid.getTile(pos) == GridTile.MISS) {
					g.setColor(new Color(0, 191, 255));
					g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
					
					ImageIcon miss = UIHelper.resizeImage("/images/Missed.png", tileSize, tileSize);
					g.drawImage(miss.getImage(), GRID_X + (column * tileSize), GRID_Y + (row * tileSize), null);
				}
				g.setColor(new Color(0, 0, 0));
				g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
			}
		}
	}
}
