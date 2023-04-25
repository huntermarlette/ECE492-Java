// ECE492 Java Lab 7 - Neko The Cat - Spring 2023
// Hunter Marlette
// Student ID # 200289830


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
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
	
    
    
	public NekoTheCat() // CONSTRUCTOR
		{
		// TODO Auto-generated constructor stub
		
		} // end of constructor

	
	public static void main(String[] args) // main method
		{
		// TODO Auto-generated method stub

		} // end of main

	
	public void run() // Run() Method
		{
		
		} // end of run()
	
	
} // end of class
