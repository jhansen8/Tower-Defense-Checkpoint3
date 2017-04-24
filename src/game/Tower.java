package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * This superclass represents Towers as an abstract. It is
 * a set up for any towers that might be used throughout the game.
 * 
 * The constructor ask for a game, image name, and position being used.
 * Includes a draw and get location method.
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public abstract class Tower implements Animatable
{
	//Fields
	protected Point position;
	protected GameState game;
	protected BufferedImage image;
	protected int frameCounter;
	
	/**
	 * This constructor sets up an tower object. Will 
	 * place the tower on a given position.  The tower will
	 * used an image based on imageName.
	 * 
	 * @param game
	 * @param imageName
	 * @param position
	 */
	protected Tower (GameState game, String imageName, Point position)
	{
		this.game = game;
		this.image = ResourceLoader.getLoader().getImage(imageName);
		this.position = position;
	}
	
	/**
	 * This method will draw the tower based on 
	 * given position. The image will be centered 
	 * based on position.
	 */
	@Override
	public void draw(Graphics2D g) 
	{	       
	       g.drawImage(image, position.x-(image.getWidth()/2), 
	    		   		position.y-(image.getHeight()/2), null); //circle positioned at center
	}
	
	/**
	 * This method will return the placed position of the tower.
	 */
	public Point getLocationCopy()
	{
	      return new Point(position);
	}
}
