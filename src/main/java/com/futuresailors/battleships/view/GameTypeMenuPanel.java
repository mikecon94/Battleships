package com.futuresailors.battleships.view;

import com.futuresailors.battleships.UIHelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel for selection of your game type.
 * @author Joe Baldwin
 */
public class GameTypeMenuPanel extends JPanel {

    private static final long serialVersionUID = -3329663548441626093L;

    private final int WIDTH;
    private final int HEIGHT;
    
    /**
     * Constructor for the GameTypeMenuPanel with a custom width and height.
     * @param   width     Width of the panel.
     * @param   height    Height of the panel.
     */
    public GameTypeMenuPanel(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        createPanel();
    }
    
    /**
     * Constructor for the GameTypeMenuPanel with the default width and height.
     */
    public GameTypeMenuPanel() {
        this.WIDTH = UIHelper.getWidth();
        this.HEIGHT = UIHelper.getHeight();
        createPanel();
    }
    
    /**
     * Creates the panel, inits the buttons and ui.
     */
    private void createPanel() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        JButton backBut = new JButton("Main Menu");
        backBut.setSize(100, 50);
        backBut.setLocation(10, 10);
        backBut.setLayout(null);
        add(backBut);
        
        JButton classic = new JButton("Classic");
        classic.setSize(150, 75);
        classic.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.65)));
        add(classic);

        JButton reloaded = new JButton("Reloaded");
        reloaded.setName("Reloaded Mode");
        reloaded.setSize(150, 75);
        reloaded.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.50)));
        add(reloaded);
        setName("GameTypeMenuPanel");
        System.out.println("GameTypeMenuPanel Created.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon bg = UIHelper.resizeImage("/images/background.jpg", WIDTH, HEIGHT);
        g.drawImage(bg.getImage(), 0, 0, null);
        
        g.setFont(new Font("Garamond", Font.BOLD, 50));
        g.setColor(new Color(255, 17, 0));
        g.drawChars("Select A Game Type".toCharArray(), 0, 18, (WIDTH / 2) - 180, 50);

    }
}
