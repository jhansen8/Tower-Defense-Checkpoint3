package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
//import java.awt.Point;
//import java.awt.image.BufferedImage;

public class GameState {

	// Current mode (game over, playing, etc.)
	boolean isOver, isPlaying;
	
    // Score, money, etc.
	int credit, lives;
	
	// List of enemies, towers, etc.
	List<Animatable> active;
	List<Animatable> expired;
	Tower currentTower;
	
	// Mouse location / status
	Point mouseLoc;
	int mouseX, mouseY;
	boolean buttonPressed;
    
    /**
     * Constructor for GameState object
     * 	Sets up the game state to prepare necessary details
     * 	Allows for update and draw.
     * @param 
     */
	public GameState()
	{       
		credit = 100;
		lives = 10;
		isOver = false;
		isPlaying = true;
		active = new ArrayList<Animatable>();
		expired = new ArrayList<Animatable>();
		mouseX = 0;
		mouseY = 0;
		buttonPressed = false;
		currentTower = null;
		active.add(new EnemySnail("path_2.txt", this));
	}
	
	/**
	 * This updates the action being performed for animation.
	 * 
	 */
	public void update ()
	{
		for(Animatable enemy : active) {
			enemy.update();
		}
	}
	
	/**
	 * Draws the image, path, and the animating images.
     * 
     * (The background is not cleared, it is assumed the backdrop
     * fills the panel.)
     * 
	 * @param g
	 */
	public void draw (Graphics2D g)
	{
        // Draw the background.
        g.setColor(Color.WHITE);
        //g.fillRect(600, 0, 800, 600);
        g.drawImage(ResourceLoader.getLoader().getImage("menu.jpg"),  600, 0, null);        
        g.drawImage(ResourceLoader.getLoader().getImage("path_2.jpg"),  0, 0, null); 
        
        for(Animatable enemy : active) {
			enemy.draw(g);
		}
        
	}
}
