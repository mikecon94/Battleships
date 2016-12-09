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
        @SuppressWarnings("unused")
        ReloadedModeController small = new ReloadedModeController(window, 8);
    }

    public void startMediumGame() {
        @SuppressWarnings("unused")
        ReloadedModeController medium = new ReloadedModeController(window, 10);
    }

    public void startLargeGame() {
        @SuppressWarnings("unused")
        ReloadedModeController large = new ReloadedModeController(window, 13);
    }

    public void startMap1() {
        ReloadedModeController map1 = new ReloadedModeController(window, 10);
        map1.startCircleMap();
    }

    public void startMap2() {
        ReloadedModeController map2 = new ReloadedModeController(window, 13);
        map2.startDreadnoughtMap();
    }

    public void startMap3() {
        ReloadedModeController map3 = new ReloadedModeController(window, 13);
        map3.startCorvetteMap();
    }
}