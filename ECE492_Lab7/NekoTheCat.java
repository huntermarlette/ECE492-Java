// ECE492 Java Lab 7 - Neko The Cat - Spring 2023
// Hunter Marlette
// Student ID # 200289830


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;





public class NekoTheCat implements Runnable, MouseListener {

	// instance variables:
	Image catRight1 = new ImageIcon(getClass().getResource("Neko1.gif")).getImage();
	Image catRight2 = new ImageIcon(getClass().getResource("Neko2.gif")).getImage();
	Image catLeft1  = new ImageIcon(getClass().getResource("Neko3.gif")).getImage();
	Image catLeft2  = new ImageIcon(getClass().getResource("Neko4.gif")).getImage();
	Image redBall   = new ImageIcon(getClass().getResource("red-ball.gif")).getImage();
	
	Image cat1 = catRight1;
    Image cat2 = catRight2;
    Image currentImage = catRight1;
    
    JFrame gameWindow = new JFrame("Neko The Cat!");
    JPanel gamePanel  = new JPanel();
    
    int     catxPosition  = 1;    
    int     catyPosition  = 50;
    int     catWidth      = catRight1.getWidth(gamePanel);
    int     catHeight     = catRight1.getHeight(gamePanel);
    int     ballxPosition = 0;
    int     ballyPosition = 0;
    int     ballSize      = redBall.getWidth(gamePanel); 
    int     sleepTime     = 100; // pause time between image repaints (in ms)
    int     xBump         = 10;  // amount (in pixels) cat image is moved each repaint.
    boolean catIsRunningToTheRight = true; // initially
    boolean catIsRunningToTheLeft  = false;// initially
    boolean ballHasBeenPlaced      = false;// initially
    
    Graphics g;
    
    AudioClip soundFile = Applet.newAudioClip(getClass().getResource("spacemusic.au")); 
	
    
    
	public NekoTheCat() throws Exception // CONSTRUCTOR
		{
		System.out.println("Building the GUI");
		
		// Build the GUI
		gameWindow.getContentPane().add(gamePanel, "Center");	// create the window
		gamePanel.setBackground(Color.white);		// set background color
		
		// Show Window	
		gameWindow.setLocation(10, 10); // horizontal, vertical
		gameWindow.setSize(800, 800); // width,height in pixels
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setVisible(true);
		
		System.out.println("GUI Built");
		
		g = gamePanel.getGraphics();
		gamePanel.addMouseListener(this); // call me when a mouse action/event happens!
		soundFile.play();		// runs sound file once - will add a loop() function Later to make this run continually
		
// Start an application thread in the constructor to be our animation thread in run().
		//NekoTheCat Neko = new NekoTheCat();
		//Neko.start();
		//new Thread(this).start();
		//new Thread().start();		// I have no clue if this is what I am supposed to do or not!
		new Thread(this).start(); // this just-created thread branches into our run() method, but the thread that called this "new" statement continues on!
		} // end of constructor

	
	public static void main(String[] args) throws Exception // main method
		{
		new NekoTheCat();		// without this line, the program does nothing!
		
		} // end of main

	
	
	
	
	
	public void run() // Run() Method
		{
		System.out.println("Run() method called");
		// draw the images just as a test:
		//g.drawImage(catRight1,0,0,gamePanel);//imageName, x coordinate, y coordinate, where to draw
		//g.drawImage(catRight2,1*catWidth,0,gamePanel);
		//g.drawImage(catLeft1, 2*catWidth,0,gamePanel);
		//g.drawImage(catLeft2, 3*catWidth,0,gamePanel);
		//g.drawImage(redBall,  4*catWidth,0,gamePanel);
		
		while(true)
			{
			// 1. Blank out the last image
			g.setColor(Color.white);
			g.fillRect(catxPosition, catyPosition, catWidth, catHeight);	//x of upper-left-corner, y of upper-left-corner, width, height.  This also does the draw!
			
	        // 2. Bump the location for the new image
			catxPosition = catxPosition + xBump;
		    catyPosition = catyPosition;
		    
	        // 3. Select the next image.
		    if (currentImage == cat1) currentImage = cat2;
		    else                     currentImage = cat1;
		    
	        // 4. Draw the next cat image
		    g.drawImage(currentImage,catxPosition,catyPosition,gamePanel);
		    
	        // 5. Pause briefly to let human eye see the new image!
		    try {Thread.sleep(sleepTime);}
		    catch(InterruptedException ie){}
			
			} // end of while loop
		
		} // end of run()

	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent me) {
		// erase old ball
		g.setColor(Color.white); // set the draw color to the background color
		g.fillRect(ballxPosition, ballyPosition, ballSize, ballSize);//x of upper-left-corner, y of upper-left-corner, width, height.  This also does the draw!
		
		// retrieve new mouse click position
		ballxPosition = me.getX();
	    ballyPosition = me.getY();
	    //System.out.println("Mouse was clicked at x=" + ballxPosition + ", y=" + ballyPosition);
	    
	    // draw new ball image
	    g.drawImage(redBall, ballxPosition, ballyPosition, gamePanel);
	    
	    
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
} // end of class
