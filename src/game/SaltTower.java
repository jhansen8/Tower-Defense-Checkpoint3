package game;

import java.awt.Point;

/**
 * This subclass extends the superclass Tower which also 
 * implements an animatable object. Uses set image 
 * that represents the tower when drawn.
 * 
 * Contains a constructor and update helper method. 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class SaltTower extends Tower {

	/**
	 *  This constructor sets up this type of tower.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param game
	 * @param position
	 */
	public SaltTower(GameState game, Point position) {
		super(game, "salt.png", position);
	}

	/**
	 * This method will update everything in relation
	 * to the tower is the game.
	 * 
	 * Allows for adjustments and use of other helper methods.
	 */
	@Override
	public void update() 
	{
		Point loc = getLocationCopy();
		Enemy c = game.findNearestEnemy(loc);
		if (c == null)
			return;
		
		if(c.getLocation().distance(loc) < 150 && game.getFrameCount() % 30 == 0)
		{
			FlyingSalt s = new FlyingSalt(game, loc, 
									c.getLocation().x - position.x,
									c.getLocation().y - position.y);
			game.addAnimatable(s);
		}
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}
