// Lecture 3 MyFirstGUI Code
// Hunter Marlette 200289830

// There are issues with this code and I do not know why!!!
// It is exactly the same as the code from the lecture and does not function as intended

	// I needed to install Java 19 with Azul JDK 
	// I used homebrew to install this with the command "brew install --cask zulu"
	// I then needed to change Eclipse's settings to use the new environment using the link below:
	// https://marco.dev/change-the-jre-jdk-in-eclipse-on-mac-os-x-and-access-the-sources
// apparently M1 Macs have issues running other versions of Java and must be updated to function properly 

// the button still does not have a red background and I cannot figure out why this is. It should be working but is not. 





//import java.awt.Color;		// ""awt" = Abstract Windowing Toolkit
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// test comment to check github 1
public class Lecture3_MyFirstGUI implements ActionListener 
{
	// Declare the program's "instance" variables outside of any method. (You can declare FIELDS outside of
	// a method but you cannot write any CODE outside of a method.)
	
	// These are the POINTER fields to the GUI window and the GUI objects that will go in that window.
	
	// It is the pointer varibles that are the instance variables. The actual window and button etc. OBJECTS
	// are, like our MyFirstGUI program, created in DYNAMIC storage, and those objects are used by us but are
	// separate from our MyFirstGUI program object. We are allowed to create/load these GUI objects here because
	// we are "initializing" the pointer FIELDS.
	
	// This is like we would say int x = 5; to not only declare an int field called x but to also 
	// assign an initial value to it. And if we need to access the x field from code *anywhere* in our program
	// by *any* thread, we need to do the declaration BETWEEN methods to make x an "instance"/program variable.
	
	// A "local" variable allocated on some thread's STACK is ONLY accessable by THAT thread. And that field is
	// ERASED when that thread exits that method. Sometimes that's good, sometimes it's not.
	
	
	// "Program/"instance" variables allocated in my program
	JFrame     window    = new JFrame("This is a simple GUI !");
	JButton    button    = new JButton("Push me!");
	JTextField textField = new JTextField("Type something here and press ENTER");
	JTextArea  textArea  = new JTextArea("Information to the operator will be shown here.");

	
	public Lecture3_MyFirstGUI() // my CONSTRUCTOR method! (called by the loader)
	// This Method will be  will be in the program object in DYNAMIC Memory
	{
		System.out.println("Hello from the MyFirstGUI program constructor!");		// print to console (not GUI)
		window.getContentPane().add(button,   "North");
		window.getContentPane().add(textArea, "Center");
		window.getContentPane().add(textField,"South");     
		window.setSize(500,400);   // width,height
		window.setLocation(500,0); // x,y (x is "to the right of the left margin, y is "down-from-the-top")
		
		button.setBackground(Color.red);											// ** PROBLEM LINE **
		
	  	textField.setBackground(Color.yellow);
	  	textArea.setFont(new Font("default", Font.BOLD, 20)); // 20 is the font size
	  	textArea.setEditable(false); // keep cursor out (user can't modify)
	  	window.setVisible(true);   // show the window!
	  	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// terminate the program when the user
		                                                        // manually closes the window.
	  	
	  	button.addActionListener(this);    // give address of MyFirstGUI program to the button program.
	  	textField.addActionListener(this); // give address of MyFirstGUI program to the textfield program.
	  	
	} // end of constructor method

	
	public void actionPerformed(ActionEvent ae)		// buttons etc will call here
	{	
		System.out.println("Hello from the actionPerformed() method!");
		if (ae.getSource() == button)    // compare address of calling GUI object to identify it.
		   {
		   System.out.println("The button has been pressed.");
		   textArea.setText("The button has been pressed."); // write text on the GUI!
		   }
		// And, once we have read input from the textField, we should clear it so the user
		//  doesn't have to do that manually before they enter the next input.
		if (ae.getSource() == textField) // compare address of calling GUI object to identify it.
		   {
		   String input = textField.getText(); // Read text from the GUI!
		   System.out.println("Data has been entered in the textField: '" + input + "'");
		   textField.setText(""); // clear input area
		   textArea.setText("Data entered was:  \n" + input); // overwrite previous text in textArea.
		   }
		
	}	// End of ActionPerformed

	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new Lecture3_MyFirstGUI();

	} // end of main	

} // end of class		
