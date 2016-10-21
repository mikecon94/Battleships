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

@SuppressWarnings("serial")
public class PlaceShipsPanel extends JPanel {
	
	private final int WIDTH;
	private final int HEIGHT;
	private final int GRID_WIDTH = 550;
	private final int GRID_HEIGHT = 550;
	private final int GRID_X = 100;
	private final int GRID_Y = 80;
	//All tiles are square.
	private int tileSize;
	
	private Grid grid;
	private Ship currentShip;
	private Ship[] ships;
		
	public PlaceShipsPanel(int width, int height, Grid grid, Ship[] ships){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.grid = grid;
		this.ships = ships;
		grid.getRows();
		createPanel();
	}
	
	public void updateCurrentShip(Ship newShip){
		currentShip = newShip;
		System.out.println("Current Ship: " + currentShip.getImagePath());
		repaint();
	}
	
	public PlaceShipsPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
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
			//clearHover();			
			//System.out.println("Tile Hovered: " + getTileYUnderMouse(y) + ", " + getTileXUnderMouse(x));
			//grid[getTileYUnderMouse(y)][getTileXUnderMouse(x)] = "src/main/resources/background.jpg";
			
		} else {
			//clearHover();
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon gridImage = UIHelper.resizeImage("src/main/resources/images/background2.jpg", WIDTH, HEIGHT);
        g.drawImage(gridImage.getImage(), 0, 0, this);
	    g.setFont(new Font("Garamond", Font.PLAIN , 40));
	    g.setColor(new Color(255, 255, 255));
	    g.drawChars("Place your ships.".toCharArray(), 0, 16, (WIDTH / 2) - 120, 50);
	    
	    
	    
	    g.fillRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        drawTiles(g);
        g.setColor(new Color(255));
        g.drawRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        drawShips(g);
        
        
        //Displays what ship is currently being placed.
        drawCurrentShipSpace(g);
	}
	
	private void drawShips(Graphics g){
		for(Ship ship : ships){
			if(ship.getPlaced()){
				ImageIcon shipImage = UIHelper.resizeImage(ship.getImagePath(), ship.getWidth() * tileSize - 2, ship.getHeight() * tileSize - 2);
				g.drawImage(shipImage.getImage(), GRID_X + (ship.getX() * tileSize), GRID_Y + (ship.getY() * tileSize), this);
		
			}
		}
	}
	
	private void drawCurrentShipSpace(Graphics g){
	    g.setColor(new Color(255, 255, 255));
        g.fillRect(775, 80, 350, 550);
        g.setColor(new Color(255));
        g.drawRect(775, 80, 350, 550);
        g.setColor(new Color(0));

        g.drawChars("Current Ship:".toCharArray(), 0, 13, 850, 120);
		ImageIcon shipImage = UIHelper.resizeImage(currentShip.getImagePath(), (int) (currentShip.getWidth() * tileSize * 1.3), (int) (currentShip.getHeight() * tileSize * 1.3));
		//Place the ship in the centre of the current ship space
		g.drawImage(shipImage.getImage(), 775 + (175 - (shipImage.getIconWidth() / 2)), 80 + (235 - (shipImage.getIconHeight() / 2)), this);
    }   
	
//	private void drawPlaceableShips(Graphics g){
//		//Think about separating the vertical ships with the horizontal ones...
//		int posY = 80;
//		for(int i = 0; i < placeableShips.length; i++){
//			String imagePath = placeableShips[i].getImagePath();
//			int width = placeableShips[i].getWidth() * tileSize;
//			int height = placeableShips[i].getHeight() * tileSize;
//			//System.out.println(imagePath + " Width: " + width + " Height: " + height + " posY: " + posY);
//			ImageIcon shipImage = UIHelper.resizeImage(imagePath, width - 5, height - 5);
//			g.drawImage(shipImage.getImage(), 790, posY, this);
//			posY += height + 10;
//		}
//	}
	
	public boolean overGridSpace(int x, int y){
		if(x > GRID_X && x < GRID_X + GRID_WIDTH
				&& y < GRID_Y + GRID_HEIGHT && y > GRID_Y){
			return true;
		}
		return false;
	}
	
    private void drawTiles(Graphics g){
        for(int row = 0; row < grid.getRows(); row++){
			for(int column = 0; column < grid.getColumns(); column++){
				if(grid.getTile(column, row) == ' '){
			        g.setColor(new Color(0, 0, 0));
					g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else if(grid.getTile(column, row) == 'H'){
					//H is hover.
			        g.setColor(new Color(128, 128, 128));
					g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);
				} else if(grid.getTile(column,  row) == 'S'){
					g.setColor(new Color(255, 0, 0));
					g.fillRect(GRID_X + (column * tileSize),  GRID_Y + (row * tileSize), tileSize, tileSize);
				}
		        g.setColor(new Color(0, 0, 0));
				g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize, tileSize);	
			}
		}
    }
}