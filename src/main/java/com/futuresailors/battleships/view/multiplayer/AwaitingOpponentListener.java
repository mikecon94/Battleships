package com.futuresailors.battleships.view.multiplayer;

import com.futuresailors.battleships.controller.MultiPlayerController;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * This is the listener for the awaiting opponent screen.
 * @author Mike Conroy
 */

public class AwaitingOpponentListener implements ActionListener {

    private MultiPlayerController controller;
    private JPanel panel;
    
    /**
     * The Constructor for this listener.
     * @param   controller  An instance of MultiPlayerController.
     * @param   panel       The JPanel this listener will applied to.  
     */
    public AwaitingOpponentListener(JPanel panel, MultiPlayerController controller) {
        this.controller = controller;
        this.panel = panel;
        addListeners();
    }
    
    /**
     * Loops round all the components on the main menu panel and adds this listener to all of the
     * JButtons.
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
     * The Constructor for this listener.
     * @param   event  An instance of ActionEvent.  
     */
    public void actionPerformed(ActionEvent event) {
        if ("Main Menu".equals(event.getActionCommand())) {
            controller.returnToMenu();
        }
    }
}
