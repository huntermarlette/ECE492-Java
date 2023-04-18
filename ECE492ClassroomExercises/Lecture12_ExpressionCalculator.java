
public class Lecture12_ExpressionCalculator
{
public static void main(String[] args)
	{
	if (args.length != 1)
    	{
		System.out.println("Restart. Provide an simple expression (one operator in quotes) as a command line parameter");
		return;
	    }
	String expression = args[0].toLowerCase();
	// substitution for PI symbol
	expression = expression.replaceAll("pi", String.valueOf(Math.PI));
	System.out.println("Expression is now : " + expression);
	
	// Plan: find the single operator and the operands will then be on the sides.
	char operator = ' ';
	int  operatorOffset = 0;
	int i = 0;
	for (i=1; i < expression.length(); i++)
	    {
		if((expression.charAt(i) == '+')
		 ||(expression.charAt(i) == '-')
		 ||(expression.charAt(i) == '*')
		 ||(expression.charAt(i) == '/')
		 ||(expression.charAt(i) == '^')
		 ||(expression.charAt(i) == 'r'))
	     {
		 operatorOffset = i;
		 operator = expression.charAt(i);
		 break;
		 }
	    }
	if ((i == expression.length()) || (i == expression.length()-1))
	   {
		System.out.println("No operator found in '" + expression + "' or 1st or last");
		return;
	   }
	System.out.println("In expression '" + expression + "' the operator " + operator + " was found at offset " + operatorOffset);

	// Now the left operand is the portion of the expression prior to the operator,
	// and the right operand is the portion following the operand.
	String leftOperand = expression.substring(0, operatorOffset).trim(); // not inclusive, and remove leading/trailing blanks
    double leftOperandValue = 0;
	try {leftOperandValue = Double.parseDouble(leftOperand);}
	catch(NumberFormatException nfe)
	     {
		 System.out.println("Left operand " + leftOperand + " is not numeric.");
		 return;
	     }
	String rightOperand = expression.substring(operatorOffset+1).trim(); // not inclusive, and remove leading/trailing blanks
	double rightOperandValue;
	try {rightOperandValue = Double.parseDouble(rightOperand);}
	catch(NumberFormatException nfe)
	     {
		 System.out.println("Right operand " + rightOperand + " is not numeric.");
		 return;
	     }
    System.out.println("Left operand value is " + leftOperandValue + " right operand value is " + rightOperandValue);
    
	// Now we can EVALUATE/COMPUTE the value of the expression!
    double result = 0;
    switch(operator)
          {
          case '+' : result = leftOperandValue + rightOperandValue; break;
          case '-' : result = leftOperandValue - rightOperandValue; break;
          case '*' : result = leftOperandValue * rightOperandValue; break;
          case '/' : result = leftOperandValue / rightOperandValue; break;
          case '^' : result = Math.pow(leftOperandValue,rightOperandValue); break;
          case 'r' : result = Math.pow(leftOperandValue,1/rightOperandValue); break;
          }
    System.out.println(args[0] + " = " + result);     
	} // end main() 
} // end class
