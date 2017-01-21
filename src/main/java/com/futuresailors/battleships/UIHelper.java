package com.futuresailors.battleships;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A helper class with some static methods which will be used by some of the View objects. We also
 * store the height & the width the window should be here to allow the JFrame & Panels to grab
 * instead of passing each time.
 * 
 * @author Michael Conroy
 */
public class UIHelper {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    /**
     * @return The Width the the window should be.
     */
    public static int getWidth() {
        return WIDTH;
    }

    /**
     * @return The Height the window should be.
     */
    public static int getHeight() {
        return HEIGHT;
    }

    // Returns a JButton that only displays the given image.
    /**
     * Creates a JButton stripped of the default formatting and displays the image passed.
     * 
     * @param imagePath - The file path to the image that the button should display.
     * @param width - The width the new button should be.
     * @param height - The height the new button should be.
     * @return JButton - The new button based on the parameters passed.
     */
    public static JButton createCustomButton(String imagePath, int width, int height) {
        ImageIcon image = resizeImage(imagePath, width, height);
        JButton button = new JButton(image);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Use these to control the other states of the buttons.
        // button.setRolloverIcon(myIcon2);
        // button.setPressedIcon(myIcon3);
        // button.setDisabledIcon(myIcon4);

        return button;
    }

    // Helper method for resizing a given Image. Used on the background and for buttons.
    /**
     * Resizes a given image to a given width & height. Used for the background, icons and button
     * images etc.
     * 
     * @param imagePath - The path to the image that needs resizing.
     * @param width - The new width for the image.
     * @param height - The new height for the image.
     * @return ImageIcon - The resized image.
     */
    public static ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon bg = new ImageIcon(UIHelper.class.getResource(imagePath));
        return new ImageIcon(bg.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }
    
    /**
     * Reads a file into the program.
     * @param   filePath    path of the file.
     */
    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
