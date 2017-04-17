package game;

import java.awt.Point;

public class SaltTower extends Tower {

	public SaltTower(GameState game, Point position) {
		super(game, "salt.png", position);
	}

	@Override
	public void update() {
		game.findNearestEnemy(getLocation());
	}

}
