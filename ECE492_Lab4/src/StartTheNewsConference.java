// ECE492 Java Lab 4 - News Conference - Spring 2023
	// class 4 - StartTheNewsConference Class
// Hunter Marlette
// Student ID # 200289830

// The wait()/notify() multithreading architecture

// Note:




public class StartTheNewsConference {

	public static void main(String[] args) {

		// load the WhiteHouse and the President and multiple Reporter objects into DYNAMIC storage
		// Once the loading is complete, the main thread will encounter the bottom of the main() method and return to the CommandLineLoader where it will be terminated
		
		String newline = System.getProperty("line.separator");					// declaring a line feed statement for organizing the terminal output easier
		System.out.println("Welcome to Lab 4 - News Conference by Hunter Marlette " + newline);
		
		
		
		// load the WhiteHouse Object first
		
		// count the number of arguments given in the command line
		// Load a reporter object for each of the topics given
			// passing the address of the WhiteHouse object and one of the topics-of-interest command line parameters and a reporter number to each Reporter's constructor.
			// (Probably a good idea to not accept "God bless America" as a topic-of-interest.)
		// When you're loading the Reporter objects, you can go into a for loop that iterates through the args array of command line parameters and let the loop number be the reporter number.

		// Load the President object last, passing the address of the WhiteHouse object to the President's constructor. 
			// The President object will then prompt you, the user, to enter a statement, so we want everything else to be loaded and ready to run when the President speaks!
		
		
		

		String topic;
		
		
		//WhiteHouse.attendTheNewsConference(topic);
		WhiteHouse whiteHouse;		// load the WhiteHouse Object first???
		
		// counting arguments
		if(args.length > 0) 
		{
			System.out.println("Test 1");
			//for (int i = args.length-1; i >= 0; i--) 
			for(int i = 0; i <= args.length-1; i++)
			{
				System.out.println("i = " + i);
				System.out.println("Topic of Interest #" + i + " = " + args[i]);
				
			}
			
		} else {
			System.out.println("No Topics of interest passed into the program, please restart and try again?");
		}
	
		
		
		
		
		
		
		
		
	}

}
