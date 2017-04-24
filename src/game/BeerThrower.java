package game;

import java.awt.Point;

/**
 * This subclass extends the superclass Effect which also 
 * implements an animatable object. Uses set image 
 * that represents the tower when drawn.
 * 
 * Will act as a type of Tower in the game, but will 
 * not be stationary. I will attack its enemies.
 * 
 * Contains a constructor and update helper method. 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class BeerThrower extends Effect {
	private Point startingPosition;
	private boolean returningHome, isHome;
	private Enemy lockedTarget;
	private int speedVar;
	private int returnSpeedVar;
	/**
	 *  This constructor sets up this type of tower effect.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param game
	 * @param position
	 */
	public BeerThrower(GameState game, Point position) {
		super(game, "beer.png", position);
		this.startingPosition = new Point(position);
		returningHome = false;
		isHome = true;
		speedVar = 6;
		returnSpeedVar = 2;
	}

	/**
	 * This method will update everything in relation
	 * to the tower is the game.
	 * 
	 * Beer Thrower will throw itself at an enemy and track its
	 * location until hit. It will then return to its start position
	 * in between.
	 * 
	 * Allows for adjustments and use of other helper methods.
	 */
	@Override
	public void update() 
	{
		// This will guarantee a return home as to not get stuck trying to reach home
		if (startingPosition.distance(getLocation()) < 10 && returningHome) {
			getLocation().x = startingPosition.x;
			getLocation().y = startingPosition.y;
			returningHome = false;
			isHome = true;
		}		
		
		// This prevents confusion for if the target disappears, will lock onto next target
		if (lockedTarget == null || lockedTarget.getLocation() == null || isHome) {
			lockedTarget = game.findNearestEnemy(getLocation());
		}			
		
		// IF there is no target, beer will return home
		if (lockedTarget == null) {
			returnHome();
			return;
		}
		
		//This will set course to closest target and follow target.
		if(lockedTarget.getLocation().distance(getLocation()) < 100 && !returningHome)
		{
			isHome = false;
			double velX = lockedTarget.getLocation().x - position.x;
			double velY = lockedTarget.getLocation().y - position.y;
			double vectorLength = Math.sqrt(velX*velX + velY*velY);
			velX = velX / vectorLength;
			velY = velY / vectorLength;
			this.getLocation().x += velX*speedVar;
			this.getLocation().y += velY*speedVar;
			
			//if beer gets within given distance, target will be removed.
			if(lockedTarget.getLocation().distance(getLocation()) < 5)
			{
				// Given effect will occur depending on enemy type.
				if(lockedTarget instanceof EnemySnail)
				{
					game.addAnimatable(new Splat(game, lockedTarget.getLocation()));
					game.adjustCredits(5);
				}else if(lockedTarget instanceof EnemySCargo){
					game.addAnimatable(new Crash(game, lockedTarget.getLocation()));
					game.adjustCredits(7);
				}
				
				// Target Removed.
				game.removeAnimatable(lockedTarget);
				
				// Beer will return home.
				returnHome();
			}
		} else {
			//If no enemy, target returns.
			returnHome();
		}
	}
	
	
	/**
	 * This method sends the beer thrower back to its home location.
	 * Speed of return can be adjusted accordingly. 
	 */
	private void returnHome() {
		if(isHome) {
			return;
		}
		
		// let know that beer is returning home.
		returningHome = true;
		
		//This will calculate the Vector lengths needed
		//that will adjust speed and direction of movement.
		double velX = startingPosition.x - getLocation().x;
		double velY = startingPosition.y - getLocation().y;
		double vectorLength = Math.sqrt(velX*velX + velY*velY);
		velX = velX / vectorLength;
		velY = velY / vectorLength;
		
		getLocation().x += velX*returnSpeedVar;
		getLocation().y += velY*returnSpeedVar;
	}
}
