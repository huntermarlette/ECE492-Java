
// ECE492 Java Lab 1 - Remote Therapist - Spring 2023
// Hunter Marlette
// Student ID # 200289830


import java.io.*;
import java.net.*;


public class RemoteTherapyServer_Hunter 
{
	public static void main(String[] args) throws Exception
	{
		String newline = System.getProperty("line.separator");					//This will retrieve line separator dependent on OS.
		String[] answers = {"Yes", "No", "I don't think so", "Of course", "Why not?"};	// declaring the bank of possible responses from the "Therapist"
		
		System.out.println("Welcome to the The Remote Therapy Server Application created by Hunter Marlette. "
				+ newline + "Terminate the session with the red button in the upper right of the eclipse console window or by using 'Ctrl-C' in your command line window" + newline);
		
		if (args.length > 0)													// prints to the console that the server ignores any entered parameters if any are given
		{
			System.out.println("The server does not accept any command line parameters and is ignoring any entered parms");
			//return;
		}
		
		ServerSocket ss;														// declare "ss" outside of try block so it is available elsewhere in the code
		try {
			ss = new ServerSocket(1111); 										// this app must reserve port number 1111 on it's host computer
			System.out.println("Server is up at " + InetAddress.getLocalHost().getHostAddress() + " waiting for a client to connect on port " + ss.getLocalPort() + newline);
																				// printing the address and port number to the console...
		} // bottom of try
		catch(Exception e) 
		{																		// send error messages if port is occupied on host computer
			System.out.println(newline + "Port 1111 is not available on the RemoteTherapistServer computer.");
			System.out.println("An already-running version of the server may need to be terminated.");
			return; 															// terminate so user can restart the server. 
		}

		
		while (true) 															// go into a loop waiting for the next client to connect...
		{
			int index = (int) (Math.random() * answers.length);					// create a random response for each client question 
			
			Socket s = ss.accept(); 											// WAIT here for next client to CONNECT
			
			DataInputStream dis = new DataInputStream(s.getInputStream()); 		// load high-level input I/O class for this socket
			String message = dis.readUTF(); 									// then WAIT again here for the just-connected client to send
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 	// load high-level output I/O class for this socket
			//dos.writeUTF("Received your message: ' " + message + " ' "); 		
			dos.writeUTF(answers[index]);										// send reply to client request
			
			System.out.println("Received: '" + message + "' from " + s.getInetAddress().getHostAddress() + " on port " + s.getLocalPort());
			System.out.println("Replied with '" + answers[index] + "'" + newline);
			
			dos.close(); 														// terminates the connection & associated resources
		} // end of while loop
		
	} // end of main
	
} // end of class
