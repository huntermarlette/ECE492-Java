
// ECE492 Java Lab 3 - Chat Room Server - Spring 2023
// Hunter Marlette
// Student ID # 200289830

// Note:


import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;


public class ChatRoomServer implements Runnable
{
	// Instance Variables:
	ConcurrentHashMap<String,ObjectOutputStream> whosIn = new ConcurrentHashMap<String,ObjectOutputStream>();
	ConcurrentHashMap<String,String> passwords = new ConcurrentHashMap<String,String>();
	private ServerSocket ss; // but don't try to initialize ss for port 2222 here!
	
	
	public ChatRoomServer() throws Exception  	// My constructor method
	{ 		
		ss = new ServerSocket(2222); 										// will throw an Exception if port 2222 is not available
		System.out.println("ChatRoomServer is up at " + InetAddress.getLocalHost().getHostAddress() + " on port " + ss.getLocalPort());
		
		try {	// loads the passwords collection
	        FileInputStream   fis = new FileInputStream("passwords.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        passwords = (ConcurrentHashMap<String,String>) ois.readObject();//cast type of object found
	        ois.close();                                                          //from Object to collection type
	        System.out.println("Previously in the chat room: "); 			// Printing everyone's chat name and password
	        System.out.println(passwords);                       			// on the console can be very handy in testing.
	        }
	   catch(FileNotFoundException fnfe)
	        {
	    	System.out.println("passwords.ser is not found, so an empty collection will be used.");
	        }
		//System.out.println("\n");
		new Thread(this).start(); // this just-created thread branches into our run() method, but the thread that called this "new" statement continues on!
		
	} // end of constructor method
	
	
//	public void actionPerformed(ActionEvent ae) 
//	{
//		
//	} // end of actionsPerformed
	
	
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Welcome to Lab 3 - ChatRoomServer by Hunter Marlette");
		
		if (args.length != 0)												// if any arguments are passed in, print that they will be ignored
		{
			System.out.println("Command line parameters are ignored by ChatRoomServer.");
		}
		new ChatRoomServer();
	} //end of main
	
	
	public void run() 														// client threads enter here
	{
		System.out.println("\nEntered run()");
		
		// declare local variables:
		Socket             s                = null;
		ObjectInputStream  ois              = null;
		ObjectOutputStream oos              = null;
		ObjectOutputStream previousOOS      = null; 						// used on "rejoin"
		String             joinMessage      = null;
		String             chatName         = null;
		String             providedPassword = null;
		String             storedPassword   = null;
		String             clientAddress    = null;
		
// accept connections from chat clients:
		try {
		    s = ss.accept(); 												// wait for next client to connect
		    clientAddress = s.getInetAddress().getHostAddress();
		    System.out.println("New client connecting from " + clientAddress);
		    ois = new ObjectInputStream(s.getInputStream());   				// Don't make ois and oos in ADJACENT statements.
		    joinMessage = ((String) ois.readObject()).trim();  				// Must cast 1st message read from type Object to String.
		    oos = new ObjectOutputStream(s.getOutputStream()); 				// trim() drops leading/trailing (but not imbeded) blanks.

			int blankOffset = joinMessage.indexOf(" ");          			// scan joinMessage for an imbedded blank
			if (blankOffset < 0)                                 			// negative offset return means no blank found.
			{
				try {
					System.out.println("No blank in join message: " + joinMessage);
					oos.writeObject("Invalid format in 1st message."); 		// not specific in case of hacker caller
					oos.close(); 											// kill connection
			    } catch(Exception e) {}
				return;      												// kill client session thread
			} 
			chatName = joinMessage.substring(0,blankOffset).toUpperCase(); 	// 2 parm substring() form is from-to (non-inclusive)
			providedPassword = joinMessage.substring(blankOffset).trim();  	// 1 parm substring() means here-to-end. trim() removes leading blank(s)

//Password Verification:
			if (passwords.containsKey(chatName)) 							// is this chatName a KEY in the passwords collection? (have they previously joined?)
			{
				storedPassword = passwords.get(chatName);  					// if YES, retrive the stored pw for this chatName
				if (providedPassword.equals(storedPassword))     			// case-sensitive compare to pw just entered by user 
	   /*PASS*/ { //If this person's ALREADY IN the whosIn collection, then they are "rejoining" from another address!  
					//System.out.println("   Password is correct");
					if (whosIn.containsKey(chatName))						// Is this chatName is already in the chat room?        
		/*TESTFOR*/ { // Already in! But we will accept a (re)join from a NEW location.
		/*REJOIN*/ 		previousOOS = whosIn.get(chatName); 				// get previous oos before we replace it.
				 		whosIn.replace(chatName, oos);      				// replace old oos with rejoin oos
					    previousOOS.writeObject("Session terminated due to rejoin from another location.");
					    previousOOS.close(); 								// shut down previous connection. (this prompts leave processing)
					    System.out.println(chatName + " is rejoining.");
					}                         
		/*FAIL*/} else { 	// a password was retrieved from the passwords collection, but entered pw is not = to it.
		/* PW */  			// Someone is trying to use an already-taken chat name, or they forgot their pw.
					//System.out.println("   Password is NOT correct!");
					oos.writeObject("Your entered password " + providedPassword + " is not the same as the password stored for chat name " + chatName);
					oos.close(); // hang up.
					System.out.println("Invalid password: " + providedPassword + " instead of " + storedPassword + " for " + chatName);
					return;      											// and kill this client thread
				} 
			} else { 														// accepts new logins
				passwords.put(chatName, providedPassword);
				savePasswords(); 											// save updated passwords list on disk.
				System.out.println(chatName + " is a new client in the chat room.");
			}
			
// Join Processing
			oos.writeObject("Welcome to the chat room " + chatName + " !"); // send "join is successful" to new client
			whosIn.put(chatName,oos);   									// add new-join client
			System.out.println(chatName + " is joining"); 					// trace message on server console 
			
			String[] whosInArray = whosIn.keySet().toArray(new String[0]);
			Arrays.sort(whosInArray); // note the sort() method is not in the array object, it is in a separate Arrays class.
			sendToAllClients("Welcome to " + chatName + " who has just joined (or rejoined) the chat room!");    
			sendToAllClients(whosInArray);									// Now, as a debug trace message, show on the server console the clients that are "in the chat room".
																			// Note we sent a who's-in ARRAY over the network to all the clients, and this little snippet of code
																			// is simply adding them to a single String so we can print it on the server console.
			String whosInString = "";
			for (String name : whosInArray)
			     whosInString += name + ", ";
			System.out.println("Currently in the chat room: " + whosInString);
			
			
		} // bottom of try for join processing
		catch(Exception e) 													// connecting client may not be using oos, or firstMessage was not a String
		{
			System.out.println("Connection failure during join processing from " + chatName + " at " + clientAddress + " " + e);
		    System.out.println("Client " + clientAddress + " join protocol not OOS or 1st message not String. " + e );
		    if (s.isConnected())
		    {
		       try {s.close();}         									// hang up
		       catch(IOException ioe){} 									// already hung up!
		    }
		    return; 														// return to the Thread object to terminate this client thread. 
		}
		finally 															// create a next-client thread whether catch was entered or not. 
		    {
		    new Thread(this).start(); 										// wait for next client to connect
		    }
		// If we are still running here then s, ois, oos are all good and the join message was a String! 
		// If not, we have dumped this caller. Either way we are waiting again (with a new thread) in accept() 
		// in the ServerSocket for the next client to connect. (Note we only call the accept() method with
		// one thread at a time...
	
		System.out.println("end of runnable \n");
	} // end of runnable
	
	
	
	private synchronized void sendToAllClients(Object message) // "synchronized" restricts client
	{                                                        //  threads to enter one-at-a-time
		ObjectOutputStream[] oosArray = whosIn.values().toArray(new ObjectOutputStream[0]);
		for (ObjectOutputStream clientOOS : oosArray)
	    {
			try {clientOOS.writeObject(message);}
			catch (IOException e) {} // do nothing if send error because it's probably
	    }
	} // end of sendToAllClients()
	
	
	
	private synchronized void savePasswords() 	// writing the passwords collection from memory to disk
	{ 
		System.out.println("Entered savePasswords()");
		try {
			FileOutputStream   fos = new FileOutputStream("passwords.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(passwords);
			oos.close();
	    }
		catch(Exception e)
	    {
			System.out.println("passwords collection cannot be saved on disk: " + e);
	    }
	} // end of savePasswords()
	
	
} // end of class
