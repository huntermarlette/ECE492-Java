
import java.io.*;
import java.net.*;

public class Lecture2Server 
{

	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(1234); 								// this app must reserve this port number on it's host computer
		System.out.println("Server is up at " + InetAddress.getLocalHost().getHostAddress() + " waiting for a client to connect on ports " + ss.getLocalPort());
																				// printing the address and port number to the console...
		while (true) 															// go into a loop waiting for the next client to connect...
			{
			Socket s = ss.accept(); 											// WAIT here for next client to CONNECT
			
			DataInputStream dis = new DataInputStream(s.getInputStream()); 		// load high-level input I/O class for this socket
			String message = dis.readUTF(); 									// then WAIT again here for the just-connected client to send
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 	// load high-level output I/O class for this socket
			dos.writeUTF("Got your message: ' " + message + " ' "); 			// send reply to client request
			
			dos.close(); 														// terminates the connection & associated resources
			// or s.close(); 													// "flush out the message and hang up!" 
			System.out.println("Received: '" + message + "' from " + s.getInetAddress().getHostAddress());
																				// debug trace on server console
			} // end of while loop 
		
	} // end of main
} // end of class
