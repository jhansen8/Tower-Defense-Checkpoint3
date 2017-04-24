package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**This class represents the path that will be used in a tower defense game.
 * It constructs path constructs a path object into an array that will hold points.
 * Draws path, determines length, and location based on a percentage.
 * 
 * 
 * @author Jared Hansen
 * @version March 29, 2017
 */
public class Path {

	//Fields
	
	private List<Point> path;
	
	//Methods
	
    /** This constructor does the following:
     *     - It creates a new array (or ArrayList) to hold the path coordinates,
     *          and stores it in the path variable.
     *     - It reads a number of coordinates, n, from the scanner.
     *     - It loops n times, each time scanning a coordinate x,y pair, creating an
     *         object to represent the coordinate, and storing the coordinate object in the path.
     * 
     * @param s  a Scanner set up by the caller to provide a list of coordinates
     */
	public Path (Scanner s)
	{
		int size = s.nextInt(); //finds array size as first value in text file
		path = new ArrayList<Point>(); //sets list to Array List
		
		//For loop that fills array with points from text file
		for(int i = 0; i < size; i ++)
		{
			int x = s.nextInt(); // scans first value as x
			int y = s.nextInt(); //scans second value as y
			
			Point p = new Point(x,y); //creates new Point
			path.add(p); //store point in list array
		}
		//System.out.print(getPathLength()); //total length - 2561.6437134569373
	}
	
    /**
     * Draws the current path to the screen (to wherever the graphics object points)
     * using a highly-visible color.
     * 
     * @param g   a graphics object
     */ 
	public void draw (Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(60));
		
		//for loop to draw complete path from start to end
		
		for(int i = 0; i < path.size() - 1; i++)
		{
		 	Point start = path.get(i); //starting point
		 	Point end = path.get(i+1); //ending point
		 	
			g.drawLine(start.x, start.y, end.x, end.y);
		}
	}
	
	/** 
	 * Returns the total length of the path. Since the path
	 * is specified using screen coordinates, the length is
	 * in pixel units (by default).
	 * 
	 * @return the length of the path
	 */
	 public double getPathLength ()
	 {
		 double totalLength = 0; //keeps track of the total length of all segments
		 
		 //loop calculates each segment individually and adds to total
		 
		 for(int i = 0; i < path.size()-1; i++)
			{
			 	Point start = path.get(i); //starting point
			 	Point end = path.get(i+1); //ending point
			 
			 	totalLength += start.distance(end); //calculation and storing of length
			}
		 
		 return totalLength; 
	 }
	 
	 /** 
	  * Given a percentage between 0% and 100%, this method calculates
	  * the location along the path that is exactly this percentage
	  * along the path. The location is returned in a Point object
	  * (int x and y), and the location is a screen coordinate.
	  * 
	  * If the percentage is less than 0%, the starting position is
	  * returned. If the percentage is greater than 100%, the final
	  * position is returned.
	  * 
	  * If students don't want to use Point objects, they may 
	  * write or use another object to represent coordinates. 
	  *
	  * Caution: Students should never directly return a Point object
	  * from a path list. It could be changed by the outside caller.
	  * Instead, always create and return new point objects as
	  * the result from this method.
	  * 
	  * @param percentTraveled a distance along the path
	  * @return the screen coordinate of this position along the path
	  */
	  public Point getPathPosition (double percentTraveled)
	  {
		  // Debugging only:  In my solution, I can have empty paths.  This         
		  //   statement deals with this.  (This was not shown during lecture.)         
		  //   Since we cannot look through non-existant path segments, exit early.                 
		  if (path.size() == 0)  // empty path - return 0,0             
			  return new Point(0, 0);
		  
		  Point position = null; //Point variable to store position
		  
		  double distanceToTravel = getPathLength() * percentTraveled; //total distance along path to be traveled based on percentage
		  
		  //loop to calculate the percentage and position
		  //along individual segments and return a point
		  
		  for(int i = 0; i < path.size()-1; i++)
		  {  
			  Point start = path.get(i); //starting point
			  Point end = path.get(i+1); //ending point
			  
			  int x1, y1, x2, y2; //create x and y variables for start and end
			  x1 = start.x; //x start position
			  y1 = start.y; //y start position
			  x2 = end.x; //x end position
			  y2 = end.y; //y end position
			  
			  double x = 0, y = 0; //variables for final x and y coordinates
			  
			  double distanceBetweenPoints = start.distance(end); //find distance of each segment
			  
			  if (distanceToTravel <= distanceBetweenPoints) //run if distance to be traveled is less than segment
			  {
				 //calculate percent along segment
				 double percentAlongSegment = distanceToTravel / distanceBetweenPoints; 
				 
				 //set segment percent to 100% if total percent is 100%
				 if (percentTraveled >= 1.0)
				 {
					 percentAlongSegment = 1.0;
				 }
				 //set segment percent to 0% is total is 0%
				 if (percentTraveled <= 0.0)
				 {
					 percentAlongSegment = 0.0;
				 }
				 
				 double dx = x2 - x1; //calculate delta x
				 double dy = y2 - y1; //calculate delta y
				 
				 //use delta x, delta y, starting point, and segment percent to find new point
				 x = x1 + dx * percentAlongSegment; 
				 y = y1 + dy * percentAlongSegment;
				 
				 position = new Point((int)x, (int)y); //store coordinates into position
				 break; //terminate loop
			  }
			  
			  //use this to find which segment has been traveled to
			  if (distanceToTravel > distanceBetweenPoints)
			  {
				  //if the distance to travel is larger than segment,
				  //take off segment distance from total to travel 
				  //to test next segment
				  distanceToTravel -= distanceBetweenPoints;
			  } 			  
		  }
		  
		return position; //return the position at traveled percentage
	  }
	  
	    /**
	     * This constructor builds an empty path.  It is intended for
	     * use in development stages of the project.  (The user can then 'add'
	     * additional points to the path, or print out the path coordinates.)
	     *
	     * This constructor should probably not be used during the game, but
	     * it could be if you wanted to create dynamic paths.
	     */
	  public Path ()
	  { 
		  // Just create an empty list of points.
		  //   Even though the path is empty, the List object is still
		  //   needed.  It will have 0 points in it.
		  path = new ArrayList<Point>();  // ArrayList objects are List objects.
	  }
	  
	  /**
	   * Adds a point to the end of the path.
	   * 
	   * @param p a point to add
	   */
	  public void addPathPoint (Point p)
	  {
		  path.add(p);
		  printPath();
	  }

	  /**
	   * Removes the last point in this path, if any.
	   */
	  public void removeLastPathPoint ()
	  {
		  // Make sure there is at least one point, we cannot remove an entry
		  //   from an empty list.
		  if (path.size() > 0)
		  {
			  path.remove(path.size()-1);  // Remove the point at the specified position.             
			  printPath();
		  }
	  }
	  
	    /**      
	     * Prints the path to the console for debugging.      
	     * This function makes it easier to create path text files.      
	     * Simply copy the last numeric output into a file.      
	     */     
	  public void printPath ()     
	  {
	        System.out.println ();
	        System.out.println (path.size());
	        for (Point p : path)
	        	System.out.printf ("%4d  %4d\n", p.x, p.y);
	  }
	  
	  /**
	   * 
	   * @return
	   */
	  public List<Point> getPathPoints()
	  {
		  return path;
	  }
	  
}
