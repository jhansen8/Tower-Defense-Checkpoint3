 package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;


/**
 * This superclass represents Enemies as an abstract. It is
 * a set up for any enemies that might be used throughout the game.
 * 
 * The constructor ask for a game, image name, and current path being used.
 * Includes a draw and get location method.
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public abstract class Enemy implements Animatable
{
	//Fields
	protected Path path;
	protected GameState game;
	protected double percentageTraveled;
	protected BufferedImage image;
	protected int frameCounter;
	
	/**
	 * This constructor sets up an enemy object. Will 
	 * place along given path to follow and sets the image
	 * for enemy object. Places in current game.
	 * 
	 * Current percentage initialized as 0 percent.
	 * 
	 * @param path
	 * @param game
	 * @param imageName
	 */
	protected Enemy (String path, GameState game, String imageName)
	{
		this.path = ResourceLoader.getLoader().getPath(path);
		this.game = game;
		percentageTraveled = 0;
		this.image = ResourceLoader.getLoader().getImage(imageName);
	}
	
	/**
	 * This method draws the enemy object along the path
	 * at given percentage along path. The image will be centered.
	 * 
	 * @param Graphics2D g
	 */
	@Override
	public void draw(Graphics2D g) {
	       Point imagePos = getLocation(); //create new point object for image
	       
	       g.drawImage(image, imagePos.x-(image.getWidth()/2), 
	    		   		imagePos.y-(image.getHeight()/2), null); //image positioned at center
	}
	
	/**
	 * This method keeps track of and allows to get location
	 * in game based on percentage traveled. 
	 * 
	 * Returns the position as a point.
	 * 
	 * @return Point position
	 */
	@Override
	public Point getLocation()
	{
	       Point imagePos = new Point(); //create new point object for circle
	       imagePos = path.getPathPosition(percentageTraveled); //set position based off percentage
	       
	       return imagePos;
	}
}
