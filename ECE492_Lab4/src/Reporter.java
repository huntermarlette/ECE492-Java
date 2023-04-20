
// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 2 - Reporter Class
// Hunter Marlette
// Student ID # 200289830



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
		//NOTE: the directions said to put this print function at the top of the attendTheNewsConference() Method, 
			// but it not possible to print the reporter number in that method unless I changed the method to pass
		// in the reporter number variable. So I put it here above the call instead. 
		System.out.println("Reporter #" + reporterNumber + " has arrived at the news conference."
	                + " Reporter's topic-of-interest was: " + topicOfInterest);
			// announces the arrival of the Reporter at the news conference (and prints the Reporter's number and topic-of-interest)
		
		
		String presidentsStatement = whiteHouse.attendTheNewsConference(topicOfInterest);
		
		System.out.println("Reporter #" + reporterNumber + " has returned from the news conference."
		                 + " Reporter's topic-of-interest was: " + topicOfInterest
		                 + ". President's statement was: " + presidentsStatement);
	} // end of run()
	
	
} // end of class
