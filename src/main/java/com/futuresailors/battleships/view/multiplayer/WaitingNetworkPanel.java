package com.futuresailors.battleships.view.multiplayer;

import com.futuresailors.battleships.UIHelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WaitingNetworkPanel extends JPanel {

    private static final long serialVersionUID = -161318141413754470L;

    private final int WIDTH;
    private final int HEIGHT;
    private String hostname = "Your IP is: ";
    JTextField ipField;

    public WaitingNetworkPanel(String hostname, int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.hostname += hostname;
        createPanel();
    }

    public String getConnectIP() {
        return ipField.getText();
    }

    private void createPanel() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        JButton backBut = new JButton("Main Menu");
        backBut.setSize(100, 50);
        backBut.setLocation(10, 10);
        backBut.setLayout(null);
        add(backBut);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon gridImage = UIHelper.resizeImage("/images/Background1.jpg", WIDTH, HEIGHT);
        g.drawImage(gridImage.getImage(), 0, 0, this);
        ;
        g.setFont(new Font("Garamond", Font.BOLD, 50));
        g.setColor(new Color(255, 17, 0));
        g.drawChars("Multiplayer".toCharArray(), 0, 11, (WIDTH / 2) - 120, 50);

        g.setFont(new Font("Garamond", Font.PLAIN, 30));
        g.drawChars("Waiting for a connection".toCharArray(), 0, 24, (WIDTH / 2) - 145,
                HEIGHT / 2 - 40);
        g.drawChars(hostname.toCharArray(), 0, hostname.length(), (WIDTH / 2) - 145, HEIGHT / 2);
    }
}
