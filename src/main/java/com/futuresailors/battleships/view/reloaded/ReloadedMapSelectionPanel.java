package com.futuresailors.battleships.view.reloaded;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.futuresailors.battleships.UIHelper;
/**
 * This JPanel displays available maps in reloaded mode
 * @author Joe Baldwin
 */
@SuppressWarnings("serial")
public class ReloadedMapSelectionPanel extends JPanel{

	private final int WIDTH;
	private final int HEIGHT;

	public ReloadedMapSelectionPanel(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createPanel();
	}
	
	public ReloadedMapSelectionPanel(){
		this.WIDTH = UIHelper.getWidth();
		this.HEIGHT = UIHelper.getHeight();
		createPanel();
	}
	
	private void createPanel(){
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		
		//Each button should either be an image of the map or have on above it
		JButton classicMap = new JButton("Classic Map");
	    classicMap.setSize(150, 75);
		classicMap.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.85)));
		add(classicMap);
		
		JButton map1 = new JButton("Map 1");
	    map1.setSize(150, 75);
		map1.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.70)));
		add(map1);
		
		JButton map2 = new JButton("Map 2");
	    map2.setSize(150, 75);
		map2.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.55)));
		add(map2);
		
		JButton map3 = new JButton("Map 3");
	    map3.setSize(150, 75);
		map3.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.40)));
		add(map3);
		
		JButton custom = new JButton("Custom Size");
	    custom.setSize(150, 75);
		custom.setLocation((WIDTH / 2) - 75, (int) (HEIGHT - (HEIGHT * 0.25)));
		add(custom);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    ImageIcon bg = UIHelper.resizeImage("/images/Background1.jpg", WIDTH, HEIGHT);
	    g.drawImage(bg.getImage(), 0, 0, null);
	    
	    //Draw the title.
	}
}
