
// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 2 - Reporter Class
// Hunter Marlette
// Student ID # 200289830

// Note:


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;


public class Reporter implements Runnable {
	
	// instance variables:
	int        reporterNumber;
	String     topicOfInterest;
	WhiteHouse whiteHouse;
	
	
	public Reporter(int reporterNumber, WhiteHouse whiteHouse, String topicOfInterest) // Constructor Method
	{
		this.reporterNumber  = reporterNumber;
		this.whiteHouse      = whiteHouse; 			//copy local var from stack to program var
		this.topicOfInterest = topicOfInterest; 
		
		new Thread(this).start();
	} // end of constructor

	
	
	public void run() 								// Run() Method
	{
		String presidentsStatement = whiteHouse.attendTheNewsConference(topicOfInterest);
		System.out.println("Reporter #" + reporterNumber + " has returned from the news conference."
		                 + " Reporter's topic-of-interest was: " + topicOfInterest
		                 + ". President's statement was: " + presidentsStatement);
	} // end of run()
	
	
} // end of class
