package com.futuresailors.battleships.view;

import com.futuresailors.battleships.controller.MainMenuController;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Acts as the listener for the Main Menu.
 * 
 * @author Michael Conroy
 */
public class MainMenuListener implements ActionListener {

    private MainMenuController controller;
    private JPanel panel;

    public MainMenuListener(JPanel panel, MainMenuController controller) {
        this.controller = controller;
        this.panel = panel;
        addListeners();
    }

    /**
     * Loops round all the components on the main menu panel and adds this listener to all of the
     * buttons.
     */
    private void addListeners() {
        Component[] comps = panel.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addActionListener(this);
            }
        }
    }

    /**
     * Detects which button has been clicked and performs the appropriate action.
     */
    public void actionPerformed(ActionEvent e) {
        if ("Singleplayer".equals(e.getActionCommand())) {
            System.out.println("Start Game Clicked.");
            controller.startGameSelection();
        } else if ("Multiplayer".equals(e.getActionCommand())) {
            System.out.println("Multiplayer Clicked.");
            controller.startMultiplayer();
        } else if ("Rules".equals(e.getActionCommand())) {
            System.out.println("Rules Clicked.");
            controller.showRules();
        } else if ("Exit Game".equals(e.getActionCommand())) {
            System.out.println("Exit Game Clicked.");
            controller.exit();
        }
    }
}
