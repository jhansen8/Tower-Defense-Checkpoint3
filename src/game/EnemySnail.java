package game;

/**
 * This subclass extends the superclass enemy which also 
 * implements an Animatable object. Uses set image 
 * that represents the enemy when drawn. This class is
 * used to make a snail
 * 
 * Contains a constructor and update helper method. 
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class EnemySnail extends Enemy 
{

	//Fields
	
	/**
	 * This constructor sets up this type of enemy.
	 * The superclass constructor is used to actually build
	 * the object, contains set image for object.
	 * 
	 * @param path
	 * @param game
	 */
	public EnemySnail (String path, GameState game)
	{
		super (path, game, "snail.png");
	}
	
	/**
	 * This method will update the the object.
	 * Changes position by increasing percentage.
	 * 
	 * Allows for adjustments to other variables using
	 * helper methods from gameState
	 */
	@Override
	public void update() 
	{
        // Advance the circle 0.1% (one thousandth the distance)
        //   along the path, and redraw the screen.
        
    	percentageTraveled += 0.001; //advance each time event is called
    	
    	if (percentageTraveled > 1.0) //reset when 100% is reached
    	{
    		game.removeAnimatable(this);
    		game.adjustLives(-1);
    	}

	}


}
