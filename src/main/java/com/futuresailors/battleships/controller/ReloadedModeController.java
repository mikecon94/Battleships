package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

/**
 * Controller for reloaded.
 * @author Joe Baldwin.
 */

public class ReloadedModeController extends SinglePlayerController {
    
    /**
     * Constructor.
     * @param   window      JFrame.
     * @param   gridSize    size of the grid.
     */
    public ReloadedModeController(JFrame window, int gridSize) {
        super(window, gridSize);
    }
    
    /**
     * Starts a unique map.
     */
    public void startCircleMap() {
        super.startCircleMap();
    }
    
    /**
     * Starts a unique map.
     */
    public void startDreadnoughtMap() {
        super.startDreadnoughtMap();
    }
    
    /**
     * Starts a unique map.
     */
    public void startCorvetteMap() {
        super.startCorvetteMap();
    }
}