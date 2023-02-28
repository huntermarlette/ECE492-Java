
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
		System.out.println("bookmark 5");
		
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
		
		
		//COMPOSE the GUI window (Add the GUI objects to the window in memory)
		topPanel.setLayout(new GridLayout(1,3)); // a format with 1 row and 3 columns
	    topPanel.add(sendMsgLabel);              // goes in row 1 column 1
	    topPanel.add(whosInLabel);               // goes in row 1 column 2
	    topPanel.add(receivedMsgsLabel);         // goes in row 1 column 3
	    chatWindow.getContentPane().add(topPanel,"North");

	    middlePanel.setLayout(new GridLayout(1,3)); 
	    middlePanel.add(sendScrollPane);
	    middlePanel.add(whosInScrollPane);
	    middlePanel.add(receiveScrollPane);
	    chatWindow.getContentPane().add(middlePanel,"Center");

	    bottomPanel.setLayout(new GridLayout(1,3)); 
	    bottomPanel.add(sendPublicButton);
	    bottomPanel.add(availableButton); // anticipate some future function
	    bottomPanel.add(errMsgTextField);
	    chatWindow.getContentPane().add(bottomPanel,"South");
	    
	    
	    chatWindow.setTitle(clientName + "'s CHAT ROOM    (Close this window to leave the chat room.)");
	    
	    sendPublicButton.setBackground(Color.green);
	    whosInLabel.setForeground(Color.blue);
	    
	    receiveChatArea.setLineWrap(true);     // cause long text added to be properly
	    receiveChatArea.setWrapStyleWord(true);// "wrapped" to the next line.
	    sendChatArea.setLineWrap(true);
	    sendChatArea.setWrapStyleWord(true);
	    
	    receiveChatArea.setEditable(false); // keep user from changing the output area!
	    errMsgTextField.setEditable(false); // keep user from changing the error message area!
	    
	    System.out.println("bookmark 6");
	    
	    sendPublicButton.addActionListener(this); // sendPublicButton can now call us when the user pushes it!                                      
        // "this" is our program's address in memory (different every run)
	    
	    //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); //Add this line to help Mac GUIs look better:
	    // Alternative Method:
	    chatWindow.setMenuBar(menuBar);
	    menuBar.add(fontMenu);
	    fontMenu.add(biggerFontMenuItem);
	    fontMenu.add(smallerFontMenuItem);
	    biggerFontMenuItem.addActionListener(this); // so these FontMenuItems (buttons!) 
	    smallerFontMenuItem.addActionListener(this);// in the font menu can call us!
	    
	    chatWindow.setSize(800,500);    // width,height
	    chatWindow.setLocation(400,0);  // x,y (x is "to the right of the left margin", y is "down-from-the-top")
	    chatWindow.setVisible(true);    // show it
	    chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // terminate if user closes window
	    
	    System.out.println("bookmark 7");
	} // end of constructor

	
	//@Override
	public void actionPerformed(ActionEvent ae)	// buttons etc will call here
	{	
		errMsgTextField.setText("");                // clear any error message
		errMsgTextField.setBackground(Color.white); // and remove highlight
		
		if (ae.getSource() == sendPublicButton) // is the sendPublicButton calling us?
		   {
		   String chatMessageToSend = sendChatArea.getText().trim(); // remove leading/trailing blanks.
		   if (chatMessageToSend.length() == 0) // a blank message!
		      {
		      errMsgTextField.setText("No message entered to send.");
		      errMsgTextField.setBackground(Color.pink); // highlight to get attention
		      return; // return button's thread to the button.
		      }
		   //System.out.println("Your message '" + chatMessageToSend + "' is being sent to the server.");
		   //sendChatArea.setText(""); // clear the input field.(indication to user that the message was sent.)
		   
		   // Send() Function?
		   try {
			   oos.writeObject(chatMessageToSend);
			   sendChatArea.setText(""); // clear input area
			   }
		   catch(Exception e) // Uh-oh - the network or the server is down!
			   { 
			   // Take no action.
			   // If the connection to the server is down, the readObject() method in the ois has
			   // no-doubt already seen it and the catch block in receive() is telling the user they
			   // must restart the client to re-establish connection with the chat server. 
			   } 
		   return; // return the button's thread to the JButton program
		   } // end of processing block for the send button

		
		
		
		if (ae.getSource() == biggerFontMenuItem)
		   { // This button increases the font size in the in and out chatTextAreas
		   if (fontSize < maxFontSize) fontSize += 1;
		      {
		      sendChatArea.setFont(new Font("default", Font.BOLD, fontSize)); 
		      receiveChatArea.setFont(new Font("default", Font.BOLD,fontSize));
		      }
		   return;   
		   }

		if (ae.getSource() == smallerFontMenuItem)
		   { // This button reduces the font size in the in and out chatTextAreas
		   if (fontSize > minFontSize) fontSize -= 1;
		      {
		      sendChatArea.setFont(new Font("default", Font.BOLD, fontSize)); 
		      receiveChatArea.setFont(new Font("default", Font.BOLD,fontSize));
		      }
		   return;   
		   }
		
		
	} // end of Actions Performed
	
	
	public static void main(String[] args) throws Exception 
	{
		//System.out.println("bookmark 1");
		// I could shorten these if statements into single line commands
		if (args.length !=3) 
		{
			System.out.println("Restart. Provide 3 command line parameters: ServerAddress ChatName password");
		}
		//System.out.println("bookmark 2");
		if (args.length ==3) 
		{
			//System.out.println("bookmark 3");
			try {
				System.out.println("bookmark 4");
			    ChatRoomClient crc = new ChatRoomClient(args[0], args[1], args[2]); // "new" calls the ObjectLoader
			    System.out.println("bookmark 0000");
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
