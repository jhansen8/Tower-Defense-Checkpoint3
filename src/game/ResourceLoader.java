package game;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * 
 * 
 * @author JaredHansen
 * @version April 16, 2017
 */
public class ResourceLoader {

	private Map<String, BufferedImage> imageMap;
	private Map<String, Path> pathMap;
	static private ResourceLoader singleInstance;
	
	/**
	 * This class represents a central point for accessing 
	 * images and text files.  Functions exist for loading  
	 * images and Path objects, and no image or path will ever  
	 * be loaded from a file twice.  (When an image or path  
	 * is loaded, it is saved in a map so that if it is  
	 * needed later, it can be easily and quickly returned.)  
	 *  
	 * A ResourceLoader object is needed to call the functions  
	 * that load images or Path objects.  A static method exists  
	 * for getting a ResourceLoader object.  (You cannot create  
	 * one directly from outside of the class.)  
	 *  
	 * Only one ResourceLoader object will ever be created during  
	 * any application.  (This is called a singleton.)
	 * 
	 * @return
	 */
	static public ResourceLoader getLoader()
    {
        if (singleInstance == null)
          singleInstance = new ResourceLoader ();
          
        return singleInstance;
    }
	
	/** Constructor for ResourceLoader
	 * 	This will store images and paths 
	 * 	that have already been loaded
	 */
	private ResourceLoader(){
		// Initialize Maps to hold resources.
		
		imageMap = new HashMap<String, BufferedImage>();
		pathMap = new HashMap<String, Path>();
	}
	
	/**
	 * This method will load a path if previously stored
	 * Else will store new path if not already loaded.
	 * 
	 * @param pathName
	 * @return
	 */
	public Path getPath(String pathName){
		
		// Checks to see if path has been previously loaded.
		if(pathMap.containsKey(pathName))
			return pathMap.get(pathName);
		
		try{
	        // InputStream objects are an alternative to File objects.
	        //   I use them to make it easier to locate resources - resources
	        //   can be in a directory, .jar, on the web, etc..  
	        //
	        // In the code below, I figure out
	        //   where my class was loaded from, and get the 'resource' from the 
	        //   adjoining resource directory.  Java will return an 'InputStream'
	        //   that you can use to read or scan through the resource.
	        
	        // Get the object that loaded this class, because it keeps track
	        //   of -where- things are loaded from for us.  (A very advanced
	        //   technique, please use this code.)
			
	   		ClassLoader myLoader = this.getClass().getClassLoader();	
	        InputStream pointStream = myLoader.getResourceAsStream("resources/" + pathName);
	        Scanner in = new Scanner (pointStream);  // Scan from the text file.
	        
	        Path path;
	        path = new Path(in);
	        
	        pathMap.put(pathName, path); //store path in path map
	        
	        in.close();
	        
	        return path;
		}
		catch (Exception e)
		{
            // On error, just print a message and exit.  
            //   (You should make sure the files are in the correct place.)
            
            System.err.println ("Could not load one of the files: " + pathName);
            System.exit(0);  // Bail out.
            return null;
		}
		

	}
	
	/** This method loads an image based on name and stores it.
	 * Checks to see if image has been loaded before 
	 * if loaded before, image is retrieved and returned.
	 * 
	 * @param imageName
	 */
	public BufferedImage getImage(String imageName){
		
		// Check to see if image has been previously loaded.
		if(imageMap.containsKey(imageName))
			return imageMap.get(imageName);
		
  
        try{
            // InputStream objects are an alternative to File objects.
            //   I use them to make it easier to locate resources - resources
            //   can be in a directory, .jar, on the web, etc..  
            //
            // In the code below, I figure out
            //   where my class was loaded from, and get the 'resource' from the 
            //   adjoining resource directory.  Java will return an 'InputStream'
            //   that you can use to read or scan through the resource.
            
            // Get the object that loaded this class, because it keeps track
            //   of -where- things are loaded from for us.  (A very advanced
            //   technique, please use this code.)
        	
	   		ClassLoader myLoader = this.getClass().getClassLoader();	
	        InputStream imageStream = myLoader.getResourceAsStream("resources/" + imageName);
	        
	        BufferedImage image;
	        image = javax.imageio.ImageIO.read(imageStream);  // A handy helper method
	        
	        imageMap.put(imageName, image); //store image in image map
        
	        return image;
        }
        catch (Exception e)
        {
            // On error, just print a message and exit.  
            //   (You should make sure the files are in the correct place.)
            
            System.err.println ("Could not load one of the files: " + imageName);
            System.exit(0);  // Bail out.
            return null;
        } 
		

		
	}
}
