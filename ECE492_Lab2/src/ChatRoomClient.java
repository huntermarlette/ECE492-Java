
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
	
	private JTextArea chatBox;
    private JTextField messageField;
    private JButton sendButton;
    
    
	public ChatRoomClient(String hostAddress, String clientName, String password) // My constructor method
	{
		
		
	} // end of constructor

	
	@Override
	public void actionPerformed(ActionEvent ae)	// buttons etc will call here
	{	
		
	} // end of Actions Performed
	
	
	public static void main(String[] args) 
	{
		// I could shorten these if statements into single line commands
		if (args.length !=3) 
		{
			System.out.println("prompt the user to restart and instruct them on which parameters to use");		// needs to change!!! **************
		}
		
		if (args.length !=3) 
		{
			ChatRoomClient crc = new ChatRoomClient(args[0], args[1], args[3]);	// call the constructor method
		}
		
	} // end of main

} // end of class 
