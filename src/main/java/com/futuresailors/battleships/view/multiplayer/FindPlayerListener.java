package com.futuresailors.battleships.view.multiplayer;

import com.futuresailors.battleships.controller.MultiPlayerController;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FindPlayerListener implements ActionListener {

    private MultiPlayerController controller;
    private JPanel panel;

    public FindPlayerListener(JPanel panel, MultiPlayerController controller) {
        this.controller = controller;
        this.panel = panel;
        addListeners();
    }

    private void addListeners() {
        Component[] comps = panel.getComponents();
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.addActionListener(this);
            }
        }
    }

    public void actionPerformed(ActionEvent event) {
        if ("Start Server".equals(event.getActionCommand())) {
            controller.startServer();
        } else if ("Connect".equals(event.getActionCommand())) {
            controller.connect();
        } else if ("Main Menu".equals(event.getActionCommand())) {
            controller.returnToMenu();
        }
    }
}
