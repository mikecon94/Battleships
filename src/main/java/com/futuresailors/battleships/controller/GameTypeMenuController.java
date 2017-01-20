package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.GameTypeMenuListener;
import com.futuresailors.battleships.view.GameTypeMenuPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Controller to choose game type.
 * 
 * @author Joe Baldwin
 */

public class GameTypeMenuController {

    private JFrame window;
    
    /**
     * Instantiates the JFrame window and then starts it.
     * @param window this contains the jpanels which displays the menu
     */
    public GameTypeMenuController(JFrame window) {
        this.window = window;
        start();
    }

    /**
     * Executes the showMenu method.
     */
    public void start() {
        showMenu();
    }
    
    /**
     * Populates the window JFrame with JPanels and displays the menu by setting them to 
     * visible.
     */
    private void showMenu() {
        window.getContentPane().removeAll();
        JPanel gameTypePanel = new GameTypeMenuPanel(UIHelper.getWidth(), UIHelper.getHeight());
        gameTypePanel.setVisible(true);
        @SuppressWarnings("unused")
        GameTypeMenuListener menuListener = new GameTypeMenuListener(gameTypePanel, this);
        window.add(gameTypePanel);
        window.repaint();
    }

    /**
     * This method starts Reloaded Mode. The controller is instantiated and then the panel is 
     * created to show the menu.
     */
    public void startReloadedMode() {
        @SuppressWarnings("unused")
        ReloadedMapSelectionController controller = new ReloadedMapSelectionController(window);
    }

    /**
     * This method starts Classic Mode. The controller is instantiated and then the panel is 
     * created to show the menu.
     */
    public void startClassicMode() {
        @SuppressWarnings("unused")
        GameTypeController controller = new SinglePlayerController(window);
    }
    
    /**
     * Returns to the main menu. The relevant controller is instantiated and then
     * the main menu is displayed.
     */
    public void returnToMenu() {
        MainMenuController main = new MainMenuController(window);
        main.showMenu();
        // TODO Display a JOptionPane asking if the user is sure they wish to
        // return to the menu.
    }
}
