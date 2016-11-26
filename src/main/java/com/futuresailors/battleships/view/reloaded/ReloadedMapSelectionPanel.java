package com.futuresailors.battleships.view.reloaded;

import com.futuresailors.battleships.UIHelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This JPanel displays available maps in reloaded mode.
 * 
 * @author Joe Baldwin
 */
@SuppressWarnings("serial")
public class ReloadedMapSelectionPanel extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;

    public ReloadedMapSelectionPanel(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        createPanel();
    }

    public ReloadedMapSelectionPanel() {
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

        // Each button should either be an image of the map or have on above it
        JButton classicMap = new JButton("Classic Map");
        classicMap.setSize(150, 75);
        classicMap.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.85)));
        add(classicMap);

        JButton small = new JButton("Small");
        small.setSize(150, 75);
        small.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.70)));
        add(small);

        JButton medium = new JButton("Medium");
        medium.setSize(150, 75);
        medium.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.55)));
        add(medium);

        JButton large = new JButton("Large");
        large.setSize(150, 75);
        large.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.40)));
        add(large);

        JButton map1 = new JButton("Map 1");
        map1.setSize(150, 75);
        map1.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.25)));
        add(map1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon bg = UIHelper.resizeImage("/images/background.jpg", WIDTH, HEIGHT);
        g.drawImage(bg.getImage(), 0, 0, null);
        
        g.setFont(new Font("Garamond", Font.BOLD, 50));
        g.setColor(new Color(255, 17, 0));
        g.drawChars("Choose A Map".toCharArray(), 0, 12, (WIDTH / 2) - 180, 50);
    }
}
