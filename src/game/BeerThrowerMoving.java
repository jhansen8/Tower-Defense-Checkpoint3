package game;

import java.awt.Point;
import java.util.List;

public class BeerThrowerMoving extends Effect {

	private List<Point> pathPoints;
	
	public BeerThrowerMoving(GameState game, Point position) {
		super(game, "beer.png", position);
		pathPoints = ResourceLoader.getLoader().getPath("path_2.txt").getPathPoints();
	}

	@Override
	public void update() {
		position = game.getMousePos();
		
		boolean canPlace = true;
		for(Point p : pathPoints) { //go through point list check distance for placement
			if (p.getLocation().distance(position) < 30) {
				canPlace = false;
				break;
			}
		}
		
		if(position.x < 600 && game.getPendingButtonAction() 
							&& game.getCredits() >= 1000 && canPlace) {
			
			//Add new salt tower to this position
			game.addAnimatable(new BeerThrower(game, position));
			
			// Remove Moving Salt Tower
			game.removeAnimatable(this);
			game.clearPendingButtonAction();
			
			game.adjustCredits(-1000); //adjust the credits when tower is placed
		}else if(game.getPendingButtonAction() && game.getCredits() < 1000){
			// Remove Moving Beer Tower if not enough credits
			game.removeAnimatable(this);
			game.clearPendingButtonAction();
		}

	}

}
