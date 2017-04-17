package game;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * This creates an interface that is used for any object 
 * that can be animated throughout the game.
 * 
 * provides update, draw, and getLocation methods
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public interface Animatable {

	public void update ();
	public void draw (Graphics2D g);
	public Point getLocation();
}
