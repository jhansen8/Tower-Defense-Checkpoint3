package game;

import java.awt.Point;

/**
 * This subclass extends the superclass Effect which also 
 * implements an Animatable object. Uses set image 
 * that represents the effect when drawn. This class 
 * is used to make a flying salt object.
 * 
 * It will attack the nearest enemy and last a given
 * amount of time before expiring.
 * 
 * Contains a constructor and update helper method. 
 *   
 * @author JaredHansen
 * @version April 16, 2017
 */
public class FlyingSalt extends Effect {

	// Fields
	private double velX, velY;
	
	/**
	 * This Constructor will set up the flying salt.
	 * Needs gae, position, and vector directions for x and y.
	 * Vector length and velocity will be calculated.
	 * 
	 * @param game
	 * @param position
	 * @param vecX
	 * @param vecY
	 */
	public FlyingSalt(GameState game, Point position, double vecX, double vecY) 
	{
		super(game, "salt_crystals.png" , position);
		double vectorLength = Math.sqrt(vecX*vecX + vecY*vecY);
		this.velX = vecX / vectorLength;
		this.velY = vecY / vectorLength;
	}

	/**
	 * This method will update the flying salt position to move 
	 * towards enemy and remove if reaches target.
	 * 
	 * The speed will be adjusted based on the distance needed to shoot. 
	 * It will shoot every given period of time.
	 * 
	 * Calls on effects when target is removed.
	 */
	@Override
	public void update() {
		//This will animate the effect towards target
		this.getLocation().x += velX*5;
		this.getLocation().y += velY*5;
		
		//expires when given frame count reaches limit.
		if(game.getFrameCount() % 30 == 0)
		{
			game.removeAnimatable(this);
		}
		
		//track nearest enemy to current position.
		Enemy nearbyEnemy = game.findNearestEnemy(getLocation());
		
		//return if no current nearby enemy.
		if(nearbyEnemy == null)
			return;
		
		//Enemy is removed when effect hits enemy. 
		if(nearbyEnemy.getLocation().distance(getLocation()) < 30)
		{
			//If snail then will add effect and adjust credits.
			if(nearbyEnemy instanceof EnemySnail)
			{
				game.addAnimatable(new Splat(game, nearbyEnemy.getLocation()));
				game.adjustCredits(5);
			}
			//If SCargo then will add effect and adjust credits.
			else if(nearbyEnemy instanceof EnemySCargo)
			{
				game.addAnimatable(new Crash(game, nearbyEnemy.getLocation()));
				game.adjustCredits(7);

			}
		
			//Remove all necessary animatables.
			game.removeAnimatable(nearbyEnemy);
			game.removeAnimatable(this);
		}

	}

}
