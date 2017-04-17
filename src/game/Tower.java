package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * 
 * 
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
	
	protected Tower (GameState game, String imageName, Point position)
	{
		this.game = game;
		this.image = ResourceLoader.getLoader().getImage(imageName);
		this.position = position;
	}
	
	@Override
	public void draw(Graphics2D g) 
	{	       
	       g.drawImage(image, position.x-(image.getWidth()/2), 
	    		   		position.y-(image.getHeight()/2), null); //circle positioned at center
	}
	
	@Override
	public Point getLocation()
	{
	      return position;
	}
}
