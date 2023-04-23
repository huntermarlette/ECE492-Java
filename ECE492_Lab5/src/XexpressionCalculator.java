// ECE492 Java Lab 5 - X Expression Calculator - Spring 2023
// Hunter Marlette
// Student ID # 200289830


import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;


public class XexpressionCalculator implements ActionListener
{

	public static void main(String[] args) //throws Exception
	{
		String newline = System.getProperty("line.separator");					// declaring a line feed statement for organizing the terminal output easier
		
		try{new XexpressionCalculator();}
		catch(Exception e){System.out.println(e);} // print the Exception object as the error msg.
	
	} // end of main
	
	
	
	//instance variables
	private String       newLine                = System.getProperty("line.separator");
	private String       expressionInstructions = "Enter an algebraic expression. e.g. 5^2 [5 squared] or 25r2 [square root of 25]"; 
	private String       previousExpression 	= "";
	private String       previousForXString 	= "";
	private String       previousAnswer     	= "";
	// the document told me to put the instance variables in-between main and the constructor instead of at the top of the class?!?
	
	String expression;		// I had to add this to rectify errors with the section beginning with "String originalExpression = expression;"
	String forXString;		// defined to prevent errors in step 14 code and later in lab 5
	
	//GUI Objects
	private JFrame       window              = new JFrame("X Expression Calculator    Operators are + - * / ^ r     Operands are numbers, e, pi, and pa (previous answer), and X ");
	private JButton      clearButton         = new JButton("Clear");
	private JButton      recallButton        = new JButton("Recall");
	private JTextField   expressionTextField = new JTextField(30);
	private JTextArea    displayTextArea     = new JTextArea();
	private JScrollPane  displayScrollPane   = new JScrollPane(displayTextArea);
	private JTextField   errorTextField      = new JTextField();
	private JLabel       forXLabel           = new JLabel("for x =", SwingConstants.RIGHT);
	private JTextField   forXTextField       = new JTextField(8);
	
	

	public XexpressionCalculator() // CONSTRUCTOR
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
		errorTextField.setText("");               	 							// clear any error message shown 
		errorTextField.setBackground(Color.white); 								// for the last expression.

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
	 	   forXString        = forXTextField.getText();							// removed string declaration here and moved it to the instance variables...
           if (expression.length() == 0) return; 								// ignore ENTER or blank(s)
           if (expression.contains("="))
              {
		      errorTextField.setText("Expression may not contain '='");	
              errorTextField.setBackground(Color.pink);
		      return; // back to GUI object	
		      }
           }
        // added in lab 5 step 3:
        if (expression.contains("x") && (forXString.length()==0)) 
        	{
        	errorTextField.setText("Expression contains x but xValue is not provided.");	
        	   errorTextField.setBackground(Color.pink);
        	   return;
        	}
        if (!expression.contains("x") && (forXString.length() > 0)) 
        	{
        	errorTextField.setText("xValue is provided but expression does not contain x.");	
        	   errorTextField.setBackground(Color.pink);
        	   return;
        	}

        String originalExpression = expression;
        
        // Lab 5 Step 4:
        // do x operand substitution in expression
        expression = expression.replace("x", forXString);
        System.out.println("Expression with x replaced is " + expression);

        // Fix invalid unary operators caused by x substitution
        // Fix left operand
        if (expression.startsWith("--")) expression = expression.substring(2);
        // Fix right operand
        expression = expression.replace("---" ,"-");
        expression = expression.replace("- --","-");
        System.out.println("Expression with unary operators fixed is " + expression);
        
        
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
        
        // calculate the value of the expression
        boolean mustReenter = false;
        double result = 0;
        switch (operator)
        {
        case '+' : result = leftNumber + rightNumber;         break;
        case '-' : result = leftNumber - rightNumber;         break;
        case '*' : result = leftNumber * rightNumber;         break;
        case '/' : result = leftNumber / rightNumber;         break;
        case '^' : result = Math.pow(leftNumber,rightNumber); break;
        case 'r' : if (leftNumber > 0){result = Math.pow( leftNumber,1/rightNumber);break;} 
                   else if (rightNumber%2 == 0) {mustReenter = true; break;}
                   else {result =-Math.pow(-leftNumber,1/rightNumber);break;}
        }
        // Note the Math class offers square root and cube root methods, but the form used above allows higher-order roots. 
        if (mustReenter)
           {
           errorTextField.setText("Invalid root expression. Reenter expression.");
           errorTextField.setBackground(Color.pink);
           return;
           }
     	     	
        // At this point entered expression and x values are valid.
        // (no exception was thrown!) so save them for recall.
        previousExpression = expression;
        previousForXString = forXString;
        previousAnswer     = String.valueOf(result);

        // Show the expression and it's value in the log area on GUI
        displayTextArea.append(newLine + originalExpression + " = " + result);
        System.out.println(originalExpression + " = " + result);		// I added this just to help with debugging and visuals

        if (originalExpression.contains("x") || originalExpression.contains("X"))
           {
           displayTextArea.append(" for x = " + forXString);
           }

        // scroll down the log area		  
        displayTextArea.setCaretPosition(displayTextArea.getDocument().getLength()); // scroll to bottom
        expressionTextField.setText(""); 		// clear expression
        forXTextField.setText("");       		// clear x field
        expressionTextField.requestFocus();		// set cursor in.  
        return;
        
    } // end of actions performed

} // end of class

