package game;

import java.awt.Point;

/**
 * This subclass extends the superclass Tower which also 
 * implements an animatable object. Uses set image 
 * that represents the tower when drawn.
 * 
 * Contains a constructor and update helper method. 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class BeerTower extends Effect {
	private Point startingPosition;
	private boolean returningHome, isHome;
	private Enemy lockedTarget;
	private int speed;
	private int returnSpeed;
	/**
	 *  This constructor sets up this type of tower.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param game
	 * @param position
	 */
	public BeerTower(GameState game, Point position) {
		super(game, "beer.png", position);
		this.startingPosition = new Point(position);
		returningHome = false;
		isHome = true;
		speed = 6;
		returnSpeed = 2;
	}

	/**
	 * This method will update everything in relation
	 * to the tower is the game.
	 * 
	 * Allows for adjustments and use of other helper methods.
	 */
	@Override
	public void update() 
	{
		if (startingPosition.distance(getLocation()) < 10 && returningHome) {
			getLocation().x = startingPosition.x;
			getLocation().y = startingPosition.y;
			returningHome = false;
			isHome = true;
		}		
		
		if (lockedTarget == null || lockedTarget.getLocation() == null || isHome) {
			lockedTarget = game.findNearestEnemy(getLocation());
		}			
		
		if (lockedTarget == null) {
			returnHome();
			return;
		}
		
		if(lockedTarget.getLocation().distance(getLocation()) < 200 && !returningHome)
		{
			isHome = false;
			double vX = lockedTarget.getLocation().x - position.x;
			double vY = lockedTarget.getLocation().y - position.y;
			double vectorLength = Math.sqrt(vX*vX + vY*vY);
			vX = vX / vectorLength;
			vY = vY / vectorLength;
			this.getLocation().x += vX*speed;
			this.getLocation().y += vY*speed;
			
			if(lockedTarget.getLocation().distance(getLocation()) < 10)
			{
				if(lockedTarget instanceof EnemySnail)
				{
					game.addAnimatable(new Splat(game, lockedTarget.getLocation()));
					game.adjustCredits(5);
				}else if(lockedTarget instanceof EnemySCargo){
					game.addAnimatable(new Crash(game, lockedTarget.getLocation()));
					game.adjustCredits(10);
				}
				
				game.removeAnimatable(lockedTarget);
				returnHome();
			}
		} else {
			returnHome();
		}
	}
	
	private void returnHome() {
		if(isHome) {
			return;
		}
		returningHome = true;
		double vX = startingPosition.x - getLocation().x;
		double vY = startingPosition.y - getLocation().y;
		double vectorLength = Math.sqrt(vX*vX + vY*vY);
		vX = vX / vectorLength;
		vY = vY / vectorLength;
		getLocation().x += vX*returnSpeed;
		getLocation().y += vY*returnSpeed;
	}
}
