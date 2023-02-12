import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lecture1_LocalTherapist 
{
	// Lecture 1 Class Program - Essentially an AI magic 8 ball

	public static void main(String[] args) throws Exception	// handles exceptions here
	{
		System.out.println("Welcome to the Therapist by Hunter Marlette.");
		System.out.println("Enter a question that can be answered with yes or no.");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader    br  = new BufferedReader(isr);
		//String[]          answers = new String[10];
		String[] answers = {"I don't think so", "Of course", "Why not?"};
		
		while (true) 
		{
			System.out.println("Enter your Question.");
			String question = br.readLine();
			int index = (int) (Math.random() * answers.length);
			System.out.println(answers[index]);
		} // end of while loop
	} // end of main

} // end of class
