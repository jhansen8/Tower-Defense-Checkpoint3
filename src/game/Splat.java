package game;

import java.awt.Point;

/**
 * This class represents a splat effect that will
 * appear for given amount of time.
 * 
 * Has given image that draws at given position
 * 
 * @author jared
 * @April 23, 2017
 */
public class Splat extends Effect {

	/**
	 * This constructor builds a splat effect using super
	 * Will be made with given image and position, placed 
	 * inside game.
	 * 
	 * @param game
	 * @param position
	 */
	public Splat(GameState game, Point position) {
		super(game, "splat.png", position);
	}

	/**
	 * This update will remove object after given frames
	 */
	@Override
	public void update() {
		if(game.getFrameCount() % 20 == 0)
			game.removeAnimatable(this);
	}

}
