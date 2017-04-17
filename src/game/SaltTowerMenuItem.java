package game;

import java.awt.Point;

/**
 * 
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class SaltTowerMenuItem extends Effect {

	public SaltTowerMenuItem(GameState game, Point position) {
		super(game, "salt.png", position);
	}

	@Override
	public void update() {

		if(game.getMousePressed() && game.getMousePos().distance(position)<25)
		{
			game.pending.add(new SaltTowerMoving(game, game.getMousePos()));
			game.clearMousePressed();
		}


	}

}
