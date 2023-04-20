// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 4 - StartTheNewsConference Class
// Hunter Marlette
// Student ID # 200289830

// The wait()/notify() multithreading architecture


// STEPS: 
	// load the WhiteHouse Object first
	
	// count the number of arguments given in the command line
	// Load a reporter object for each of the topics given
		// passing the address of the WhiteHouse object and one of the topics-of-interest command line parameters and a reporter number to each Reporter's constructor.
		// (Probably a good idea to not accept "God bless America" as a topic-of-interest.)
	// When you're loading the Reporter objects, you can go into a for loop that iterates through the args array of command line parameters and let the loop number be the reporter number.

	// Load the President object last, passing the address of the WhiteHouse object to the President's constructor. 
		// The President object will then prompt you, the user, to enter a statement, so we want everything else to be loaded and ready to run when the President speaks!


// Arguments: "world hunger" "world peace" "Donald Trump" "universal health care" "universal health care" "God bless America"



public class StartTheNewsConference {

	public static void main(String[] args) {
		// load the WhiteHouse and the President and multiple Reporter objects into DYNAMIC storage
		// Once the loading is complete, the main thread will encounter the bottom of the main() method and return to the CommandLineLoader where it will be terminated
		
		String newline = System.getProperty("line.separator");					// declaring a line feed statement for organizing the terminal output easier
		System.out.println("Welcome to Lab 4 - News Conference by Hunter Marlette " + newline);

		
		// Instance Variables:
		String topic;									// current topic being loaded
		int num;										// reporter number - "let the loop number be the reporter number" 
		
		WhiteHouse whiteHouse = new WhiteHouse();		// load the WhiteHouse Object first
		
		if(args.length > 0) 							// catches when the person does not enter any arguments
		{
			for(int i = 0; i <= args.length-1; i++)
			// "When you're loading the Reporter objects, you can go into a for loop that iterates through the args array of command line parameters"
			{
				topic = args[i];
				num = i+1;
				
				if(topic.equals("God bless America") || topic.equals("God Bless America") || topic.equals("god bless america") || topic.equals("GOD BELSS AMERICA")) 
				// check for "God Bless America" being falsely used as a topic of interest:
				{
					System.out.println("'God bless America' is not an acceptable topic of interest, and therefore this entry will be ignored.");	
			 	} else {
					//System.out.println("Topic of Interest #" + num + " = " + topic);
					
					//Reporter repo = new Reporter(num, whiteHouse, topic);
			 		new Reporter(num, whiteHouse, topic);
					// Load a reporter object for each of the topics given
					// passing the address of the WhiteHouse object and one of the topics-of-interest command line parameters and a reporter number to each Reporter's constructor.
			 	}
			} // end of for loop
		} else {
			System.out.println("No Topics of interest passed into the program, please restart and try again. ");
		} // end of if/else statement
	
		// Load the President object last, passing the address of the WhiteHouse object to the President's constructor. 
			// The President object will then prompt you, the user, to enter a statement, so we want everything else to be loaded and ready to run when the President speaks!
		new President(whiteHouse);
		
	} // end of main

} // end of class
