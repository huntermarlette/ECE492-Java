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
		new Thread().start();		// I have no clue if this is what I am supposed to do or not!
		
		} // end of constructor

	
	public static void main(String[] args) throws Exception // main method
		{
		new NekoTheCat();		// without this line, the program does nothing!
		
		} // end of main

	
	
	
	
	
	public void run() // Run() Method
		{
		// draw the images just as a test:
		g.drawImage(catRight1,0,0,gamePanel);//imageName, x coordinate, y coordinate, where to draw
		g.drawImage(catRight2,1*catWidth,0,gamePanel);
		g.drawImage(catLeft1, 2*catWidth,0,gamePanel);
		g.drawImage(catLeft2, 3*catWidth,0,gamePanel);
		g.drawImage(redBall,  4*catWidth,0,gamePanel);
		
//		while(true)
//			{
//			// After the test images are printed, have the run thread enter a while(true) loop to "capture" it.
//			}
		
		} // end of run()

	
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent me) {
		// retrieve mouse click position and print it to the console
		ballxPosition = me.getX();
	    ballyPosition = me.getY();
	    System.out.println("Mouse was clicked at x=" + ballxPosition + ", y=" + ballyPosition);
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
