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
        start();
    }

    public void start() {
        showMenu();
    }

    private void showMenu() {
        window.getContentPane().removeAll();
        JPanel mapSelectPanel = new ReloadedMapSelectionPanel(UIHelper.getWidth(),
            UIHelper.getHeight());
        mapSelectPanel.setVisible(true);
        @SuppressWarnings("unused")
        ReloadedMapSelectionListener menuListener = 
        new ReloadedMapSelectionListener(mapSelectPanel, this);
        window.add(mapSelectPanel);
        window.repaint();
    }
    
    public void returnToMenu() {
        MainMenuController main = new MainMenuController(window);
        main.showMenu();
        // TODO Display a JOptionPane asking if the user is sure they wish to
        // return to the menu.
    }
    
    public void chooseMap(String map) {
        if (map == "s") {
            System.out.println("Small");
            ReloadedModeController small = new ReloadedModeController(window,5);
        } else if (map == "m") {
            ReloadedModeController medium = new ReloadedModeController(window,10);
        } else if (map == "l") {
            ReloadedModeController large = new ReloadedModeController(window,15);
        }
    }

}
