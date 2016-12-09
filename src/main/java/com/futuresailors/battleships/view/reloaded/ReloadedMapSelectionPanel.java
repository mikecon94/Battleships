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

        //Classic Map
        JButton small = new JButton("<html>Small<br/><center>8x8</html>");
        small.setSize(150, 75);
        small.setLocation((WIDTH / 2) - 200, (int) (HEIGHT - (HEIGHT * 0.65)));
        add(small);

        JButton medium = new JButton("<html>Medium<br/><center>10x10</html>");
        medium.setSize(150, 75);
        medium.setLocation((WIDTH / 2) - 200, (int) (HEIGHT - (HEIGHT * 0.50)));
        add(medium);

        JButton large = new JButton("<html>Large<br/><center>13x13</html>");
        large.setSize(150, 75);
        large.setLocation((WIDTH / 2) - 200, (int) (HEIGHT - (HEIGHT * 0.35)));
        add(large);
        
        //Custom Maps
        JButton map1 = new JButton("<html>Maelstrom<br/><center>10x10</html>");
        map1.setSize(150, 75);
        map1.setLocation((WIDTH / 2) - 0, (int) (HEIGHT - (HEIGHT * 0.65)));
        add(map1);

        JButton map2 = new JButton("<html>Dreadnought<br/><center>13x13</html>");
        map2.setSize(150, 75);
        map2.setLocation((WIDTH / 2) - 0, (int) (HEIGHT - (HEIGHT * 0.50)));
        add(map2);

        JButton map3 = new JButton("<html>Corvette<br/><center>13x13</html>");
        map3.setSize(150, 75);
        map3.setLocation((WIDTH / 2) - 0, (int) (HEIGHT - (HEIGHT * 0.35)));
        add(map3);
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
