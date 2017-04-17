package game;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * 
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
