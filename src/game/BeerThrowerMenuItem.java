package game;

import java.awt.Point;

/**
 * This subclass extends the superclass Effect which also 
 * implements an Animatable object. Uses set image 
 * that represents the effect when drawn. This class 
 * is used to make a beer thrower menu item. When clicked
 * it will create a beer thrower moving object.
 * 
 * Contains a constructor and update helper method. 
 *  
 * @author JaredHansen
 * @version April 23, 2017
 */
public class BeerThrowerMenuItem extends Effect {

	/**
	 * This constructor sets up this type of effect.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param game
	 * @param position
	 */
	public BeerThrowerMenuItem(GameState game, Point position) {
		super(game, "beer.png", position);
	}

	/**
	 * This method will update everything in relation
	 * to the thrower inS the game.
	 * 
	 * Allows for adjustments and use of other helper methods.
	 */
	@Override
	public void update() {
		if(game.getPendingButtonAction() && game.getMousePos().distance(position)<25)
		{
			//add new Beer Thrower to current mouse position.
			game.addAnimatable(new BeerThrowerMoving(game, game.getMousePos()));
			game.clearPendingButtonAction();
		}


	}

}
