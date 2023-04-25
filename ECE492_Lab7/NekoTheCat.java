// ECE492 Java Lab 7 - Neko The Cat - Spring 2023
// Hunter Marlette
// Student ID # 200289830

//Note: I added a few features to make the game a bit nicer than the .jar given by the professor:
	//I added a few additional erase commands to smooth out the animation. 
	//I also added a draw white rectangle section at the beginning of the inner while(true) loop to erase the instruction text at the beginning.
		// there is also one under step 7 if you would rather comment out that first section and keep the text until the end. 


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JApplet;



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
		System.out.println("Hunter Marlette's Neko The Cat Game");
		
		// Build the GUI
		gameWindow.getContentPane().add(gamePanel, "Center");	// create the window
		gamePanel.setBackground(Color.white);		// set background color
		
		// Show Window	
		gameWindow.setLocation(10, 10); // horizontal, vertical
		gameWindow.setSize(800, 800); // width,height in pixels
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setVisible(true);
		
		g = gamePanel.getGraphics();
		gamePanel.addMouseListener(this); // call me when a mouse action/event happens!
		soundFile.loop();		// runs sound file once - will add a loop() function Later to make this run continually
		
		// show game instructions on the screen
		g.setFont(new Font("Times Roman", Font.BOLD, 20)); // Font name, style, size
		g.drawString("Neko the cat is looking for it's red ball!"         ,100,100); // String, x, y
		g.drawString("Click the mouse to place Neko's ball."              ,100,120);
		g.drawString("Can you move the ball to keep Neko from getting it?",100,140);
		g.drawString("(Pull window larger to make the game easier)"       ,100,160);
		
		// Start an application thread in the constructor to be our animation thread in run().
		new Thread(this).start(); // this just-created thread branches into our run() method, but the thread that called this "new" statement continues on!
		} // end of constructor

	
	public static void main(String[] args) throws Exception // main method
		{
		new NekoTheCat();
		} // end of main

	
	
	public void run() // Run() Method
		{
		System.out.println("Run() method called");
		while(true)
			{
	    	while(true)
		    	{
	    		
	    		while ((catxPosition > 0) &&  (catxPosition < gamePanel.getSize().width)) 
		          	{
	    			if(ballHasBeenPlaced == true)		// I added this To delete the initial instruction text from the start screen.
						{
						g.setColor(Color.white);		
						g.fillRect(80, 80, 600, 100);
						}
	    			
		          	g = gamePanel.getGraphics(); // get g again in case user has resized the window! 
		          	
		          	// move Neko again in the current direction
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
				    
				    // oops! - turn Neko around.
			    	if (catxPosition > (gamePanel.getSize().width - catWidth))	// I changed this to include the width of the cat so that it would bounce better on the right side
				        {
				        reverseDirectionFromRightToLeft();
				        catxPosition = gamePanel.getSize().width - catWidth;
				        }
			     
				    if (catxPosition < 0)
				    	{
				        reverseDirectionFromLeftToRight();
				        catxPosition = 1;
				        }
				    
				    // 6. If necessary, redirect the cat toward the ball.
			        if (ballHasBeenPlaced) // first ensure that the ball is "in play"
			           	{
			        	// If cat is BELOW the ball
			        	// then move cat up 1 line. (subtract 10 pixels)
			        	if (catyPosition > ballyPosition) 
			        		{
			        		g.setColor(Color.white);										// I added this because I was getting ghosting if I didn't include it here as well
							g.fillRect(catxPosition, catyPosition, catWidth, catHeight);	//x of upper-left-corner, y of upper-left-corner, width, height.  This also does the draw!
			        		catyPosition = catyPosition - 10;
			        		}
			       	   
			           	// If cat is ABOVE the ball 
			    	   	// then move cat down one line. (add 10 pixels)
			    	   	if (catyPosition < ballyPosition) 
			    	   		{
			    	   		g.setColor(Color.white);
							g.fillRect(catxPosition, catyPosition, catWidth, catHeight);	//x of upper-left-corner, y of upper-left-corner, width, height.  This also does the draw!
			    	   		catyPosition = catyPosition + 10;
			    	   		}
			        	   
			           	// If the cat is running to the left
			           	// and the ball is to the right of the cat 
			    	   	if((catIsRunningToTheLeft  == true) && (catxPosition < ballxPosition))
			    	   		reverseDirectionFromLeftToRight();
			        	  
			       	    //If the cat is running to the right
			       	   	// and the ball is to the left of the cat 
			    	   	if((catIsRunningToTheRight  == true) && (catxPosition > ballxPosition))
			    	   		reverseDirectionFromRightToLeft();    
			           	}
				       
			        // 7. Proximity test to see if Neko is "at" the ball. 
			        if ((Math.abs(catyPosition - ballyPosition) < 10)   // y within 10   
			        		&& (Math.abs(catxPosition - ballxPosition) < 10))  // x within 10 pixels
			            {
			            // Take the Neko-got-the-ball action! 
			        	gamePanel.removeMouseListener(this);
			        	g.setColor(Color.red);
			        	g.setFont(new Font("Times Roman", Font.BOLD, 50)); // Font name, style, size
			    		g.drawString("At last I have my ball!", 100, 60); // String, x, y
			    		soundFile.stop();
			    		
			    		//g.setColor(Color.white);		// I added this To delete the initial text from the start screen.
						//g.fillRect(80, 80, 600, 100);	
			    		return;
			            }
		          	}	
	    		
		    	} // end of inner while(true) loop
			} // end of outer while(true) loop
		
		} // end of run()

	
	private void reverseDirectionFromRightToLeft()
	    {
	    xBump = -xBump; // reverse increment
	    cat1 = catLeft1;
	    cat2 = catLeft2;
	    catIsRunningToTheLeft  = true;
	    catIsRunningToTheRight = false;
	    }

	private void reverseDirectionFromLeftToRight()
	    {
	    xBump = -xBump;	
	    cat1 = catRight1;
	    cat2 = catRight2;
	    catIsRunningToTheRight = true;
	    catIsRunningToTheLeft  = false;
	    }
	

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
	    ballHasBeenPlaced = true;
	    
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
