package com.futuresailors.battleships.view;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The panel that displays the grid and loops through the ships for that game type. Allowing the
 * player to place them on the grid before calling the controller that started it startGame method.
 * 
 * @author Michael Conroy
 */
public class PlaceShipsPanel extends JPanel {

    private static final long serialVersionUID = 7157206880287883320L;

    private final int WIDTH;
    private final int HEIGHT;
    private final int GRID_WIDTH = 520;
    private final int GRID_HEIGHT = 520;
    private final int GRID_X = 135;
    private final int GRID_Y = 80;
    private int tileSize;
    private Grid grid;
    private int currentShip = 0;
    private Ship[] ships;

    private final ImageIcon backgroundImage;
    
    /**
     * Constructor for the PlaceShipsPanel.
     * @param   width   The width of the Panel.
     * @param   height  The height of the Panel.
     * @param   grid    The grid the ships will be placed on.
     * @param   ships   The array of Ships to be placed on the grid.
     */
    public PlaceShipsPanel(int width, int height, Grid grid, Ship[] ships) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.grid = grid;
        this.ships = ships;

        backgroundImage = UIHelper.resizeImage("/images/background.jpg", WIDTH, HEIGHT);

        grid.getRows();
        createPanel();
    }
    
    /**
     * Changes the current ship that is being placed.
     * @param   newShip  The array position of the new ship.
     */
    public void updateCurrentShip(int newShip) {
        currentShip = newShip;
        repaint();
    }
    
    /**
     * Constructor for the Panel with no params.
     */
    public PlaceShipsPanel() {
        this.WIDTH = UIHelper.getWidth();
        this.HEIGHT = UIHelper.getHeight();
        backgroundImage = UIHelper.resizeImage("/images/Background1.jpg", WIDTH, HEIGHT);

        createPanel();
    }
    
    /**
     * Calculates the X of the tile under the mouse.
     * @param   x   Int of the X from the mouse.
     * @return  Integer of the X of the tile.
     */
    public int getTileXUnderMouse(int x) {
        return (x - GRID_X) / tileSize;
    }
    
    /**
     * Calculates the Y of the tile under the mouse.
     * @param   y   Int of the Y from the mouse.
     * @return  Integer of the Y of the tile.
     */
    public int getTileYUnderMouse(int y) {
        return (y - GRID_Y) / tileSize;
    }
    
    /**
     * Creates the panel and its components.
     */
    private void createPanel() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        JButton backBut = new JButton("Main Menu");
        backBut.setSize(100, 50);
        backBut.setLocation(10, 10);
        backBut.setLayout(null);
        add(backBut);
        // Will be configurable at a later date.
        tileSize = 520 / grid.getColumns();
        System.out.println("PlaceShipsPanel Created.");
    }
    
    /**
     * Draws Graphics onto the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
        g.setFont(new Font("Garamond", Font.BOLD, 50));
        g.setColor(new Color(255, 17, 0));
        g.drawChars("Place your ships.".toCharArray(), 0, 16, (WIDTH / 2) - 120, 50);

        g.setColor(new Color(255, 255, 255));
        g.fillRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        drawGrid(g);
        g.setColor(new Color(255));
        g.drawRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        drawShips(g);

        // Displays what ship is currently being placed.
        drawCurrentShipSpace(g);

        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Draws the ships on the Panel.
     * @param   g   The Graphics object for this Panel.
     */
    private void drawShips(Graphics g) {
        for (Ship ship : ships) {
            if (ship.getPlaced()) {
                ImageIcon shipImage = UIHelper.resizeImage(ship.getImagePath(),
                        ship.getWidth() * tileSize - 8, ship.getHeight() * tileSize - 8);
                g.drawImage(shipImage.getImage(), GRID_X + (ship.getX() * tileSize) + 4,
                        GRID_Y + (ship.getY() * tileSize) + 4, this);
            }
        }
    }
    
    /**
     * Checks if the rotate button has been clicked.
     * @param   pos   Point object representing the click location.
     * @return  Boolean.
     */
    public boolean checkRotateClicked(Point pos) {
        if (pos.x >= 915 && pos.x <= 985 && pos.y >= 550 && pos.y <= 620) {
            return true;
        }
        return false;
    }
    
    /**
     * Draws the section of the panel that displays the current ship.
     * @param   g   Graphics Object of this panel.
     */
    private void drawCurrentShipSpace(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(775, 80, 350, 550);
        g.setColor(new Color(255));
        g.drawRect(775, 80, 350, 550);
        g.setColor(new Color(0));
        if (currentShip != ships.length) {
            g.setFont(new Font("Garamond", Font.PLAIN, 30));
            g.drawChars("Current Ship:".toCharArray(), 0, 13, 850, 120);
            int shipWidth = (int) (ships[currentShip].getWidth() * tileSize * 1.2);
            int shipHeight = (int) (ships[currentShip].getHeight() * tileSize * 1.2);
            shipWidth = shipWidth > 345 ? 345 : shipWidth;
            shipHeight = shipHeight > 370 ? 370 : shipHeight;
            ImageIcon shipImage = UIHelper.resizeImage(ships[currentShip].getImagePath(), shipWidth,
                    shipHeight);
            // Place the ship in the centre of the current ship space
            g.drawImage(shipImage.getImage(), 775 + (175 - (shipImage.getIconWidth() / 2)),
                    80 + (235 - (shipImage.getIconHeight() / 2)), this);
            // Draw the rotate button.
            ImageIcon rotateImage = UIHelper.resizeImage("/images/rotate.png", 70, 70);
            g.drawImage(rotateImage.getImage(), 915, 550, this);
        } else {
            grid.clearHoverTiles();
            g.setFont(new Font("Garamond", Font.PLAIN, 30));
            g.drawChars("All Ships Placed".toCharArray(), 0, 16, 850, 120);
            g.setFont(new Font("Garamond", Font.PLAIN, 25));
            g.drawChars("Click anywhere to proceed.".toCharArray(), 0, 26, 800, 250);
        }
    }
    
    /**
     * Calculates if a given x and y are over a valid gridspace.
     * @param   x   The X value to be checked.
     * @param   y   The Y value to be checked.
     * @return  boolean
     */
    public boolean overGridSpace(int x, int y) {
        if (x > GRID_X && x < GRID_X + GRID_WIDTH && y < GRID_Y + GRID_HEIGHT && y > GRID_Y) {
            return true;
        }
        return false;
    }
    
    /**
     * Calculates the X of the tile under the mouse.
     * @param   g   Graphics Object of the Panel.
     */
    private void drawGrid(Graphics g) {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int column = 0; column < grid.getColumns(); column++) {
                Point pos = new Point(column, row);
                if (grid.getTile(pos) == GridTile.EMPTY) {
                    g.setColor(new Color(0, 0, 0));
                    g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                } else if (grid.getTile(pos) == GridTile.HOVER) {
                    // H is hover.
                    g.setColor(new Color(128, 128, 128));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                } else if (grid.getTile(pos) == GridTile.SHIP) {
                    g.setColor(new Color(50, 205, 50));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                }  else if (grid.getTile(pos) == GridTile.LAND) {
                    g.setColor(new Color(43, 179, 124));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                } 
                g.setColor(new Color(0, 0, 0));
                g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                        tileSize);
            }
        }
    }
}