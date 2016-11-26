package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.reloaded.ReloadedMapSelectionListener;
import com.futuresailors.battleships.view.reloaded.ReloadedMapSelectionPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReloadedMapSelectionController {

    private JFrame window;

    public ReloadedMapSelectionController(JFrame window) {
        this.window = window;
        showMenu();
    }

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

    public void returnToMenu() {
        MainMenuController main = new MainMenuController(window);
        main.showMenu();
    }

    public void startSmallGame() {
        ReloadedModeController small = new ReloadedModeController(window, 8);
    }

    public void startMediumGame() {
        ReloadedModeController medium = new ReloadedModeController(window, 10);
    }

    public void startLargeGame() {
        ReloadedModeController large = new ReloadedModeController(window, 13);
    }

}
