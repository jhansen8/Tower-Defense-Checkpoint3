package game;


public class EnemySCargo extends Enemy 
{

	//Fields
	
	public EnemySCargo (String path, GameState game)
	{
		super (path, game, "s-cargo.png");
	}
	
	@Override
	public void update() 
	{
        // Advance the circle 0.1% (one thousandth the distance)
        //   along the path, and redraw the screen.
        
    	percentageTraveled += 0.003; //advance each time event is called
    	
    	if (percentageTraveled > 1.0) //reset when 100% is reached
    	{
    		percentageTraveled = 0;
    	}

	}


}
