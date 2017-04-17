package game;

import java.awt.Color;
import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.image.BufferedImage;

public class GameState {

	// Current mode (game over, playing, etc.)
	
    // Score, money, etc.

	// List of enemies, towers, etc.
	
	    //private double percentTraveled; //to track the percentage across path
	    private Animatable enemy;
	// Mouse location / status
        
    
    /**
     * Constructor for GameState object
     * 	Sets up the game state to prepare necessary details
     * 	Allows for update and draw.
     * @param 
     */
	public GameState()
	{       
        // Assume the circle has traveled 0% along the path.
        
        //percentTraveled = 0.0;
        enemy = new EnemySnail("path_2.txt", this);
	}
	
	/**
	 * This updates the action being performed for animation.
	 * 
	 */
	public void update ()
	{
        // Advance the circle 0.1% (one thousandth the distance)
        //   along the path, and redraw the screen.
        enemy.update();
//    	percentTraveled += 0.001; //advance each time event is called
//    	
//    	if (percentTraveled > 1.0) //reset when 100% is reached
//    	{
//    		percentTraveled = 0;
//    	}
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
        
        enemy.draw(g);
//        // Have the path object draw itself.
//        Path path = ResourceLoader.getLoader().getPath("path_2.txt");
//       //path.draw(g);
//        
//        // Draw the circle, centered on its location.  (Must get it's location first.)
//      
//       Point imagePos = new Point(); //create new point object for circle
//       imagePos = path.getPathPosition(percentTraveled); //set position based off percentage
//       
//       BufferedImage image = ResourceLoader.getLoader().getImage("snail.png");
//
//       g.setColor(Color.RED);
//       g.drawImage(image, imagePos.x-(image.getWidth()/2), 
//    		   		imagePos.y-(image.getHeight()/2), null); //circle positioned at center
	}
}
