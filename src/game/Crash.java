package game;

import java.awt.Point;

public class Crash extends Effect {

	public Crash(GameState game, Point position) {
		super(game, "crash.png", position);
	}

	@Override
	public void update() {
		if(game.getFrameCount() % 60 == 0)
			game.removeAnimatable(this);
	}

}
