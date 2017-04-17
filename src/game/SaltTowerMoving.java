package game;

import java.awt.Point;

/**
 * 
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class SaltTowerMoving extends Effect {

	public SaltTowerMoving (GameState game, Point position) {
		super(game, "salt.png", position);
	}

	@Override
	public void update() {
		position = game.getMousePos();
		if(game.getMousePressed() && game.credit >= 25) {
			//Add new salt tower to this position
			game.addAnimatable(new SaltTower(game, game.getMousePos()));
			// Remove Moving Salt Tower
			game.removeAnimatable(this);
			game.clearMousePressed();
			
			game.adjustCredits(-25);
		}else if(game.getMousePressed() && game.credit < 25){
			// Remove Moving Salt Tower
			game.removeAnimatable(this);
			game.clearMousePressed();
		}
	}

}
