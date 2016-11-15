package com.futuresailors.battleships.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;

/**
 * The JPanel that creates and displays the Main Menu.
 * 
 * @author Michael Conroy
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    public MainMenuPanel(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        createPanel();
    }

    public MainMenuPanel() {
        this.WIDTH = UIHelper.getWidth();
        this.HEIGHT = UIHelper.getHeight();
        createPanel();
    }

    private void createPanel() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        // JButton startGameBut = UIHelper.createCustomButton("src/main/resources/icon.png", 100,
        // 50);
        JButton singleButton = new JButton("Singleplayer");
        singleButton.setSize(150, 75);
        singleButton.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.70)));
        add(singleButton);

        JButton multiButton = new JButton("Multiplayer");
        multiButton.setSize(150, 75);
        multiButton.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.55)));
        add(multiButton);

        JButton showRulesBut = new JButton("Rules");
        showRulesBut.setName("StartGameButton");
        showRulesBut.setSize(150, 75);
        showRulesBut.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.40)));
        add(showRulesBut);

        JButton exitBut = new JButton("Exit Game");
        exitBut.setSize(150, 75);
        exitBut.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.25)));
        add(exitBut);
        setName("MainMenuPanel");
        System.out.println("MainMenuPanel Created.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon bg = UIHelper.resizeImage("/images/Background1.jpg", WIDTH, HEIGHT);
        g.drawImage(bg.getImage(), 0, 0, null);

        // Draw the title.
        ImageIcon title = UIHelper.resizeImage("/images/Title.png", 1100, 150);
        g.drawImage(title.getImage(), (WIDTH / 2) - 550, 10, null);
    }
}