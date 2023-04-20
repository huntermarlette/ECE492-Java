
// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 1 - President Class
// Hunter Marlette
// Student ID # 200289830


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class President implements Runnable 
{
	// instance variables:
	String newline = System.getProperty("line.separator");
	WhiteHouse whiteHouse;						//this will cause errors until we create the whiteHouse Class
	
	
	public President(WhiteHouse whiteHouse) 	// Constructor Method
	{
		this.whiteHouse = whiteHouse; 			// copy local var from stack to program var
		new Thread(this).start();				// this just-created thread branches into our run() method, but the thread that called this "new" statement continues on!
	} // end of constructor
	
	
	
	public void run() 							// Run() Method
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader    br  = new BufferedReader(isr);								// setup user input from console 
		
		System.out.println(newline + "Reminder: The phrase 'God Bless America' ends the news conference.");
		System.out.println("Enter a statement:");

		try {
			while(true) 
			{
				String statement = br.readLine();
				if (statement.equals("") || statement.isEmpty()) 						// checks for blank message
				{
					System.out.println("Blank messages are ignored.");
				} else {																// proceed if message is not blank
					//System.out.println("Statement: '" + statement + "'");				// optional print statement I like to have but isn't called for in the documentation.
					whiteHouse.makeAstatement(statement);	
					if(statement.contains("God bless America") || statement.contains("God Bless America") || statement.contains("god bless america") || statement.contains("GOD BELSS AMERICA")) break;
				} // end of if/else statement
			} // end of while loop
		} catch(IOException e) {
			System.out.println("IOException - Possible Keyboard Failure");
		}
		
		System.out.println("The News Conference is over");
		// (The President thread, encountering the bottom of the run() method, will return to it's Thread object and be terminated, which is appropriate!)
	} // end of run()
	
} // end of class

