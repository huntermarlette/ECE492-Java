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
		
	}
}
