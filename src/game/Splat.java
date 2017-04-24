package game;

import java.awt.Point;

public class Splat extends Effect {

	public Splat(GameState game, Point position) {
		super(game, "splat.png", position);
	}

	@Override
	public void update() {
		if(game.getFrameCount() % 60 == 0)
			game.removeAnimatable(this);
	}

}
