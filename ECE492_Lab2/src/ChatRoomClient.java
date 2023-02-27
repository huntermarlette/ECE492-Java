
// ECE492 Java Lab 2 - Chat Room Client - Spring 2023
// Hunter Marlette
// Student ID # 200289830

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.Socket;

//import java.awt.event.ActionEvent;

public class ChatRoomClient extends JFrame implements ActionListener 
{
	// INSTANCE variables (allocated in the "program object" in Dynamic storage)
	Socket s;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	String newLine = System.lineSeparator();
	int fontSize = 20;
	int maxFontSize = 50;
	int minFontSize = 5;
	
	// GUI Objects
	JFrame      chatWindow          = new JFrame(); // like int x = 5; to provide an inital value to the pointer field
	JPanel      topPanel            = new JPanel();
	JPanel      middlePanel         = new JPanel();
	JPanel      bottomPanel         = new JPanel();
	JLabel      sendMsgLabel        = new JLabel("Enter a message here and push SEND below.");
	JLabel      whosInLabel         = new JLabel("Who's in the chat room:");
	JLabel      receivedMsgsLabel   = new JLabel("Received messages (including our sends)");
	JButton     sendPublicButton    = new JButton("Send To Everyone In");
	JButton     availableButton     = new JButton();
	JTextField  errMsgTextField     = new JTextField("Error messages will show here.");
	JTextArea   sendChatArea        = new JTextArea();
	JList<String> whosInList        = new JList<String>(); // note JList is declared to hold String objects (chat names)
	JTextArea   receiveChatArea     = new JTextArea();
	JScrollPane sendScrollPane      = new JScrollPane(sendChatArea);
	JScrollPane whosInScrollPane    = new JScrollPane(whosInList); 
	JScrollPane receiveScrollPane   = new JScrollPane(receiveChatArea); 
	 
	//Window Menu items
	MenuBar  menuBar             = new MenuBar();
	Menu     fontMenu            = new Menu("Font");
	MenuItem biggerFontMenuItem  = new MenuItem("Bigger");
	MenuItem smallerFontMenuItem = new MenuItem("Smaller");
    
    
	public ChatRoomClient(String serverAddress, String clientName, String password) throws Exception // My constructor method
	{
		if (serverAddress.contains(" ") || clientName.contains(" ") || password.contains(" "))
		{
		    throw new IllegalArgumentException("Parameters may not contain blanks."); // also returns.
		}
		System.out.println("bookmark 3");
		
		System.out.println("Connecting to the chat room server at " + serverAddress + " on port 2222.");
		s = new Socket(serverAddress, 2222); // connect (server net address is specified by user but server port # is assumed)
		
		System.out.println("Connected to the chat server!");
		
		oos = new ObjectOutputStream(s.getOutputStream()); // oos is declared as an instance variable.
		oos.writeObject(clientName + " " + password);        // send the "join message" (a String)
		ois = new ObjectInputStream(s.getInputStream());   // ois is declared as an instance variable.
		String reply = (String) ois.readObject();          // wait for server response (note cast of receive type from Object to String)
		
		if (reply.startsWith("Welcome")) 
		{
			   System.out.println("Join was successful!");
		} else { 
			   throw new IllegalArgumentException("Join of " + clientName + " with password " + password + " was not successful."); // also returns
		}
			 
			 
	} // end of constructor

	
	@Override
	public void actionPerformed(ActionEvent ae)	// buttons etc will call here
	{	
		
	} // end of Actions Performed
	
	
	public static void main(String[] args) throws Exception 
	{
		//System.out.println("bookmark 0");
		// I could shorten these if statements into single line commands
		if (args.length !=3) 
		{
			System.out.println("Restart. Provide 3 command line parameters: ServerAddress ChatName password");
		}
		//System.out.println("bookmark 0.5");
		if (args.length ==3) 
		{
			//System.out.println("bookmark 1");
			try {
				System.out.println("bookmark 1.5");
			    ChatRoomClient crc = new ChatRoomClient(args[0], args[1], args[2]); // "new" calls the ObjectLoader
			    System.out.println("bookmark 2");
			    // crc.receive(); // branch the main thread into the program object's receive() method to
			    }                 // receive & display chat messages received from the server (other clients).
			catch(Exception e)
			    {   
			    System.out.println(e); // print the exception object as the error message
			    
			    System.out.println("Connection to ChatRoomServer at " + args[0] + " on port 2222 has failed.");
			    System.out.println("Is the server started? Have you entered the correct network address for the server?"); 
			    
			    return; // can't continue if can't load the program!
			    }       // (OR we have encountered an Exception in the receive() method during the receive() process.)
		}
		
	} // end of main

} // end of class 
