package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
	private int credit, lives, frameCounter, snailRandom, sCargoRandom, level;
	
	// List of enemies, towers, etc.
	private List<Animatable> active;
	private List<Animatable> expired;
	private List<Animatable> pending;
	
	// Mouse location / status
	private Point mouseLoc;
	private boolean buttonActionPending;
	
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
		resetGame();
		rand = new Random();
	}
	
	/**
	 * This Method resets game to all default values.
	 */
	public void resetGame()
	{
		credit = 200;
		lives = 10;
		isOver = false;
		isPlaying = false;
		buttonActionPending = false;
		mouseLoc = new Point(0,0);
		frameCounter = 0;
		active = new ArrayList<Animatable>();
		expired = new ArrayList<Animatable>();
		pending = new ArrayList<Animatable>();
		active.add(new SaltTowerMenuItem(this, new Point(650, 200)));
		active.add(new BeerTowerMenuItem(this, new Point(750, 200)));
		snailRandom = 4950;
		sCargoRandom = 9980;
		level = 1;
		
	}
	
	/**
	 * This updates the action being performed for animation.
	 * 
	 */
	public void update ()
	{
		if(isOver || !isPlaying) // Initialize the gameplay 
		{
			if(buttonActionPending)
			{
				resetGame();
				isPlaying = true;
			} else
				return;
		}
		
		for(Animatable a : active) { //go through active list and update
			a.update();
		}
		
		if (rand.nextInt(5000) > snailRandom) { //This will create a new enemy snail at random
			active.add(new EnemySnail("path_2.txt", this)); // add to list
		}
		if (rand.nextInt(10000) > sCargoRandom) {//This will create a new EnemySCargo at random
			active.add(new EnemySCargo("path_2.txt", this)); //add to list
		}
		
		//remove all expired animatables from active list and clear expired
		active.removeAll(expired);
		expired.clear();
		
		//add all pending animatables to active list and clear pending
		active.addAll(pending);
		pending.clear();
		
		//this will reset the buttonPressed during each update.
		clearPendingButtonAction();
		
		if(lives == 0)
			isOver = true;
		
		if(++frameCounter > 1000) {
			frameCounter = 0;
			snailRandom -= 30;
			if(snailRandom < 0)
				snailRandom = 0;
			sCargoRandom -= 50;
			if(sCargoRandom < 0)
				sCargoRandom = 0;
			++level;
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
        g.fillRect(600, 0, 200, 600);
        g.drawImage(ResourceLoader.getLoader().getImage("path_2.jpg"),  0, 0, null); 
        
        // display lives and credits on menu.
        g.setColor(Color.BLACK);
        g.drawString("100 Credits", 615, 250);
        g.drawString("1000 Credits", 715, 250);
        
        g.setFont(new Font("arial", 20 , 20));
        g.drawString("TOWER DEFENSE", 615, 50);
        g.drawString("Credit: " + Integer.toString(credit), 620, 100);
        g.drawString("Lives: " + Integer.toString(lives), 620, 130);
        g.drawString("Level: " + Integer.toString(level), 665, 400);
        
        // Draw enemy objects
        for(Animatable a : active) {
			a.draw(g);
		}        
        
        if(!isPlaying || isOver)
        {
        	g.setColor(Color.BLACK);
        	g.drawString("Click to play.", 645, 500);
        }
        
        if(isOver)
        {
        	g.drawImage(ResourceLoader.getLoader().getImage("game_over.png"), 0, 0, null);
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
	public void  setPendingButtonAction ()              // Sets a boolean flag to true (to indicate a mouse press)
	{
		buttonActionPending = true;
	}
	
	/**
	 * This helper method will reset buttonPressed to false
	 * when called.
	 */
	public void  clearPendingButtonAction ()             // Clears the mouse press boolean flag
	{
		buttonActionPending = false;
	}
	
	/**
	 * This helper method returns the buttonPressed as boolean
	 * @return
	 */
	public boolean getPendingButtonAction ()             // Returns the value of the mouse press flag
	{
		return buttonActionPending;
	}
	
	/**
	 * This helper method will help to find the closest 
	 * enemy to a specific point in the game. 
	 * 
	 * When a new enemy is closer, it replaces the older enemy 
	 * as closest enemy. 
	 * 
	 * @param Point p
	 * @return nearest enemy or Null
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
	
	/**
	 * This method returns the frame count.
	 * 
	 * @return int frame Count
	 */
	public int getFrameCount()
	{
		return frameCounter;
	}
}
