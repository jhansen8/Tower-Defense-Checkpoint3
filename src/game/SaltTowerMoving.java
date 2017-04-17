package game;

import java.awt.Point;

public class SaltTowerMoving extends Effect {

	public SaltTowerMoving (GameState game, Point position) {
		super(game, "salt.png", position);
	}

	@Override
	public void update() {
		position = game.getMousePos();
		if(game.getMousePressed()) {
			//Add new salt tower to this position
			game.addAnimatable(new SaltTower(game, game.getMousePos()));
			// Remove Moving Salt Tower
			game.removeAnimatable(this);
			game.clearMousePressed();
		}
	}

}
