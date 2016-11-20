package com.futuresailors.battleships.view;

import com.futuresailors.battleships.controller.Controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * This adds the appropriate listeners to the panels.
 * 
 * @author Michael Conroy
 */
public class GameListener {

    private Controller controller;
    private JPanel panel;

    public GameListener(JPanel panel, Controller controller) {
        this.controller = controller;
        this.panel = panel;
        addListeners();
    }

    private void addListeners() {
        // 0 is the Start Game Button.
        panel.getComponent(0).addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent event) {
                // Check which button was clicked:
                // 0 = Start Game
                controller.returnToMenu();
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }

            public void mousePressed(MouseEvent event) {
            }
        });

        panel.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent event) {
                controller.mouseClicked(new Point(event.getX(), event.getY()));
            }

            public void mouseClicked(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }

            public void mousePressed(MouseEvent event) {
            }
        });

        panel.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent event) {
                controller.mouseMoved(new Point(event.getX(), event.getY()));
            }

            public void mouseDragged(MouseEvent event) {
                controller.mouseMoved(new Point(event.getX(), event.getY()));
            }
        });
    }
}
