package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.reloaded.ReloadedMapSelectionListener;
import com.futuresailors.battleships.view.reloaded.ReloadedMapSelectionPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Controller for reloaded map selection.
 * 
 * @author Joe Baldwin
 */
public class ReloadedMapSelectionController {
    
    private JFrame window;
    
    /**
     * Creates the panel and shows the menu.
     * @param window - The JFrame to add the panel to.
     */
    public ReloadedMapSelectionController(JFrame window) {
        this.window = window;
        showMenu();
    }
    
    /**
     * Shows the menu and inits the listener.
     */
    private void showMenu() {
        window.getContentPane().removeAll();
        JPanel mapSelectPanel = new ReloadedMapSelectionPanel(UIHelper.getWidth(),
                UIHelper.getHeight());
        mapSelectPanel.setVisible(true);
        @SuppressWarnings("unused")
        ReloadedMapSelectionListener menuListener = new ReloadedMapSelectionListener(mapSelectPanel,
                this);
        window.add(mapSelectPanel);
        window.repaint();
    }
    
    /**
     * Returns to the main menu.
     */
    public void returnToMenu() {
        MainMenuController main = new MainMenuController(window);
        main.showMenu();
    }
    
    /**
     * Starts a game with a 8x8 grid.
     */
    public void startSmallGame() {
        @SuppressWarnings("unused")
        ReloadedModeController small = new ReloadedModeController(window, 8);
    }
    
    /**
     * Starts a game with a 10x10 grid.
     */
    public void startMediumGame() {
        @SuppressWarnings("unused")
        ReloadedModeController medium = new ReloadedModeController(window, 10);
    }
    
    /**
     * Starts a game with a 13x13 grid.
     */
    public void startLargeGame() {
        @SuppressWarnings("unused")
        ReloadedModeController large = new ReloadedModeController(window, 13);
    }
    
    /**
     * Starts a game with the Maelstrom 10x10 grid.
     */
    public void startMap1() {
        ReloadedModeController map1 = new ReloadedModeController(window, 10);
        map1.startCircleMap();
    }
    
    /**
     * Starts a game with the Dreadnought 13x13 grid.
     */
    public void startMap2() {
        ReloadedModeController map2 = new ReloadedModeController(window, 13);
        map2.startDreadnoughtMap();
    }

    /**
     * Starts a game with the Corvette 13x13 grid.
     */
    public void startMap3() {
        ReloadedModeController map3 = new ReloadedModeController(window, 13);
        map3.startCorvetteMap();
    }
}