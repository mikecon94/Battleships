package com.futuresailors.battleships.model;

import java.awt.Point;

/**
 * This object represents a ship on the board and its respective tiles. It also contains the ships
 * icons file path and methods that change the state of the ship in the game.
 * 
 * @author Joe Baldwin, Michael Conroy
 */

public class Ship {

    // Not final as these will change on rotation.
    private int width;
    private int height;
    // Whether the ship has been sunk
    private boolean sunk = false;
    // Tiles the ship occupies
    private ShipTile[] tiles;
    private String imagePath;
    // If the ship has been placed on the board already
    private boolean placed = false;
    // Ship location represented as a Point
    Point pos;

    // Constructor
    public Ship(int width, int height, String imagePath) {
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;
        // Init tile array with the amount of tiles the ship occupys
        tiles = new ShipTile[height * width];
        System.out.println("New Ship Created, Height: " + height + " Width: " + width + " Tiles: "
                + tiles.length);
    }

    /**
     * Checks if the opponent scored a hit on a ships tile.
     * 
     * @param location - A Point object for the tile location that was hit
     * @return Whether the player has scored a hit as a boolean
     */
    public boolean hit(Point location) {
        for (ShipTile tile : tiles) {
            if (tile.getPosition() == location) {
                tile.setHit();
                //Check if Ship is sunk and update status if so.
                updateSunk();
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any of the tiles have been hit and if they have all been hit returns true.
     * 
     * @return Whether ship is sunk as boolean
     */
    public void updateSunk() {
        // Loops through each tile
        for (ShipTile tile : tiles) {
            // If a tile is not hit
            if (tile.getHit() != true) {
                return;
            } 
        }
        // Sets ship to sunk
        sunk = true;
    }

    /**
     * @return If ship is sunk as boolean.
     */
    public boolean isSunk() {
        return sunk;
    }

    /**
     * @return If ship is placed as boolean.
     */
    public boolean getPlaced() {
        return placed;
    }

    /**
     * Initialises the tiles array based on the top left hand tile.
     * 
     * @param pos - A Point representing the top left hand tile of the ship
     */
    public void placeShip(Point pos) {
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point point = new Point(pos.x + j, pos.y + i);
                tiles[index] = new ShipTile(point);
                index++;
            }
        }
        placed = true;
        this.pos = pos;
    }

    /**
     * Allows ships to be rotated to either be horizontal or vertical.
     */
    public void rotateShip() {
        if (imagePath.contains("Horizontal")) {
            imagePath = imagePath.replace("Horizontal", "Vertical");
        } else {
            imagePath = imagePath.replace("Vertical", "Horizontal");
        }
        int tempWidth = width;
        width = height;
        height = tempWidth;
    }

    public Point getPos() {
        return pos;
    }

    public int getX() {
        return (int) pos.getX();
    }

    public int getY() {
        return (int) pos.getY();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getImagePath() {
        return imagePath;
    }
}