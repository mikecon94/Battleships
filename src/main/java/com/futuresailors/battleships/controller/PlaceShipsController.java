package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.PlaceShipsPanel;

import java.awt.Point;

import javax.swing.JFrame;

/**
 * This controller adds a PlaceShipsPanel to the window and is used to allow users to choose the
 * tiles they wish to place their ships in.
 * 
 * @author Michael Conroy
 */
public class PlaceShipsController implements Controller {

    private JFrame window;
    private GameTypeController parentController;
    private PlaceShipsPanel panel;
    // Maybe make this configurable going forwards
    // Pass the number of possible ships in constructor.
    private Ship[] ships;
    private int currentShip = 0;
    private Grid grid;
    private boolean allShipsPlaced = false;

    /**
     * Creates the panel, adds it to the window and adds the listener.
     * 
     * @param grid - The player's grid.
     * @param ships - The ships that need to be placed.
     * @param controller - The parent controller of this controller which it will return to once
     *            complete.
     */
    public PlaceShipsController(Grid grid, Ship[] ships, GameTypeController controller,
            JFrame window) {
        this.window = window;
        this.parentController = controller;
        this.grid = grid;
        this.ships = ships;
        addPanel();
        panel.updateCurrentShip(currentShip);
    }

    /**
     * Adds the view to the window to allow the user to interact with this controller.
     */
    private void addPanel() {
        window.getContentPane().removeAll();
        panel = new PlaceShipsPanel(UIHelper.getWidth(), UIHelper.getHeight(), grid, ships);
        window.add(panel);
        window.repaint();
        @SuppressWarnings("unused")
        GameListener listener = new GameListener(panel, this);
    }

    /**
     * Called by the listener to tell the mouse has been clicked. If all the ships have been placed
     * then the user can click anywhere and move onto the actual gameplay itself. If the ships
     * haven't been placed it will check whether the position clicked in is a valid tile to place a
     * ship and if so it will place it, move onto the next ship, and update the view.
     */
    public void mouseClicked(Point pos) {
        if (!allShipsPlaced) {
            Point newPos = new Point(panel.getTileXUnderMouse(pos.x),
                    panel.getTileYUnderMouse(pos.y));
            if (panel.overGridSpace(pos.x, pos.y)
                    && grid.checkValidPlace(newPos, ships[currentShip])) {
                grid.placeShip(newPos, ships[currentShip]);
                currentShip++;
                if (currentShip == ships.length) {
                    allShipsPlaced = true;
                }
                panel.updateCurrentShip(currentShip);
            } else if (panel.checkRotateClicked(pos)) {
                ships[currentShip].rotateShip();
                panel.repaint();
            }
        } else {
            parentController.startGame();
        }
    }

    /**
     * Called by the listener when the mouse is moved. Detects the state of the board and if all the
     * ships haven't been placed it will display what tiles the ship will take up if the user clicks
     * to place it.
     */
    public void mouseMoved(Point pos) {
        if (!allShipsPlaced) {
            grid.clearHoverTiles();
            if (panel.overGridSpace(pos.x, pos.y)) {
                Point newPos = new Point(panel.getTileXUnderMouse(pos.x),
                        panel.getTileYUnderMouse(pos.y));
                grid.hoverShip(newPos, ships[currentShip]);
            }
            // Repaint the panel since the model has been updated.
            panel.repaint();
        }
    }

    /**
     * Returns the user to the Main Menu by passing creating a new MainMenuController and calling
     * the showMenu method.
     */
    public void returnToMenu() {
        grid.clearHoverTiles();
        parentController.returnToMenu();
    }
}