package com.futuresailors.battleships.controller;

import java.awt.Point;

import javax.swing.JFrame;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.view.GameListener;
import com.futuresailors.battleships.view.RulesPanel;

/**
 * Displays the rules to the user from the rules.html resource.
 * 
 * @author Ryan Lowers
 */
public class RulesController implements Controller {
    private JFrame window;
    private RulesPanel panel;

    /**
     * Creates the RulesView and adds it to the panel.
     * 
     * @param window - The JFrame to add the window to.
     */
    public RulesController(JFrame window) {
        window.getContentPane().removeAll();
        panel = new RulesPanel(UIHelper.getWidth(), UIHelper.getHeight());
        window.add(panel);
        window.repaint();
        @SuppressWarnings("unused")
        GameListener listener = new GameListener(panel, this);
        this.window = window;
    }

    /**
     * Returns the user to the Main Menu by passing creating a new MainMenuController and calling
     * the showMenu method.
     */
    public void returnToMenu() {
        MainMenuController main = new MainMenuController(window);
        main.showMenu();
    }

    /**
     * Not used on the RulesController.
     */
    public void mouseClicked(Point pos) {
    }

    /**
     * Not used on the RulesController.
     */
    public void mouseMoved(Point pos) {
    }
}
