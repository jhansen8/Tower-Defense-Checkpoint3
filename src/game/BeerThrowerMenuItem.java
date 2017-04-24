package game;

import java.awt.Point;

public class BeerThrowerMenuItem extends Effect {

	public BeerThrowerMenuItem(GameState game, Point position) {
		super(game, "beer.png", position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		if(game.getPendingButtonAction() && game.getMousePos().distance(position)<25)
		{
			game.addAnimatable(new BeerThrowerMoving(game, game.getMousePos()));
			game.clearPendingButtonAction();
		}


	}

}
