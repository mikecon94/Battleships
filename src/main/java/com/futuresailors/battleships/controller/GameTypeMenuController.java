package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.GameTypeMenuListener;
import com.futuresailors.battleships.view.GameTypeMenuPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameTypeMenuController {

    private JFrame window;

    public GameTypeMenuController(JFrame window) {
        this.window = window;
        start();
    }

    public void start() {
        showMenu();
    }

    private void showMenu() {
        window.getContentPane().removeAll();
        JPanel gameTypePanel = new GameTypeMenuPanel(UIHelper.getWidth(), UIHelper.getHeight());
        gameTypePanel.setVisible(true);
        @SuppressWarnings("unused")
        GameTypeMenuListener menuListener = new GameTypeMenuListener(gameTypePanel, this);
        window.add(gameTypePanel);
        window.repaint();
    }

    public void startReloadedMode() {
        @SuppressWarnings("unused")
        ReloadedMapSelectionController controller = new ReloadedMapSelectionController(window);
    }

    public void startClassicMode() {
        @SuppressWarnings("unused")
        GameTypeController controller = new SinglePlayerController(window, 10);
    }
    
    public void returnToMenu() {
        MainMenuController main = new MainMenuController(window);
        main.showMenu();
        // TODO Display a JOptionPane asking if the user is sure they wish to
        // return to the menu.
    }
}
