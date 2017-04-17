package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a GameState object. It is
 * where the game is actually created in order to 
 * work with GUI. 
 * 
 * It sets up game variables, constructors, and is
 * in charge of running the game
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class GameState {

	// Current mode (game over, playing, etc.)
	private boolean isOver, isPlaying;
	
    // Score, money, etc.
	private int credit, lives;
	
	// List of enemies, towers, etc.
	List<Animatable> active;
	List<Animatable> expired;
	List<Animatable> pending;
	
	// Mouse location / status
	private Point mouseLoc;
	private int mouseX, mouseY;
	private boolean buttonPressed;
	
	Random rand;
    
    /**
     * Constructor for GameState object
     * 	Sets up the game state to prepare necessary details
     * 	Allows for update and draw.
     * 
     * Creates new objects and lists and sets default values to variables
     * 
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
		
		for(Animatable enemy : active) { //go through active list and update
			enemy.update();
		}
		
		if (rand.nextInt(5000) > 4950) { //This will create a new enemy snail at random
			active.add(new EnemySnail("path_2.txt", this)); // add to list
		}
		if (rand.nextInt(10000) > 9980) {//This will create a new EnemySCargo at random
			active.add(new EnemySCargo("path_2.txt", this)); //add to list
		}
		
		//remove all expired animatables from active list and clear expired
		active.removeAll(expired);
		expired.clear();
		
		//add all pending animatables to active list and clear pending
		active.addAll(pending);
		pending.clear();
		
		//this will reset the buttonPressed during each update.
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
        
        // display lives and credits on menu.
        g.setColor(Color.BLACK);
        g.drawString("Credit: " + Integer.toString(credit), 650, 100);
        g.drawString("Lives: " + Integer.toString(lives), 650, 120);
        
        // Draw enemy objects
        for(Animatable enemy : active) {
			enemy.draw(g);
		}        
	}
	
	//helper methods
	
	/**
	 * This helper method adds Animatable to expired list
	 * to be removed from active.
	 * 
	 * @param e
	 */
	public void removeAnimatable(Animatable e)
	{
		this.expired.add(e);
	}
	
	/**
	 * This helpler method adds animatable to pending list
	 * to be added to actives.
	 * 
	 * @param n
	 */
	public void  addAnimatable (Animatable n)  // Adds the object to our active list
	{
		this.pending.add(n);
	}
	
	/**
	 * This helper method allows for the credits to be adjusted
	 * based on specified amount. Always keeps credits above 0.
	 * 
	 * @param amount
	 */
	public void  adjustCredits (int amount)    // Adjusts the credits by the given amount
	{
		credit += amount;
		if(credit < 0)
			credit = 0;
	}
	
	/**
	 * This helper method allows for the lives to be adjusted
	 * based on specific amount. Always keeps lives above 0.
	 * 
	 * @param amount
	 */
	public void  adjustLives (int amount)    // Adjusts lives by the given amount
	{
		lives += amount;
		if(lives < 0)
			lives = 0;
	}
	
	/**
	 * This helper method sets the mouse Location to variables
	 * that can be used. Int for X and Y, Point for point.
	 * 
	 * @param Point p
	 */
	public void  setMousePos (Point p)       // Records the current mouse position
	{
		mouseX = p.x;
		mouseY = p.y;
		mouseLoc = p;
	}
	
	/**
	 * This helper method returns the mouse location
	 * as a point.
	 * 
	 * @return
	 */
	public Point getMousePos ()              // Returns the current mouse position
	{
		return mouseLoc;
	}
	
	/**
	 * This helper method will set buttonPressed to true 
	 * when called.
	 */
	public void  setMousePressed ()              // Sets a boolean flag to true (to indicate a mouse press)
	{
		buttonPressed = true;
	}
	
	/**
	 * This helper method will reset buttonPressed to false
	 * when called.
	 */
	public void  clearMousePressed ()             // Clears the mouse press boolean flag
	{
		buttonPressed = false;
	}
	
	/**
	 * This helper method returns the buttonPressed as boolean
	 * @return
	 */
	public boolean getMousePressed ()             // Returns the value of the mouse press flag
	{
		return buttonPressed;
	}
	
	/**
	 * This helper method will help to find the closest 
	 * enemy to a specific point in the game. 
	 * 
	 * When a new enemy is closer, it replaces the older enemy 
	 * as closest enemy. 
	 * 
	 * @param p
	 * @return
	 */
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
	
	/**
	 * This helper method allows to get lives.
	 * 
	 * @return
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * This helper method allows to get credits.
	 * 
	 * @return
	 */
	public int getCredits()
	{
		return credit;
	}
}
