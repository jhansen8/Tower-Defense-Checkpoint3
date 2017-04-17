package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class GameState {

	// Current mode (game over, playing, etc.)
	boolean isOver, isPlaying;
	
    // Score, money, etc.
	int credit, lives;
	
	// List of enemies, towers, etc.
	List<Animatable> active;
	List<Animatable> expired;
	List<Animatable> pending;
	
	// Mouse location / status
	Point mouseLoc;
	int mouseX, mouseY;
	boolean buttonPressed;
	
	Random rand;
    
    /**
     * Constructor for GameState object
     * 	Sets up the game state to prepare necessary details
     * 	Allows for update and draw.
     * @param 
     */
	public GameState()
	{       
		credit = 200;
		lives = 15;
		isOver = false;
		isPlaying = true;
		active = new ArrayList<Animatable>();
		expired = new ArrayList<Animatable>();
		pending = new ArrayList<Animatable>();
		mouseX = 0;
		mouseY = 0;
		buttonPressed = false;
		rand = new Random();
		active.add(new EnemySnail("path_2.txt", this));
		active.add(new SaltTowerMenuItem(this, new Point(650, 200)));
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
		
		if (rand.nextInt(5000) > 4950) {
			active.add(new EnemySnail("path_2.txt", this));
		}
		if (rand.nextInt(10000) > 9980) {
			active.add(new EnemySCargo("path_2.txt", this));
		}
		
		active.removeAll(expired);
		expired.clear();
		active.addAll(pending);
		pending.clear();
		
		clearMousePressed();
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
        g.fillRect(600, 0, 200, 600);
        g.drawImage(ResourceLoader.getLoader().getImage("path_2.jpg"),  0, 0, null); 
        
        g.setColor(Color.BLACK);
        g.drawString("Credit: " + Integer.toString(credit), 650, 100);
        g.drawString("Lives: " + Integer.toString(lives), 650, 120);
        
        for(Animatable enemy : active) {
			enemy.draw(g);
		}        
	}
	
	public void removeAnimatable(Animatable e)
	{
		this.expired.add(e);
	}
	
	
	public void  addAnimatable (Animatable n)  // Adds the object to our active list
	{
		this.pending.add(n);
	}
	
	
	public void  adjustCredits (int amount)    // Adjusts the credits by the given amount
	{
		credit += amount;
		if(credit < 0)
			credit = 0;
	}
	
	
	public void  adjustLives (int amount)    // Adjusts lives by the given amount
	{
		lives += amount;
		if(lives < 0)
			lives = 0;
	}
	
	
	public void  setMousePos (Point p)       // Records the current mouse position
	{
		mouseX = p.x;
		mouseY = p.y;
		mouseLoc = p;
	}
	
	
	public Point getMousePos ()              // Returns the current mouse position
	{
		return mouseLoc;
	}
	
	
	public void  setMousePressed ()              // Sets a boolean flag to true (to indicate a mouse press)
	{
		buttonPressed = true;
	}
	
	
	public void  clearMousePressed ()             // Clears the mouse press boolean flag
	{
		buttonPressed = false;
	}
	
	
	public boolean getMousePressed ()             // Returns the value of the mouse press flag
	{
		return buttonPressed;
	}
	
	
	public Enemy findNearestEnemy (Point p)       // Finds the active enemy nearest to point p, returns it
	                                              //   (or null if none).
	{
		Enemy closest = null;
		for(Animatable a: active)
			if(a instanceof Enemy)
			{
				Enemy e = (Enemy) a;
				if (e.getLocation() == null)
					continue;
				if(closest == null)
					closest = e;
				else if(e.getLocation().distance(p) <
						closest.getLocation().distance(p))
					closest = e;		
			}
		return closest;	
		
	}
}
