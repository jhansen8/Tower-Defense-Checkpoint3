 package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Enemy implements Animatable
{
	//Fields
	protected Path path;
	protected GameState game;
	protected double percentageTraveled;
	protected BufferedImage image;
	
	protected Enemy (String path, GameState game, String imageName)
	{
		this.path = ResourceLoader.getLoader().getPath(path);
		this.game = game;
		percentageTraveled = 0;
		this.image = ResourceLoader.getLoader().getImage(imageName);
	}
	
	@Override
	public void draw(Graphics2D g) {
	       Point imagePos = new Point(); //create new point object for circle
	       imagePos = path.getPathPosition(percentageTraveled); //set position based off percentage
	       
	       g.setColor(Color.RED);
	       g.drawImage(image, imagePos.x-(image.getWidth()/2), 
	    		   		imagePos.y-(image.getHeight()/2), null); //circle positioned at center

	}
	
	public Point getLocation()
	{
	       Point imagePos = new Point(); //create new point object for circle
	       imagePos = path.getPathPosition(percentageTraveled); //set position based off percentage
	       
	       return imagePos;
	}
}
