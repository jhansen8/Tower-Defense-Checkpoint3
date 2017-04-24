package game;

import java.awt.Point;

public class FlyingSalt extends Effect {

	// Fields
	private double vX, vY;
	
	public FlyingSalt(GameState game, Point position, double vX, double vY) 
	{
		super(game, "salt_crystals.png" , position);
		double vectorLength = Math.sqrt(vX*vX + vY*vY);
		this.vX = vX / vectorLength;
		this.vY = vY / vectorLength;
	}

	@Override
	public void update() {
		this.getLocation().x += vX*5;
		this.getLocation().y += vY*5;
		
		if(game.getFrameCount() % 30 == 0)
		{
			game.removeAnimatable(this);
		}
		
		Enemy nearbyEnemy = game.findNearestEnemy(getLocation());
		if(nearbyEnemy == null)
			return;
		if(nearbyEnemy.getLocation().distance(getLocation()) < 30)
		{
			if(nearbyEnemy instanceof EnemySnail)
			{
				game.addAnimatable(new Splat(game, nearbyEnemy.getLocation()));
				game.adjustCredits(5);
			}else if(nearbyEnemy instanceof EnemySCargo){
				game.addAnimatable(new Crash(game, nearbyEnemy.getLocation()));
				game.adjustCredits(10);

			}
			
			game.removeAnimatable(nearbyEnemy);
			game.removeAnimatable(this);
		}

	}

}
