package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
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
        //@SuppressWarnings("unused")
        //GameTypeMenuListener menuListener = new GameTypeMenuListener(gameTypePanel, this);
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
        if (map == "1") {
            //Map map1 = new Map1();
        }
    }

}
