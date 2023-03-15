
// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 1 - President Class
// Hunter Marlette
// Student ID # 200289830

// Note:

// where do I put the printing to console to welcome the user to the program at the beginning? There is no main() method


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;


public class President implements Runnable {
	
	// instance variables:
	String newline = System.getProperty("line.separator");
	WhiteHouse whiteHouse;						//this will cause errors until we create the whiteHouse Class
	
	
	public President(whiteHouse whiteHouse) 	// Constructor Method
	{
		this.whiteHouse = whiteHouse; 			//copy local var from stack to program var
		new Thread(this).start();	// I am not sure if this is actually correct or not, but I think thats what the instructions are asking for
	} // end of constructor
	
	
	
	public void run() 							// Run() Method
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader    br  = new BufferedReader(isr);								// setup user input from console 
		// Not sure if this will work for: "At the top of run(), load the I/O classes to read the keyboard. (See your Therapist program.)"
		
		System.out.println("Reminder: The phrase 'God Bless America' ends the news conference.");
		System.out.println("Enter a statement:");
// try/catch structure option 1:
		try {
			while(true) 
			{
				String statement = br.readLine();
				if(statement.equals("God bless America") || statement.equals("God Bless America") || statement.equals("god bless america") || statement.equals("GOD BELSS AMERICA")) break;
				if (statement.equals("") || statement.isEmpty()) 							// checks for blank message
				{
					System.out.println("Blank messages are ignored.");
				} else {																		// proceed if message is not blank
					whiteHouse.makeAstatement(statement);							
				} // end of if/else statement
			} // end of while loop
		} catch(IOException e) {
			System.out.println("IOException - Possible Keyboard Failure");
		}
		
// try/catch structure option 2:
//		while(true)  
//		{
//			String statement = br.readLine();
//			if(statement.equals("God bless America") || statement.equals("God Bless America") || statement.equals("god bless america") || statement.equals("GOD BELSS AMERICA")) break;
//			if (statement.equals("") || statement.isEmpty()) 							// checks for blank message
//			{
//				System.out.println("Blank messages are ignored.");
//			} else {																		// proceed if message is not blank
//				try{
//					whiteHouse.makeAstatement(statement);
//				} catch(IOException e) {					// THIS IS NOT WHAT HE ASKED FOR BUT I DO NOT KNOW WHAT TO DO OTHERWISE!!!
//					break;									// ???
//				}											// how do I have the try block inside the loop but put the catch block outside the loop?!? That Makes no sense!
//			} // end of if/else statement
//		}
		
		System.out.println("The News Conference is over");
		// (The President thread, encountering the bottom of the run() method, will return to it's Thread object and be terminated, which is appropriate!)
	} // end of run()
	
	
} // end of class
