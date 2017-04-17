package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * A path test panel is a GUI panel that displays a tower
 * defense path on the screen, and animates a small object
 * moving along the path.
 * 
 * This class won't be part of the final project - we're just
 * using it for testing.
 * 
 * @author Peter Jensen and Jared Hansen
 * @version March 29, 2017
 */
public class TowerDefense extends JPanel implements Runnable, ActionListener, MouseListener
{
    // This constant avoids an obnoxious warning, but it is totally unused by us.
    //   It would only be relevant if we were using object serialization.
    
    private static final long serialVersionUID = 42L;
    
    // Fields (object variables) 
    
    private GameState game;

    	// Students will add a few more fields (object variables) to keep
        //   track of their path object, the circle position (as a percentage),
        //   and possibly a Timer object.
    
    // Methods
    
    /**
     * The application entry point.
     * 
     * @param args unused
     */
    public static void main (String[] args)
    {
        // Main runs in the 'main' execution thread, and the GUI
        //   needs to be built by the GUI execution thread.
        //   Ask the GUI thread to run our 'run' method (at some
        //   later time).
    	
        SwingUtilities.invokeLater(new TowerDefense());

        // Done.  Let the main thread of execution finish.  All the
        //   remaining work will be done by the GUI thread.
    }
    
    /**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
    public void run ()
    {
    	JFrame f = new JFrame("Path Tester");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.setContentPane(this);
        
        Dimension panelSize = new Dimension(800, 600);
        
        // Set the size of this panel to match the size of the image.

        this.setMinimumSize(panelSize);
        this.setPreferredSize(panelSize);
        this.setMaximumSize(panelSize);
        
        f.pack();
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);

        //new GameState object
        game = new GameState();

        // Create a timer (for animation), have it call our actionPerformed
        //   method 60 times a second.
        
    	Timer t = new Timer(16, this);
    	t.start();
        
    	this.addMouseListener(this);
    }
    
    /**
     * Draws the image, path, and the animating images.
     * 
     * (The background is not cleared, it is assumed the backdrop
     * fills the panel.)
     * 
     * @param g the graphics object for drawing on this panel
     */
    public void paint (Graphics g)
    {
    	 game.draw((Graphics2D) g);
    }
    
    /**
     * The actionPerformed method is called (from the GUI event loop)
     * whenever an action event occurs that this object is lisening to.
     * 
     * We assume that the Timer has expired, so
     * we advance our small sphere along the path.
     * 
     * @param e the event object 
     */
    public void actionPerformed (ActionEvent e)
    {
    	game.update();
    	repaint(); //repaint after each event called
    }
    
    /* Unused mouse events - notice empty bodies on the same line.      
     *   We need these methods becuase we implement MouseListener,      
     *   but we don't use them.      
     */         
    
    @Override public void mousePressed (MouseEvent e) {}     
    @Override public void mouseClicked (MouseEvent e) {}     
    @Override public void mouseEntered (MouseEvent e) {}     
    @Override public void mouseExited  (MouseEvent e) {}     
    @Override public void mouseReleased(MouseEvent e) {}
}
