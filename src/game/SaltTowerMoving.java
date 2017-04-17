package game;

import java.awt.Point;

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
		if(game.getMousePressed() && game.getCredits() >= 25) {
			//Add new salt tower to this position
			game.addAnimatable(new SaltTower(game, game.getMousePos()));
			
			// Remove Moving Salt Tower
			game.removeAnimatable(this);
			game.clearMousePressed();
			
			game.adjustCredits(-25); //adjust the credits when tower is placed
		}else if(game.getMousePressed() && game.getCredits() < 25){
			// Remove Moving Salt Tower if not enough credits
			game.removeAnimatable(this);
			game.clearMousePressed();
		}
	}

}
