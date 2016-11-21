package com.futuresailors.battleships.view;

import com.futuresailors.battleships.controller.SinglePlayerController;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Listener for selection of difficulty for the single player games.
 * 
 * @author Joe Baldwin
 */
public class DifficultySelectionListener implements ActionListener {

    private SinglePlayerController controller;
    private JPanel panel;

    public DifficultySelectionListener(JPanel panel, SinglePlayerController controller) {
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
    public void actionPerformed(ActionEvent event) {
        if ("Easy".equals(event.getActionCommand())) {
            controller.selectEasyMode();
        } else if ("Moderate".equals(event.getActionCommand())) {
            controller.selectModerateMode();
        } else if ("Hard".equals(event.getActionCommand())) {
            controller.selectHardMode();
        } else if ("Main Menu".equals(event.getActionCommand())) {
            controller.returnToMenu();
        }
    }
}
