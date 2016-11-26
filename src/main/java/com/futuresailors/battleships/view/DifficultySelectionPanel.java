package com.futuresailors.battleships.view;

import com.futuresailors.battleships.UIHelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel for selection of difficulty for the single player games.
 * 
 * @author Joe Baldwin
 */
public class DifficultySelectionPanel extends JPanel {

    private static final long serialVersionUID = 4773467669120329106L;

    private final int WIDTH;
    private final int HEIGHT;

    public DifficultySelectionPanel(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        createPanel();
    }

    public DifficultySelectionPanel() {
        this.WIDTH = UIHelper.getWidth();
        this.HEIGHT = UIHelper.getHeight();
        createPanel();
    }

    private void createPanel() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        
        JButton backBut = new JButton("Main Menu");
        backBut.setSize(100, 50);
        backBut.setLocation(10, 10);
        backBut.setLayout(null);
        add(backBut);

        JButton easy = new JButton("Easy");
        easy.setSize(150, 75);
        easy.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.65)));
        add(easy);

        JButton moderate = new JButton("Moderate");
        moderate.setSize(150, 75);
        moderate.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.50)));
        add(moderate);

        JButton hard = new JButton("Hard");
        hard.setSize(150, 75);
        hard.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.35)));
        add(hard);

        setName("DifficultySelectionPanel");
        System.out.println("DifficultySelectionPanel Created.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon bg = UIHelper.resizeImage("/images/background.jpg", WIDTH, HEIGHT);
        g.drawImage(bg.getImage(), 0, 0, null);

        g.setFont(new Font("Garamond", Font.BOLD, 50));
        g.setColor(new Color(255, 17, 0));
        g.drawChars("Select Difficulty".toCharArray(), 0, 17, (WIDTH / 2) - 170, 50);
    }
}
