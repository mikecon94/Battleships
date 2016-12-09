package com.futuresailors.battleships.view.reloaded;

import com.futuresailors.battleships.controller.ReloadedMapSelectionController;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ReloadedMapSelectionListener implements ActionListener {
    
    private ReloadedMapSelectionController controller;
    private JPanel panel;

    public ReloadedMapSelectionListener(JPanel panel, ReloadedMapSelectionController controller) {
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
        if ("<html>Small<br/><center>8x8</html>".equals(event.getActionCommand())) {
            controller.startSmallGame();
        } else if ("<html>Medium<br/><center>10x10</html>".equals(event.getActionCommand())) {
            controller.startMediumGame();
        } else if ("<html>Large<br/><center>13x13</html>".equals(event.getActionCommand())) {
            controller.startLargeGame();
        } else if ("Main Menu".equals(event.getActionCommand())) {
            controller.returnToMenu();
        } else if ("<html>Maelstrom<br/><center>10x10</html>".equals(event.getActionCommand())) {
            controller.startMap1();
        } else if ("<html>Dreadnought<br/><center>13x13</html>".equals(event.getActionCommand())) {
            controller.startMap2();
        } else if ("<html>Corvette<br/><center>13x13</html>".equals(event.getActionCommand())) {
            controller.startMap3();
        }
    }

}
