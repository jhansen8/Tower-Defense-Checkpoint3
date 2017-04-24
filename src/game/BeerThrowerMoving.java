package game;

import java.awt.Point;
import java.util.List;

/**
 * This subclass extends the superclass Effect which also 
 * implements an Animatable object. Uses set image 
 * that represents the effect when drawn. This class 
 * is used to make a beer thrower moving object.
 * It will follow the mouse and place a tower object.
 * 
 * Contains a constructor and update helper method. 
 *   
 * @author JaredHansen
 * @version April 23, 2017
 */
public class BeerThrowerMoving extends Effect {

	//list of points used to check placement
	private List<Point> pathPoints;
	
	/**
	 * This constructor sets up this type of effect.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param game
	 * @param position
	 */
	public BeerThrowerMoving(GameState game, Point position) {
		super(game, "beer.png", position);
		pathPoints = ResourceLoader.getLoader().getPath("path_2.txt").getPathPoints();
	}

	/**
	 * This method will update everything in relation
	 * to the thrower is the game.
	 * 
	 * It will create a beer thrower in the position
	 * that mouse is clicked, as long as position is not on path.
	 * 
	 * Allows for adjustments and use of other helper methods.
	 */
	@Override
	public void update() {
		position = game.getMousePos();
		
		//This will prevent the Thrower from being placed on path.
		boolean canPlace = true;
		for(Point p : pathPoints) { //go through point list check distance for placement
			if (p.getLocation().distance(position) < 30) {
				canPlace = false;
				break;
			}
		}
		
		// Allows placement outside of menu area, if clicked, and enough credits
		if(position.x < 600 && game.getPendingButtonAction() 
							&& game.getCredits() >= 1000 && canPlace) {
			
			//Add new beer thrower to this position
			game.addAnimatable(new BeerThrower(game, position));
			
			// Remove Moving Beer Thrower
			game.removeAnimatable(this);
			game.clearPendingButtonAction();
			
			game.adjustCredits(-1000); //adjust the credits when thrower is placed
		}
		else if(game.getPendingButtonAction() && game.getCredits() < 1000)
		{
			// Remove Moving Beer Thrower if not enough credits
			game.removeAnimatable(this);
			game.clearPendingButtonAction();
		}

	}

}
