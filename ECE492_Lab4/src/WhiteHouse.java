// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 3 - WhiteHouse Class
// Hunter Marlette
// Student ID # 200289830

// Note:


public class WhiteHouse {	// The meeting place for reporters and speakers
	
	// instance variable(s):
	String presidentsStatement;

	
	public void makeAstatement(String presidentsStatement) 	// pick up object's lock on entry to this method
	{
		this.presidentsStatement = presidentsStatement; 	// save local method variable in instance variable
		notifyAll();   										// wake up all threads waiting in this object
															// release object lock on exit
	} // end of function

	
	public synchronized String attendTheNewsConference(String topicOfInterest) 
	{
		String newline = System.getProperty("line.separator");					// declaring a line feed statement for organizing the terminal output easier
		
		//System.out.println("Print Statement Goes Here... ");	// delete later!!! ******************
		System.out.println("WHtopic: " + topicOfInterest);
		//System.out.println("Reporter #" + reporterNumber + " has returned from the news conference."
        //        + " Reporter's topic-of-interest was: " + topicOfInterest); 	// *********
		// announces the arrival of the Reporter at the news conference (and prints the Reporter's number and topic-of-interest)
			// I am not sure how to do this with the variables passed in - possibly referring to the print line within the reporter code after this section
		while (true) 
		{
			System.out.println("Test Inside While Loop");
			try { wait(); } // enter WAIT queue (the Reporter thread releases the object's lock so that other Reporter threads can enter)
			catch(InterruptedException ie) 
			{
				System.out.println("Test ie exception" + newline);
			}
			
			System.out.println("Test after try/catch");
			
			if (presidentsStatement.contains(topicOfInterest) || presidentsStatement.contains("God bless America"))
				return presidentsStatement; // leave the news conference.
			
		}
	}
}
