// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 3 - WhiteHouse Class
// Hunter Marlette
// Student ID # 200289830



public class WhiteHouse {	// The meeting place for reporters and speakers
	
	// instance variable(s):
	String presidentsStatement;

	
	public synchronized void makeAstatement(String presidentsStatement) 	// pick up object's lock on entry to this method
	{
		this.presidentsStatement = presidentsStatement; 	// save local method variable in instance variable
		notifyAll();   										// wake up all threads waiting in this object
															// release object lock on exit
	} // end of method

	
	public synchronized String attendTheNewsConference(String topicOfInterest) 
	{
		//System.out.println("Print Statement Goes Here... ");	
		//System.out.println("WHtopic: " + topicOfInterest);
			// print function as specified cannot go in this function so it has been moved to the reporter class
		
		while (true) 
		{
			try { wait(); } // enter WAIT queue (the Reporter thread releases the object's lock so that other Reporter threads can enter)
			catch(InterruptedException ie) {}
			
			if (presidentsStatement.contains(topicOfInterest) || presidentsStatement.contains("God bless America"))
				return presidentsStatement; // leave the news conference.
			
		} // end of do-forever loop
		
	} // end of method
	
} // end of class
