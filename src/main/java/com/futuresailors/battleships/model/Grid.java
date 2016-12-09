package com.futuresailors.battleships.model;

import com.futuresailors.battleships.UIHelper;

import java.awt.Point;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents a grid that ships can be placed on.
 * 
 * @author Michael Conroy
 */
public class Grid {

    private GridTile[][] grid;
    private int gridSize = 10;

    public Grid() {
        // Create new 2D array for the grid using standard sizes.
        grid = new GridTile[gridSize][gridSize];
        createNewGrid();
    }

    public Grid(int size) {
        // Create grid with given sizes.
        // Use default if size is less than 5 or greater than 30.
        if (size < 5 || size > 30) {
            grid = new GridTile[gridSize][gridSize];
        } else {
            gridSize = size;
            grid = new GridTile[size][size];
        }
        createNewGrid();
    }

    public boolean dropBomb(Point target) {
        if (grid[target.y][target.x] == GridTile.SHIP
                || grid[target.y][target.x] == GridTile.HOVERSHIP) {
            grid[target.y][target.x] = GridTile.HIT;
            return true;
        } else {
            grid[target.y][target.x] = GridTile.MISS;
            return false;
        }
    }

    /**
     * Returns the value of what is in the given tile.
     * 
     * @param pos - The tile that should be returned.
     * @return A char that is the value in the requested tile.
     */
    public GridTile getTile(Point pos) {
        return grid[pos.y][pos.x];
    }

    public boolean checkGameOver() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == GridTile.SHIP || grid[y][x] == GridTile.HOVERSHIP) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sets a collection of tiles to 'H' which the view then knows to render as a tile being hovered
     * over.
     * 
     * @param pos - The top left position to represent the ship.
     * @param ship - The ship currently being placed.
     */
    public void hoverShip(Point pos, Ship ship) {
        // Clears any tiles that are currently being hovered over.
        clearHoverTiles();
        // Checks if it is a valid place for the ship to be potentially placed.
        if (checkValidPlace(pos, ship)) {
            for (int yIndex = 0; yIndex < ship.getHeight(); yIndex++) {
                for (int xIndex = 0; xIndex < ship.getWidth(); xIndex++) {
                    grid[pos.y + yIndex][pos.x + xIndex] = GridTile.HOVER;
                }
            }
        }
    }

    /**
     * Sets a collection of tiles to 'H' which the view then knows to render as a tile being hovered
     * over.
     * 
     * @param pos - The top left position to represent the ship.
     */
    public void hoverBomb(Point pos) {
        // Clears any tiles that are currently being hovered over.
        clearHoverTiles();
        // Check the tile is a valid tile to hover.
        if (pos.x >= 0 && pos.x < gridSize && pos.y >= 0 && pos.y < gridSize) {
            if (grid[pos.y][pos.x] == GridTile.EMPTY) {
                grid[pos.y][pos.x] = GridTile.HOVER;
            } else if (grid[pos.y][pos.x] == GridTile.SHIP) {
                grid[pos.y][pos.x] = GridTile.HOVERSHIP;
            }
        }
    }

    /**
     * Reset any hover tiles back to empty.
     */
    public void clearHoverTiles() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == GridTile.HOVER) {
                    grid[y][x] = GridTile.EMPTY;
                } else if (grid[y][x] == GridTile.HOVERSHIP) {
                    grid[y][x] = GridTile.SHIP;
                }
            }
        }
    }

    /**
     * Initialises the grid setting all the tiles to empty (called at the start of a new game.
     */
    private void createNewGrid() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] = GridTile.EMPTY;
            }
        }
    }

    /**
     * @return The number of rows in the grid.
     */
    public int getRows() {
        return grid.length;
    }

    /**
     * @return The number columns in the grid.
     */
    public int getColumns() {
        return grid[0].length;
    }

    /**
     * @return The grid - 2D Array of chars.
     */
    public GridTile[][] getGrid() {
        return grid;
    }

    /**
     * Takes a ship and a top left position. Then checks whether it is a valid place for the ship to
     * be placed. ie. it isn't off the grid and there isn't already a ship there.
     * 
     * @param pos - Top left position to place the ship.
     * @param ship - The ship being placed.
     * @return True if the ship can be legally placed there. False if not.
     */
    public boolean checkValidPlace(Point pos, Ship ship) {
        // Check the ship isn't off the side of the grid.
        if (pos.x < 0 || pos.x > gridSize || pos.y < 0 || pos.y > gridSize
                || (pos.x + ship.getWidth()) > gridSize || (pos.y + ship.getHeight()) > gridSize) {
            return false;
        }

        // Loop round the width and height of the ship
        // and check that there is nothing blocking a placement.
        for (int yIndex = 0; yIndex < ship.getHeight(); yIndex++) {
            for (int xIndex = 0; xIndex < ship.getWidth(); xIndex++) {
                if (grid[pos.y + yIndex][pos.x + xIndex] == GridTile.SHIP
                        || grid[pos.y + yIndex][pos.x + xIndex] == GridTile.LAND) {
                    return false;
                }
            }
        }
        return true;
    }

    public void createCircleGrid() {
        drawLand("/maps/circle.map");
    }

    private void drawLand(String path) {
        // String mapString = UIHelper
        // .readFile(Paths.get(getClass().getResource(path).toURI()).toString());

        Scanner s = new Scanner(getClass().getResourceAsStream(path)).useDelimiter("\\A");
        String mapString = s.next();
        int currentChar = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                while (mapString.charAt(currentChar) == '\n'
                        || mapString.charAt(currentChar) == (char) 10
                        || mapString.charAt(currentChar) == (char) 13) {
                    currentChar++;
                }
                if (mapString.charAt(currentChar) == 'L') {
                    grid[y][x] = GridTile.LAND;
                }
                currentChar++;
            }
        }
    }

    public void createDreadnoughtGrid() {
        drawLand("/maps/dreadnought.map");
    }

    public void createCorvetteGrid() {
        drawLand("/maps/corvette.map");
    }

    /**
     * Drops the ship onto the grid and marks the tiles it has been placed as S. Also updates the
     * ships object itself so it knows it has been placed.
     * 
     * @param pos - Top left position of the position to place the ship.
     * @param ship - The ship being placed.
     */
    public void placeShip(Point pos, Ship ship) {
        for (int yIndex = 0; yIndex < ship.getHeight(); yIndex++) {
            for (int xIndex = 0; xIndex < ship.getWidth(); xIndex++) {
                grid[pos.y + yIndex][pos.x + xIndex] = GridTile.SHIP;
            }
        }
        ship.placeShip(pos);
    }
}