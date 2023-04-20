// Lecture 13 - Command Line Calculator 
// Hunter Marlette - Spring 2023

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lecture13_CommandLineCalculator 
{

	public static void main(String[] args) throws Exception
	{
		String newline = System.getProperty("line.separator");					// declaring a line feed statement for organizing the terminal output easier
		
		System.out.println("Welcome to the Calculator program." + newline);
		System.out.println("An operand can be a number or the symbol e, pi or pa (previous answer)");
		System.out.println("Operators are + - * / ^ r (note multiplication operator is *, not x)");
		System.out.println("Sample expressions:  5*2 (=10) or  25r2 (= 5)  or 5^2 (=25) or pi*pa (=78.54)");
		System.out.println("Enter a simple algebraic expression (single operator) or END to terminate:" + newline);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String previousAnswer = "0";
		
		while(true)
	    	{	// read the expression
			String expression = br.readLine().trim().toLowerCase(); 			// convert E, PI, PA (and later X)
			if (expression.length() == 0) continue; 							// ignore ENTER or blank(s)
			if (expression.equals("end")) return;  								// terminate
			if (expression.contains("="))
	        	{
				System.out.println("Expression may not contain '='");	
				continue; // back to top of loop	
	        	}
			
			expression = expression.replace("pa", previousAnswer);
			expression = expression.replace("pi", String.valueOf(Math.PI));
			expression = expression.replace("e",  String.valueOf(Math.E));
			//System.out.println("Expression is: " + expression + newline);
			
			// find the operator!
		    char operator = ' ';
		    int  operatorOffset = 0;
			int i;
			for (i = 1; i < expression.length(); i++) 							//(1st char shouldn't be an operator)
				{                                       						// and starting at 1 allows a unary!
				if((expression.charAt(i) == '+')
			    ||(expression.charAt(i) == '-')
			    ||(expression.charAt(i) == '*')
			    ||(expression.charAt(i) == '/')
			    ||(expression.charAt(i) == '^')
			    ||(expression.charAt(i) == 'r'))
			    	{
			       operator = expression.charAt(i);
			       operatorOffset = i;
			       break;
			    	}
				} // end of for loop
			
			if (operatorOffset == 0) // an operator was not found in the expression  
				{
				System.out.println("operator is missing or is 1st char .");
			    continue;
				}
			if (i == expression.length()-1) 
				{
			    System.out.println("operator cannot be the last character of the expression.");
			    continue;
				}
			//System.out.println("In expression '" + expression + "' the operator " + operator + " was found at offset " + operatorOffset);
			
			String leftOperand = expression.substring(0,operatorOffset).trim();
			String rightOperand = expression.substring(operatorOffset+1).trim();
			//System.out.println("left operand is '" + leftOperand + "' and right operand is '" + rightOperand + "'");
			
			// convert operands from String to double 
		    // Note that parseDouble() will allow a unary operator!

			double leftNumber;
			try { 
				leftNumber = Double.parseDouble(leftOperand);
			    }
			catch(NumberFormatException nfe)
			    {
			    System.out.println("Left operand '" + leftOperand + "' is not a proper operand.");
			    continue;
		        }

			double rightNumber;
			try {
				rightNumber = Double.parseDouble(rightOperand);
			    }
			catch(NumberFormatException nfe)
			    {
			    System.out.println("Right operand '" + rightOperand + "' is not a proper operand.");
			    continue;
			    }
		    //System.out.println("Left operand value is " + leftNumber + " and right operand value is " + rightNumber);
			
		    boolean mustReenter = false;
			double result = 0;
			switch (operator) 
				{
				case '+' : result = leftNumber + rightNumber; 	    							break;
			    case '-' : result = leftNumber - rightNumber; 	    							break;
			    case '*' : result = leftNumber * rightNumber; 	    							break;
			    case '/' : result = leftNumber / rightNumber; 	    							break;
			    case '^' : result = Math.pow(leftNumber,rightNumber);   						break;
			    case 'r' : if (leftNumber > 0){result = Math.pow( leftNumber,1/rightNumber);	break;} 
			               else if (rightNumber%2 == 0) {mustReenter = true; 					break;}
			               else {result =-Math.pow(-leftNumber,1/rightNumber);					break;}
			    default  : throw new IllegalArgumentException("Operator " + operator + " is not recognized.");
				}
			if (mustReenter)
		    	{
				System.out.println("Invalid root expression. Reenter expression.");
				continue;
		    	}
			// Note the Math class offers square root and cube root methods, but the form used above allows higher-order roots.
	        System.out.println(expression + " = "+ result + newline); // show expression and value to user!
	        previousAnswer = String.valueOf(result);
		    
		    
	    	} // end of while(true)
		
		
		
	} // end of main

} // end of class
