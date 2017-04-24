package game;

import java.awt.Point;

/**
 * This subclass extends the superclass Effect which also 
 * implements an Animatable object. Uses set image 
 * that represents the effect when drawn. This class 
 * is used to make a salt tower menu item. When clicked
 * it will create a salt tower moving object.
 * 
 * Contains a constructor and update helper method. 
 *  
 * @author JaredHansen
 * @version April 16, 2017
 */
public class SaltTowerMenuItem extends Effect {

	/**
	 * This constructor sets up this type of effect.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param game
	 * @param position
	 */
	public SaltTowerMenuItem(GameState game, Point position) {
		super(game, "salt.png", position);
	}

	/**
	 * This method will update everything in relation
	 * to the tower is the game.
	 * 
	 * Allows for adjustments and use of other helper methods.
	 */
	@Override
	public void update() {

		if(game.getPendingButtonAction() && game.getMousePos().distance(position)<25)
		{
			game.addAnimatable(new SaltTowerMoving(game, game.getMousePos()));
			game.clearPendingButtonAction();
		}


	}

}
