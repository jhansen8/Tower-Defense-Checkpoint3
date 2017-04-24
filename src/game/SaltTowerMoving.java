package game;

import java.awt.Point;
import java.util.List;

/**
 * This subclass extends the superclass Effect which also 
 * implements an Animatable object. Uses set image 
 * that represents the effect when drawn. This class 
 * is used to make a salt tower moving object.
 * It will follow the mouse and place a tower object.
 * 
 * Contains a constructor and update helper method. 
 *   
 * @author JaredHansen
 * @version April 16, 2017
 */
public class SaltTowerMoving extends Effect {

	private List<Point> pathPoints;
	
	/**
	 * This constructor sets up this type of effect.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param game
	 * @param position
	 */
	public SaltTowerMoving (GameState game, Point position) {
		super(game, "salt.png", position);
		pathPoints = ResourceLoader.getLoader().getPath("path_2.txt").getPathPoints();
	}

	/**
	 * This method will update everything in relation
	 * to the tower is the game.
	 * 
	 * It will create a salt tower in the position
	 * that mouse is clicked.
	 * 
	 * Allows for adjustments and use of other helper methods.
	 */
	@Override
	public void update() {
		position = game.getMousePos();
		
		boolean canPlace = true;
		for(Point p : pathPoints) { //go through active list and update
			if (p.getLocation().distance(position) < 30) {
				canPlace = false;
				break;
			}
		}
		
		if(position.x < 600 && game.getPendingButtonAction() 
							&& game.getCredits() >= 100 && canPlace) {
			
			//Add new salt tower to this position
			game.addAnimatable(new SaltTower(game, position));
			
			// Remove Moving Salt Tower
			game.removeAnimatable(this);
			game.clearPendingButtonAction();
			
			game.adjustCredits(-100); //adjust the credits when tower is placed
		}else if(game.getPendingButtonAction() && game.getCredits() < 100){
			// Remove Moving Salt Tower if not enough credits
			game.removeAnimatable(this);
			game.clearPendingButtonAction();
		}
	}

}
