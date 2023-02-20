
// there was no code written in this actual lecture but the lecture document did have one

public class Lecture4_SayHelloTo {

	public Lecture4_SayHelloTo() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main (String[] args) 
	{
		if (args.length == 0) // no command line parameter
	     {
	     System.out.println("Restart. Provide names to say 'hello' to as command line parameters!");
	     return;
	     }
	  for (int i = 0; i < args.length; i++) // print all names that were entered as parms.
	       System.out.println("Hello, " + args[i]);
	}

}
