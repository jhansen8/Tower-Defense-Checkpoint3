package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * This superclass represents Effects as an abstract. It is
 * a set up for any effects that might be used throughout the game.
 * 
 * The constructor ask for a game, image name, and current position.
 * Includes a draw and get location method.
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public abstract class Effect implements Animatable 
{
	//Fields
	protected Point position;
	protected GameState game;
	protected BufferedImage image;
	protected int frameCounter;
	
	/**
	 * This constructor sets up an Effect object.  Places an image
	 * in a specific position within the game. 
	 * 
	 * @param game
	 * @param imageName
	 * @param position
	 */
	protected Effect (GameState game, String imageName, Point position)
	{
		this.game = game;
		this.image = ResourceLoader.getLoader().getImage(imageName);
		this.position = position;
	}
	
	/**
	 * This version of the draw method will draw any image
	 * based on position input.
	 * 
	 * @param Graphics2D g
	 */
	@Override
	public void draw(Graphics2D g) 
	{	       
	       g.drawImage(image, position.x-(image.getWidth()/2), 
	    		   		position.y-(image.getHeight()/2), null); //image positioned at center
	}
	
	/**
	 * This will return the given position as a point.
	 * 
	 * @return Point position
	 */
	@Override
	public Point getLocation()
	{
	      return position;
	}
}
