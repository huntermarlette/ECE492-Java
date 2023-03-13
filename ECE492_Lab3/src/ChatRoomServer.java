
// ECE492 Java Lab 3 - Chat Room Server - Spring 2023
// Hunter Marlette
// Student ID # 200289830

// Note:


import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;


public class ChatRoomServer implements Runnable
{
	// Instance Variables:
	ConcurrentHashMap<String,ObjectOutputStream> whosIn = new ConcurrentHashMap<String,ObjectOutputStream>();
	ConcurrentHashMap<String,String> passwords = new ConcurrentHashMap<String,String>();
	private ServerSocket ss; // but don't try to initialize ss for port 2222 here!
	
	public ChatRoomServer() throws Exception  	// My constructor method
	{ // There is no GUI in this program so the constructor is much more simple
		
		ss = new ServerSocket(2222); // will throw an Exception if port 2222 is not available
		System.out.println("ChatRoomServer is up at " + InetAddress.getLocalHost().getHostAddress() + " on port " + ss.getLocalPort());
		
	} // end of constructor method
	
	public void actionPerformed(ActionEvent ae) 
	{
		
		
	} // end of actionsPerformed
	
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Welcome to Lab 3 - ChatRoomServer by Hunter Marlette");
		
		if (args.length != 0)	// if any arguments are passed in, print that they will be ignored
		{
			System.out.println("Command line parameters are ignored by ChatRoomServer.");
		}
		new ChatRoomServer();
		
	} //end of main
	
	
	public void run() 
	{
		
	} // end of runnable
	
} // end of class
