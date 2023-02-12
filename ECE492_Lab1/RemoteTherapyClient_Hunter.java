
// ECE492 Java Lab 1 - Remote Therapist - Spring 2023
// Hunter Marlette
// Student ID # 200289830

// Note: I have included a bunch of print calls in this code. These are mainly for testing and debugging my code as I go along. 
// 		 They are commented out because they were either only temporarily used or I did not need them displayed to the user in the final application. 

// Note: My original design was to prompt the user to enter the network address within the console window and store that as a variable instead of using 
// 		 the Args[0] method of taking the network address from the application startup. I prefer this for multiple reasons. It is more intuitive to the 
// 		 user to have a prompt to tell them when to enter any information they are expected to give instead of hoping they remembered that they need to 
// 		 include that in their terminal window. This would leave a lot less ambiguity to the program and eliminate a substantial potential for frustrating 
// 		 user error. In addition to this, you could open up the ability to prompt the user to reenter a different network address if issues were encountered 
//  	 instead of forcing them to terminate the whole application. If there is any duplicate code commented out in relation to the network address, it is 
// 		 because I left the alternate statements from my initial code and just commented them out. 

// Why are we writing to the log file within the client application? 
// If the purpose of recording this data was for the hypothetical therapy company to review them for their records, wouldn't they have to be on the host computer?


import java.io.*;
import java.net.*;
import java.util.Date;

public class RemoteTherapyClient_Hunter 
{

	public static void main(String[] args) throws Exception
	{
		String newline = System.getProperty("line.separator");							//This will retrieve line separator dependent on OS.
		
		System.out.println("Welcome to the The Remote Therapy Client Application created by Hunter Marlette.");
		System.out.println("Enter only questions that can be answered with yes or no.");
		System.out.println("Type 'QUIT' any time you are prompted for a question to end your session" + newline);
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader    br  = new BufferedReader(isr);								// setup user input from console 
		
		if (args.length != 1)															// require a single command line parameter for network address
		{
			System.out.println(newline + "Restart. Provide the server address as a single  command line parameter enclosed in quotes ");
			return;																		// terminate so user can start over.
		}
		String network = args[0];														// This is the line if you want to get the network address from the first argument
		//System.out.println("Please enter the network address you wish to access: ");
		//String network = br.readLine();													// get the Network address from the console
		//String network = "localhost";		// Only for testing purposes
		System.out.println("The address you entered was: " + network + newline);
		
		System.out.println("Opening Log File..." + newline);
		FileWriter logFile = new FileWriter("TherapyLog.txt", true); 					// open log file in append mode
		BufferedWriter log = new BufferedWriter(logFile);
		// write a date/time stamp to the log file
		log.newLine();
 		log.write("New therapy session on " + new Date()); 								// The date class is in the java.util package
		log.newLine();																	// put a new line character at the end of the line

		while (true) 
		{
			System.out.println("Enter your question below...");
			String question = br.readLine();
				
			if(question.equals("QUIT") || question.equals("quit") || question.equals("Quit") 
					|| question.equals("END") || question.equals("end") || question.equals("End") 
					|| question.equals("STOP") || question.equals("stop") || question.equals("Stop") 
					|| question.equals("EXIT") || question.equals("exit") || question.equals("Exit")) break; 	
																						// watches for the user request to terminate program 
			if (question.equals("") || question.equals(" ")) 								// checks for blank message
			{
				System.out.println("Blank messages are ignored.");
			} else {																		// proceed if message is not blank
				try {
					//System.out.println("Connecting to Server..." + newline);
					//Socket s = new Socket(network, 1111);
					Socket s = new Socket(args[0], 1111);  									// wait for server to accept connection
					
					DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 		// load high-level output class to the server address entered from user
					dos.writeUTF(question); 												// send the entered question to the server
					DataInputStream dis = new DataInputStream(s.getInputStream()); 			// same socket used for input back from server
					String reply = dis.readUTF(); 											// wait for reply from server
						
					//System.out.println("Reply from server: \r" + reply + newline);
					System.out.println(reply + newline); 									// print the reply from server
					
					log.write("Question was: '" + question + "' Therapist answer was: '" + reply + "'");	// write each response to the log file
					log.newLine();
				} // bottom of try						
				catch(ConnectException ce) 													// catches exceptions from the try section
				{
					//System.out.println("We are unable to connect to the server. Perhaps the server app is not up yet? ");
					System.out.println("Connection to TherapyServer at " + args[0] + " on port 1111 has failed.");
					//System.out.println("Check above if the server network address you provided correct? If not, please restart the application and reenter the correct one. \r\r");
					System.out.println("Is server network address correct? Has the server been started on port 1111?"); 
					return; 																// terminate so user can fix & restart.
				}
			} // end of if else statement
			
		} // end of while loop
		System.out.println("\rClosing Log File...");
		log.close(); 																		// CLOSE of output file writes it to disk!
		//System.out.println("Terminating Application... Have A Nice Day! ");
		System.out.println("Thank you for using Remote Therapy Application");
		
	} // end of main
	
}	// end of class
