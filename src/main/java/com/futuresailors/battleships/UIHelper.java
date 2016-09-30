package com.futuresailors.battleships;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UIHelper {
	
	//Returns a JButton that only displays the given image.
	public static JButton createCustomButton(String imagePath, int width, int height){	    
		ImageIcon image = resizeImage(imagePath, width, height);
		JButton button = new JButton(image);
		button.setBorder(null);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);

		//Use these to control the other states of the buttons.
		//button.setRolloverIcon(myIcon2);
		//button.setPressedIcon(myIcon3);
		//button.setDisabledIcon(myIcon4);
		
		return button;
	}
	
	//Helper method for resizing a given Image. Used on the background and for buttons.
	public static ImageIcon resizeImage(String imagePath, int width, int height){
		ImageIcon bg = new ImageIcon(imagePath);
		return new ImageIcon(bg.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}	
}
