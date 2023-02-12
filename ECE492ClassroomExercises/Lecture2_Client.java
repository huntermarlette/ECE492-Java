
import java.io.*;
import java.net.*;

public class Lecture2Client 
{

	public static void main(String[] args) throws Exception
	{
		if (args.length != 1)													// require a single command line parameter
			{
			System.out.println("Restart. Provide the message to send a single  command line parameter. (enclose multiple words in quotes)");
			return;																// terminate so user can start over.
			} // end of if statement
		
		System.out.println("Message to send is: " + args[0]);					// show the message the user entered
		// To be clear, this is only the argument 0 used from eclipse or when running the code in terminal. This does not accept a user input. 
	
		try {
			Socket s = new Socket("localhost", 1234); 							// wait for server to accept connection
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 	// load high-level output class
			dos.writeUTF(args[0]); 												// send the entered command line parameters (in slot 0 of args command line parms array)
			
			DataInputStream dis = new DataInputStream(s.getInputStream()); 		// same socket
			String reply = dis.readUTF(); 										// wait for reply from server
			
			System.out.println("Reply from server is: " + reply ); 				// that's all, folks!
		} // bottom of try
		
		catch(ConnectException ce) 
		{
			System.out.println("We are unable to connect to the server. Perhaps the server app is not up yet? ");
			System.out.println("Is the server network address and port number (provided as a command line parameter) correct? ");
		}
	
	
	} // end of main
	
} // end of class
