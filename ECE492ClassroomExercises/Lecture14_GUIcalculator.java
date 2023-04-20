// Lecture 14 - Calculator with GUI (Building upon lecture 13's command line calculator)
// Hunter Marlette - Spring 2023

// NOTE:
	// MacOS has issues with the method of GUI creation that we have been using in this class.
	// GO BACK IN THE OLDER LABS AND CODE FILES AND FIND ALL THE TRICKS I USED THE FIRST TIME AROUND!!!	

	// the program is supposed to ignore blank entries and so far it does not do that!!!



//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;



public class Lecture14_GUIcalculator implements ActionListener
{

	public static void main(String[] args) //throws Exception
	{
		String newline = System.getProperty("line.separator");					// declaring a line feed statement for organizing the terminal output easier
		
		try{new Lecture14_GUIcalculator();}
		catch(Exception e){System.out.println(e);} // print the Exception object as the error msg.
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.println("Welcome to the Calculator program." + newline);
//		System.out.println("An operand can be a number or the symbol e, pi or pa (previous answer)");
//		System.out.println("Operators are + - * / ^ r (note multiplication operator is *, not x)");
//		System.out.println("Sample expressions:  5*2 (=10) or  25r2 (= 5)  or 5^2 (=25) or pi*pa (=78.54)");
//		System.out.println("Enter a simple algebraic expression (single operator) or END to terminate:" + newline);
//	
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String previousAnswer = "0";
//		
//		while(true)
//	    	{	// read the expression
//			String expression = br.readLine().trim().toLowerCase(); 			// convert E, PI, PA (and later X)
//			if (expression.length() == 0) continue; 							// ignore ENTER or blank(s)
//			if (expression.equals("end")) return;  								// terminate
//			if (expression.contains("="))
//	        	{
//				System.out.println("Expression may not contain '='");	
//				continue; // back to top of loop	
//	        	}
//			
//			expression = expression.replace("pa", previousAnswer);
//			expression = expression.replace("pi", String.valueOf(Math.PI));
//			expression = expression.replace("e",  String.valueOf(Math.E));
//			//System.out.println("Expression is: " + expression + newline);
//			
//			// find the operator!
//		    char operator = ' ';
//		    int  operatorOffset = 0;
//			int i;
//			for (i = 1; i < expression.length(); i++) 							//(1st char shouldn't be an operator)
//				{                                       						// and starting at 1 allows a unary!
//				if((expression.charAt(i) == '+')
//			    ||(expression.charAt(i) == '-')
//			    ||(expression.charAt(i) == '*')
//			    ||(expression.charAt(i) == '/')
//			    ||(expression.charAt(i) == '^')
//			    ||(expression.charAt(i) == 'r'))
//			    	{
//			       operator = expression.charAt(i);
//			       operatorOffset = i;
//			       break;
//			    	}
//				} // end of for loop
//			
//			if (operatorOffset == 0) // an operator was not found in the expression  
//				{
//				System.out.println("operator is missing or is 1st char .");
//			    continue;
//				}
//			if (i == expression.length()-1) 
//				{
//			    System.out.println("operator cannot be the last character of the expression.");
//			    continue;
//				}
//			//System.out.println("In expression '" + expression + "' the operator " + operator + " was found at offset " + operatorOffset);
//			
//			String leftOperand = expression.substring(0,operatorOffset).trim();
//			String rightOperand = expression.substring(operatorOffset+1).trim();
//			//System.out.println("left operand is '" + leftOperand + "' and right operand is '" + rightOperand + "'");
//			
//			// convert operands from String to double 
//		    // Note that parseDouble() will allow a unary operator!
//	
//			double leftNumber;
//			try { 
//				leftNumber = Double.parseDouble(leftOperand);
//			    }
//			catch(NumberFormatException nfe)
//			    {
//			    System.out.println("Left operand '" + leftOperand + "' is not a proper operand.");
//			    continue;
//		        }
//	
//			double rightNumber;
//			try {
//				rightNumber = Double.parseDouble(rightOperand);
//			    }
//			catch(NumberFormatException nfe)
//			    {
//			    System.out.println("Right operand '" + rightOperand + "' is not a proper operand.");
//			    continue;
//			    }
//		    //System.out.println("Left operand value is " + leftNumber + " and right operand value is " + rightNumber);
//			
//		    boolean mustReenter = false;
//			double result = 0;
//			switch (operator) 
//				{
//				case '+' : result = leftNumber + rightNumber; 	    							break;
//			    case '-' : result = leftNumber - rightNumber; 	    							break;
//			    case '*' : result = leftNumber * rightNumber; 	    							break;
//			    case '/' : result = leftNumber / rightNumber; 	    							break;
//			    case '^' : result = Math.pow(leftNumber,rightNumber);   						break;
//			    case 'r' : if (leftNumber > 0){result = Math.pow( leftNumber,1/rightNumber);	break;} 
//			               else if (rightNumber%2 == 0) {mustReenter = true; 					break;}
//			               else {result =-Math.pow(-leftNumber,1/rightNumber);					break;}
//			    default  : throw new IllegalArgumentException("Operator " + operator + " is not recognized.");
//				}
//			if (mustReenter)
//		    	{
//				System.out.println("Invalid root expression. Reenter expression.");
//				continue;
//		    	}
//			// Note the Math class offers square root and cube root methods, but the form used above allows higher-order roots.
//	        System.out.println(expression + " = "+ result + newline); // show expression and value to user!
//	        previousAnswer = String.valueOf(result);
//	        
//	    	} // end of while(true)
	
	} // end of main
	
	
	
	//instance variables
	private String       newLine                = System.getProperty("line.separator");
	private String       expressionInstructions = "Enter an algebraic expression. e.g. 5^2 [5 squared] or 25r2 [square root of 25]"; 
	private String       previousExpression 	= "";
	private String       previousForXString 	= "";
	private String       previousAnswer     	= "";
	// the document told me to put the instance variables in-between main and the constructor instead of at the top of the class?!?
	
	String expression;		// I had to add this to rectify errors with the section beginning with "String originalExpression = expression;"
	
	//GUI Objects
	private JFrame       window              = new JFrame("EXPRESSION CALCULATOR    Operators are + - * / ^ r      Operands are numbers, e, pi and pa (previous answer) ");
	private JButton      clearButton         = new JButton("Clear");
	private JButton      recallButton        = new JButton("Recall");
	private JTextField   expressionTextField = new JTextField(30);
	private JTextArea    displayTextArea     = new JTextArea();
	private JScrollPane  displayScrollPane   = new JScrollPane(displayTextArea);
	private JTextField   errorTextField      = new JTextField();
	private JLabel       forXLabel           = new JLabel("for x =", SwingConstants.RIGHT);
	private JTextField   forXTextField       = new JTextField(8);
	
	
	
	public Lecture14_GUIcalculator() // CONSTRUCTOR
	{
		// Build the GUI
		// Load the topPanel and then add it to "North"
		JPanel topPanel = new JPanel();
		topPanel.add(clearButton);
		topPanel.add(recallButton);
		topPanel.add(expressionTextField);
		topPanel.add(forXLabel);
		topPanel.add(forXTextField);
		window.getContentPane().add(topPanel,         "North");
		// then add the ScrollPane to "Center" to be our log of entered expressions
		window.getContentPane().add(displayScrollPane,"Center");
		// then add an error message display field at the bottom 
		window.getContentPane().add(errorTextField,   "South");
		 
		// Show window
		displayTextArea.setText(expressionInstructions + newLine);
		window.setSize(800,500);
		window.setVisible(true);
		expressionTextField.requestFocus(); // set cursor in
		
		// Miscellaneous
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    displayTextArea.setEditable(false); // keep cursor out
	    displayTextArea.setFont(new Font("default",Font.BOLD,15));
	    errorTextField.setEditable(false);
	    expressionTextField.setFont(new Font("default",Font.BOLD,15));
	    
	    // Register for event notification
	    expressionTextField.addActionListener(this); // give our address to GUI objects
	    clearButton.addActionListener(this);
	    recallButton.addActionListener(this);
	    forXTextField.addActionListener(this);
		
    } // end of constructor

	
	
	public void actionPerformed(ActionEvent ae) // GUI objects call here
	{
		// watch for button and text field action in the GUI
		errorTextField.setText("");                // clear any error message shown 
		errorTextField.setBackground(Color.white); // for the last expression.

		if (ae.getSource() == clearButton)
		   {
		   forXTextField.setText("");
		   expressionTextField.setText("");
		   expressionTextField.requestFocus();
		   return;
		   }

		if (ae.getSource() == recallButton)
		   {
		   expressionTextField.setText(previousExpression);
		   forXTextField.setText(previousForXString);
		   expressionTextField.requestFocus();
		   return;
		   }

        if ((ae.getSource() == expressionTextField) || (ae.getSource() == forXTextField))  
           {
           expression = expressionTextField.getText().trim().toLowerCase();		// edited line to create variable outside of function
           System.out.println("Expression '" + expression + "' was entered.");	
	 	   String forXString        = forXTextField.getText();
           if (expression.length() == 0) return; // ignore ENTER or blank(s)
           if (expression.contains("="))
              {
		      errorTextField.setText("Expression may not contain '='");	
              errorTextField.setBackground(Color.pink);
		      return; // back to GUI object	
		      }
           }
        
        

        String originalExpression = expression;
        System.out.println(expression);
        
        // do operand substitution for e, pi and pa
        expression = expression.replace("pa", previousAnswer);
        expression = expression.replace("pi", String.valueOf(Math.PI));
        expression = expression.replace("e", String.valueOf(Math.E));
        
        // find the operator!
        char operator = ' ';
        int  operatorOffset = 0;
        int i;
        for (i = 1; i < expression.length(); i++) //(1st char shouldn't be an operator)
        	{                                       // and starting at 1 allows a unary!
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
        	}
        if (operatorOffset == 0) 
        	{
        	errorTextField.setText("operator is missing or is 1st char");
        	errorTextField.setBackground(Color.pink);
        	return;
        	}
        if (i == expression.length()-1) 
       		{
    	    errorTextField.setText("operator cannot be the last character in the expression.");
    	    errorTextField.setBackground(Color.pink);
    	    return;
       		}
        
        
        // find operands!
        String leftOperand = expression.substring(0,operatorOffset).trim();
        String rightOperand = expression.substring(operatorOffset+1).trim();

        // convert operands from String to double 
        // Note that parseDouble() will allow a unary operator!
        double leftNumber;
        try { 
            leftNumber = Double.parseDouble(leftOperand);
            }
        catch(NumberFormatException nfe)
            {
            errorTextField.setText("Left operand '" + leftOperand + "' is not a proper operand.");
            errorTextField.setBackground(Color.pink);
            return;
            }
        double rightNumber;
        try {
            rightNumber = Double.parseDouble(rightOperand);
            }
        catch(NumberFormatException nfe)
            {
            errorTextField.setText("Right operand '" + rightOperand + "'is not a proper operand.");
            errorTextField.setBackground(Color.pink);
            return;
            }
        
        
        
        
        
    } // end of actions performed

} // end of class

