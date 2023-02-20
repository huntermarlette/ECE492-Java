import java.io.*;

// there was no code written in this actual lecture but the lecture document did have some

public class Lecture4_SayHelloTo {

	public Lecture4_SayHelloTo() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
//	public static void main (String[] args) 
//	{
//		if (args.length == 0) // no command line parameter
//	     {
//	     System.out.println("Restart. Provide names to say 'hello' to as command line parameters!");
//	     return;
//	     }
//	  for (int i = 0; i < args.length; i++) // print all names that were entered as parms.
//	       System.out.println("Hello, " + args[i]);
//	} // end of main
	
	
	public static void main(String[] args)
	  {
	  try {
	      for (int i = 0; i < args.length; i++) 
	           System.out.println("Hello, " + args[i]);
	      }
	  catch (ArrayIndexOutOfBoundsException exp)
	      {
	      System.out.println("Please restart. Provide the names of people to say 'hello' to as command line parameters.");
	      return; // terminate by returning-to-caller (the CommandLineLoader)
	      }
	  System.out.println("Have a nice day!");
	  }	 // end of main
	
	
	
	
	
	
	public void someMethod () throws Exception
	  {
	  try {
	      // code statement #1 
	      // code statement #2
	      // code statement #3
	      // code statement #4
	      }
	  catch (FileNotFoundException fnfe) // This is the beginning of a catch GROUP
	      {
	      System.out.println(fnfe);      // print the Exception object as the error message
	      }                              // Execution will continue after this exception.
	  catch (InvalidPasswordException ipe) 
	      {
	      System.out.println(ipe);       // print the Exception object as the error message
	      return;                        // EXECUTION WILL *NOT* CONTINUE AFTER THIS EXCEPTION!
	      }
	  catch (NumberFormatException nfe)
	      {
	      System.out.println(nfe);      // print the Exception object as the error message
	      }                             // This is the end of this catch GROUP.
	  // following code - execution will continue here after try code if 1) no exception 2) fnfe or 3) nfe but NOT if 
	  	//the exception was InvalidPasswordExcepion because that catch block has a return statement at the bottom.
	  	// ...
	  }

	
	
	
} // end of class
