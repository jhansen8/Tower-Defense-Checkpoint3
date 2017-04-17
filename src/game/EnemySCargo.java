package game;

/**
 * This subclass extends the superclass enemy which also 
 * implements an animatable object. Uses set image 
 * that represents the enemy when drawn. This class is used to 
 * make and SCargo enemy.
 * 
 * Contains a constructor and update helper method. 
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class EnemySCargo extends Enemy 
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
	public EnemySCargo (String path, GameState game)
	{
		super (path, game, "s-cargo.png");
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
        // Advance the circle 0.2% (one thousandth the distance)
        //   along the path, and redraw the screen.
        
    	percentageTraveled += 0.002; //advance each time event is called
    	
    	if (percentageTraveled > 1.0) //reset when 100% is reached
    	{
    		game.removeAnimatable(this);
    		game.adjustLives(-2);
    	}

	}


}
