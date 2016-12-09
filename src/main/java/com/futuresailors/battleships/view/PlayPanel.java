package com.futuresailors.battleships.view;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.GridTile;
import com.futuresailors.battleships.model.Ship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the panel where the main game will be played. The controllers for each game mode will
 * create an instance of this panel and control the logic as needed.
 * 
 * @author Michael Conroy, Joe Baldwin
 */
public class PlayPanel extends JPanel {

    private static final long serialVersionUID = 5959433312203394355L;

    private final int WIDTH;
    private final int HEIGHT;
    private final int GRID_WIDTH = 520;
    private final int GRID_HEIGHT = 520;
    // My Grid
    private final int GRID_X = 100;
    private final int GRID_Y = 80;
    // Opponents Grid
    private final int GRID_2_X = 670;
    private final int GRID_2_Y = 80;
    // All tiles are square.
    private int tileSize;
    // Client grid - Left
    private Grid myGrid;
    // Opponent Grid - Right
    private Grid oppGrid;
    // Client Ships - Left
    private Ship[] ships;
    // Menu Button
    private JButton menuBut;
    // To display the turn graphically
    private boolean myTurn;

    private final ImageIcon backgroundImage;
    private final ImageIcon hitImage;
    private final ImageIcon missImage;

    private final URL missAudioPath = getClass().getResource("/audio/Miss.wav");
    private final URL hitAudioPath = getClass().getResource("/audio/Boom1.wav");
    private AudioInputStream missAudioStream;
    private AudioInputStream hitAudioStream;
    private Clip hitClip;
    private Clip missClip;

    public PlayPanel(int width, int height, Grid grid1, Grid grid2, Ship[] ships) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.myGrid = grid1;
        this.oppGrid = grid2;
        this.ships = ships;

        // Will be configurable at a later date.
        tileSize = 520 / myGrid.getColumns();
        backgroundImage = UIHelper.resizeImage("/images/background.jpg", WIDTH, HEIGHT);
        hitImage = UIHelper.resizeImage("/images/Hit.png", tileSize, tileSize);
        missImage = UIHelper.resizeImage("/images/Missed.png", tileSize, tileSize);
        myGrid.getRows();
        setupAudio();
        createPanel();
    }

    private void setupAudio() {
        try {
            missAudioStream = AudioSystem.getAudioInputStream(missAudioPath);
            hitAudioStream = AudioSystem.getAudioInputStream(hitAudioPath);
            missClip = AudioSystem.getClip();
            missClip.open(missAudioStream);
            hitClip = AudioSystem.getClip();
            hitClip.open(hitAudioStream);
            FloatControl gainControl = (FloatControl) missClip
                    .getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.0f);
            gainControl = (FloatControl) hitClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.0f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public int getTileXUnderMouse(int x) {
        return (x - GRID_2_X) / tileSize;
    }

    public boolean overGridSpace(int x, int y) {
        if (x > GRID_2_X && x < GRID_2_X + GRID_WIDTH && y < GRID_2_Y + GRID_HEIGHT
                && y > GRID_2_Y) {
            return true;
        }
        return false;
    }

    public int getTileYUnderMouse(int y) {
        return (y - GRID_2_Y) / tileSize;
    }

    private void createPanel() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        menuBut = new JButton("Main Menu");
        menuBut.setSize(100, 50);
        menuBut.setLocation(10, 10);
        menuBut.setLayout(null);
        add(menuBut);
        System.out.println("MainPlayPanel Created.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage.getImage(), 0, 0, this);
        g.setFont(new Font("Garamond", Font.BOLD, 50));
        g.setColor(new Color(255, 17, 0));
        g.drawChars("Classic Game".toCharArray(), 0, 12, (WIDTH / 2) - 120, 50);

        // Draw outline on my turn
        if (myTurn == false) {
            g.setColor(new Color(255, 17, 0));
            g.fillRect(GRID_X - 10, GRID_Y - 10, GRID_WIDTH + 20, GRID_HEIGHT + 20);
        } else {
            g.setColor(new Color(0, 174, 255));
            g.fillRect(GRID_2_X - 10, GRID_2_Y - 10, GRID_WIDTH + 20, GRID_HEIGHT + 20);
        }

        g.setColor(new Color(255, 255, 255));
        g.fillRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
        drawMyGrid(g);
        g.setColor(new Color(0, 0, 0));
        g.drawRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);

        g.setColor(new Color(255, 255, 255));
        g.fillRect(GRID_2_X, GRID_2_Y, GRID_WIDTH, GRID_HEIGHT);
        drawOppGrid(g);
        g.drawRect(GRID_2_X, GRID_2_Y, GRID_WIDTH, GRID_HEIGHT);
        // This must be called last as the ships need to be on top of the grid.
        drawShips(g);
        // Draw bombs - Must be done after drawing ships so the images are on
        // top.
        drawBombs(myGrid, GRID_X, GRID_Y, g);
        drawBombs(oppGrid, GRID_2_X, GRID_2_Y, g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBombs(Grid grid, int startX, int startY, Graphics g) {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int column = 0; column < grid.getColumns(); column++) {
                if (grid.getTile(new Point(column, row)) == GridTile.HIT) {
                    g.drawImage(hitImage.getImage(), startX + (column * tileSize),
                            startY + (row * tileSize), null);
                }
            }
        }
    }

    private void drawShips(Graphics g) {
        for (Ship ship : ships) {
            if (ship.getPlaced()) {
                if (ship.isSunk() == true) {
                    g.setColor(new Color(255, 165, 0));
                    g.fillRect(GRID_X, GRID_Y, GRID_WIDTH, GRID_HEIGHT);
                } else {
                    ImageIcon shipImage = UIHelper.resizeImage(ship.getImagePath(),
                            ship.getWidth() * tileSize - 8, ship.getHeight() * tileSize - 8);
                    g.drawImage(shipImage.getImage(), GRID_X + (ship.getX() * tileSize) + 4,
                            GRID_Y + (ship.getY() * tileSize) + 4, this);
                }
            }
        }
    }

    public void showWinner(boolean won) {
        repaint();
        if (won) {
            JOptionPane.showMessageDialog(null, "You Win! Click OK to return to the main menu.",
                    "Winner.", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "You Lose. Better luck next time. Click OK to return to the main menu.",
                    "Loser", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setMyGrid(Grid grid) {
        myGrid = grid;
        repaint();
    }

    public void setOppGrid(Grid grid) {
        oppGrid = grid;
        repaint();
    }

    private void drawOppGrid(Graphics g) {
        for (int row = 0; row < oppGrid.getRows(); row++) {
            for (int column = 0; column < oppGrid.getColumns(); column++) {
                Point pos = new Point(column, row);
                if (oppGrid.getTile(pos) == GridTile.EMPTY) {
                    g.setColor(new Color(0, 0, 0));
                    g.drawRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize),
                            tileSize, tileSize);
                } else if (oppGrid.getTile(pos) == GridTile.HOVER
                        || oppGrid.getTile(pos) == GridTile.HOVERSHIP) {
                    // H is hover.
                    g.setColor(new Color(0, 255, 255));
                    g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize),
                            tileSize, tileSize);
                } else if (oppGrid.getTile(pos) == GridTile.SHIP) {
                    // TODO Remove before final game.
                    // g.setColor(new Color(66, 134, 244));
                    // g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize),
                    // tileSize, tileSize);
                } else if (oppGrid.getTile(pos) == GridTile.HIT) {
                    g.setColor(new Color(222, 21, 21));
                    g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize),
                            tileSize, tileSize);
                    g.drawImage(hitImage.getImage(), GRID_2_X + (column * tileSize),
                            GRID_2_Y + (row * tileSize), null);
                    // g.setColor(new Color(222, 21, 21));
                    // g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y +
                    // (row * tileSize), tileSize, tileSize);
                } else if (oppGrid.getTile(pos) == GridTile.MISS) {
                    g.setColor(new Color(0, 191, 255));
                    g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize),
                            tileSize, tileSize);
                    g.drawImage(missImage.getImage(), GRID_2_X + (column * tileSize),
                            GRID_2_Y + (row * tileSize), null);
                } else if (oppGrid.getTile(pos) == GridTile.LAND) {
                    g.setColor(new Color(43, 179, 124));
                    g.fillRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize),
                            tileSize, tileSize);
                }
                g.setColor(new Color(0, 0, 0));
                g.drawRect(GRID_2_X + (column * tileSize), GRID_2_Y + (row * tileSize), tileSize,
                        tileSize);
            }
        }
    }

    private void drawMyGrid(Graphics g) {
        for (int row = 0; row < myGrid.getRows(); row++) {
            for (int column = 0; column < myGrid.getColumns(); column++) {
                Point pos = new Point(column, row);
                if (myGrid.getTile(pos) == GridTile.EMPTY) {
                    g.setColor(new Color(0, 0, 0));
                    g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                } else if (myGrid.getTile(pos) == GridTile.HOVER) {
                    g.setColor(new Color(0, 255, 255));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                } else if (myGrid.getTile(pos) == GridTile.SHIP) {
                    g.setColor(new Color(50, 205, 50));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                } else if (myGrid.getTile(pos) == GridTile.HIT) {
                    g.setColor(new Color(222, 21, 21));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                } else if (myGrid.getTile(pos) == GridTile.MISS) {
                    g.setColor(new Color(0, 191, 255));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                    g.drawImage(missImage.getImage(), GRID_X + (column * tileSize),
                            GRID_Y + (row * tileSize), null);
                } else if (myGrid.getTile(pos) == GridTile.LAND) {
                    g.setColor(new Color(43, 179, 124));
                    g.fillRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                            tileSize);
                }
                g.setColor(new Color(0, 0, 0));
                g.drawRect(GRID_X + (column * tileSize), GRID_Y + (row * tileSize), tileSize,
                        tileSize);
            }
        }
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public void playHitSound() {
        hitClip.stop();
        hitClip.setFramePosition(0);
        hitClip.start();
    }

    public void playMissSound() {
        missClip.stop();
        missClip.setFramePosition(0);
        missClip.start();
    }
}
