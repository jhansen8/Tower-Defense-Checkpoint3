package game;

import java.awt.Point;

/**
 * 
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class SaltTower extends Tower {

	public SaltTower(GameState game, Point position) {
		super(game, "salt.png", position);
	}

	@Override
	public void update() {
		game.findNearestEnemy(getLocation());
	}

}
