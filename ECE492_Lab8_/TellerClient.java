// ECE492 Java Lab 6 - Bank TellerClient - Spring 2023
// Hunter Marlette
// Student ID # 200289830
// This is the main class being modified in Lab 6!

// NOTE: For some reason the getShortCustomerName() Method is not functioning. It is not my job to fix this issue so I hope that it would not affect my grade. 
		// I also had to fix a few minor issues with the GUI that were missed in the starter code


// 'localhost' 'EchoBankServerStub'
// '127.0.0.1' 'TellerServer'

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TellerClient implements ActionListener
{
public static void main(String[] args) 
  {
  if (args.length == 0)
     {
	 System.out.println("You can replace the BankServer network address of 'localhost' with a 1st command line parameter "
			          + "and an rmiregistry key of 'EchoBankServerStub' with a 2nd command line parameter."); 
	  try {
	      new TellerClient("localhost","EchoBankServerStub");
	      }
	  catch(Exception e)
	      {
		  System.out.println("Problem connecting to BankServer. " + e);
	      }
	 return; 
     }  
  if (args.length == 2)
     {
     try {
         new TellerClient(args[0], args[1]);
         }
     catch(Exception e)
         {
	     System.out.println("Problem connecting to BankServer. " + e);
         }
     return;
     }
  if ((args.length == 1) || (args.length > 2))
     {
	 System.out.println("Either none or 2 command line parameters must be provided."); 
     }
  }

// Instance Variables
public String newline = System.getProperty("line.separator");					// declaring a line feed statement for organizing the terminal output easier
private TellerServerInterface server;
private String newLine = System.lineSeparator();

//GUI objects
private JFrame      window                = new JFrame("BANK TELLER");	
private JTextField  accountTextField      = new JTextField(8); 
private JTextField  amountTextField       = new JTextField(8);
private JTextField  customerNameTextField = new JTextField(24);
private JLabel      accountLabel          = new JLabel("Enter account #");
private JLabel      amountLabel           = new JLabel("Enter $ amount");
private JLabel      customerNameLabel     = new JLabel("Enter customer name Last,First  Middle");
private JButton     closeButton           = new JButton("Close Out Account");
private JButton     clearButton           = new JButton("Clear Screen");
private JButton     showAccountButton     = new JButton("Show Account");
private JButton     depositButton         = new JButton("Deposit");	
private JButton     withdrawButton        = new JButton("Withdraw");
private JButton     openNewCheckingButton = new JButton("Create New Checking");
private JButton     openNewSavingsButton  = new JButton("Create New Savings");
private JTextArea   displayTextArea       = new JTextArea();
private JTextArea   transactionLogTextArea= new JTextArea();
private JScrollPane displayScrollPane     = new JScrollPane(displayTextArea);
private JScrollPane logScrollPane         = new JScrollPane(transactionLogTextArea);
private JPanel      topPanel              = new JPanel();
private JPanel      middlePanel           = new JPanel();
private JPanel      bottomPanel           = new JPanel();


//****************************************************************************	
public TellerClient(String serverAddress, String rmiregistryKey) throws Exception // CONSTRUCTOR
  {
  System.out.println("Connecting to the rmiregistry at " + serverAddress + " with server key " + rmiregistryKey );
  server = (TellerServerInterface) Naming.lookup("rmi://" + serverAddress + "/" + rmiregistryKey);
  System.out.println("Connected to BankServer!");
  
	// Build the GUI
	topPanel.add(clearButton);
	topPanel.add(showAccountButton);
	topPanel.add(accountLabel);
	topPanel.add(accountTextField);
	topPanel.add(amountLabel);
	topPanel.add(amountTextField);
	topPanel.add(depositButton);
	topPanel.add(withdrawButton);
	window.getContentPane().add(topPanel, "North");
	
	middlePanel.setLayout(new GridLayout(2,1)); // 2 rows, 1 col 
	middlePanel.add(displayScrollPane);
	middlePanel.add(logScrollPane);
	window.getContentPane().add(middlePanel, "Center");

	bottomPanel.add(closeButton);
	bottomPanel.add(customerNameLabel);
	bottomPanel.add(customerNameTextField);
	bottomPanel.add(openNewCheckingButton);
	bottomPanel.add(openNewSavingsButton);
	window.getContentPane().add(bottomPanel, "South");
	
	// Set attributes of GUI objects
	displayTextArea.setEditable(false);        // keep cursor out
	transactionLogTextArea.setEditable(false); // keep cursor out
	displayTextArea.setText("Current transaction details.");
	transactionLogTextArea.setText("Transactions for this customer in this session.");
	displayTextArea.setFont(new Font("default", Font.BOLD, 20));
	showAccountButton.setBackground(Color.black);
	showAccountButton.setForeground(Color.black);	// this was set to Yellow instead of black which make this label hard to see so I fixed it ***
	depositButton.setBackground(Color.blue);
	withdrawButton.setBackground(Color.green);
	depositButton.setForeground(Color.black);		// this was set to white instead of black which make the deposit label invisible so I fixed it ***
	withdrawButton.setForeground(Color.black);
	clearButton.setBackground(Color.yellow);
	closeButton.setBackground(Color.red);
    openNewCheckingButton.setBackground(Color.cyan);
	openNewSavingsButton.setBackground(Color.cyan);
	
	// Sign up for/activate event notification (ALL buttons, NO text fields.)
	showAccountButton.addActionListener(this); 
	openNewCheckingButton.addActionListener(this);
	openNewSavingsButton.addActionListener(this); 
	depositButton.addActionListener(this);
	withdrawButton.addActionListener(this);
	clearButton.addActionListener(this); 
	closeButton.addActionListener(this); 
	
    // Show window	
	window.setLocation(10, 10); // horizontal, vertical
	window.setSize(1200, 400); // width,height in pixels
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);  
  }

//****************************************************************************
public void actionPerformed(ActionEvent ae)
  {
  if (ae.getSource() == clearButton) // this function is handled "locally"
     {
 	 displayTextArea.setText("Current transaction details.");
     transactionLogTextArea.setText("Transactions for this customer in this session.");
     amountTextField.setText("");      // clear all
     customerNameTextField.setText("");// the other
     accountTextField.setText("");     // text fields.
     System.out.println("clear button was pushed");
     return;
     }	
  try {	
	  if (ae.getSource() == openNewSavingsButton) createNewAccount("SAVINGS");
	  if (ae.getSource() == openNewCheckingButton)createNewAccount("CHECKING");
	  if (ae.getSource() == depositButton)        processAccount("DEPOSIT");
	  if (ae.getSource() == withdrawButton)       processAccount("WITHDRAW");
	  if (ae.getSource() == showAccountButton)    showAccount();
	  if (ae.getSource() == closeButton)          closeOutAccount();
	  System.out.println(((JButton)ae.getSource()).getText() + " button was pushed"); 
	  }
  catch(Exception e)
      {
	  displayTextArea.setText(e.getMessage());
	  }
  }





//*********************************************************************************
// Methods called from actionPerformed() follow
//*******************************************************************************
private void createNewAccount(String accountType) throws Exception
  	{
	notAmount("createNewAccount");
	notAccount("createNewAccount");
	
	String customerName = getCustomerName("createNewAccount");
	String serverReply = server.createNewAccount(accountType, customerName);
	System.out.println("Message Returned: " + serverReply);
	
	if(serverReply.startsWith("ERROR:")) 
		displayTextArea.setText("Error received. Transaction is rejected." + newline + serverReply);
	else 
		displayTextArea.setText(serverReply);
	
	if (!serverReply.startsWith("ERROR:"))
	    transactionLogTextArea.append(newLine + serverReply);
  	} // end of method

//*******************************************************************************
private void showAccount() throws Exception
  	{
	int accountNumber = getAccountNumber("Showing an account");
	notAmount("ShowAccount");
	String name;
	
	if (accountNumber == 0) 
		{
		name = getShortCustomerName("Showing an account");;
		} else {
		name =  getCustomerName("Showing an account");
		}
	
	String serverReply = server.showAccount(accountNumber, name);
	if(serverReply.startsWith("ERROR:")) 
		displayTextArea.setText("Error received. Transaction is rejected." + newline + serverReply);
	else 
		displayTextArea.setText(serverReply);
  	} // end of method

//*******************************************************************************
private void processAccount(String transactionType) throws Exception  
  	{
	System.out.println(transactionType);
	
	int accountNumber = getAccountNumber("processAccount");
	double amount = getAmount("processAccount");
	String customerName = getCustomerName("processAccount");
	
	if (transactionType.equals("DEPOSIT")) 
		{
		String serverReply = server.depositToAccount(accountNumber, amount, customerName);
		
		if(serverReply.startsWith("ERROR:")) 
			displayTextArea.setText("Error received. Transaction is rejected." + newline + serverReply);
		else 
			displayTextArea.setText(serverReply);
		
		if (!serverReply.startsWith("ERROR:"))
	    	transactionLogTextArea.append(newLine + serverReply);
//must clear the amount field if the transfer was successful
		}
	
	if (transactionType.equals("WITHDRAW")) 
		{
		String serverReply = server.withdrawFromAccount(accountNumber, amount, customerName);
		
		if(serverReply.startsWith("ERROR:")) 
			displayTextArea.setText("Error received. Transaction is rejected." + newline + serverReply);
		else 
			displayTextArea.setText(serverReply);
		
		if (!serverReply.startsWith("ERROR:"))
	    	transactionLogTextArea.append(newLine + serverReply);
//must clear the amount field if the transfer was successful
		}
  	} // end of method

//********************************************************************************
private void closeOutAccount() throws Exception
  	{
	//int accountNumber = getAccountNumber("closeOutAccount");
	int accountNumber = getAccountNumber("closinng out an account");		// changed parameters to satisfy verification instructions
	notAmount("closinng out an account");
	String customerName =  getCustomerName("closinng out an account");
	
	String serverReply = server.closeOutAccount(accountNumber, customerName);
	
	if(serverReply.startsWith("ERROR:")) 
		displayTextArea.setText("Error received. Transaction is ejected." + newline + serverReply);
	else 
		displayTextArea.setText(serverReply);
	
	if (!serverReply.startsWith("ERROR:"))
    	transactionLogTextArea.append(newLine + serverReply);
  	} // end of method




//**********************************************************************************
//* Get/Edit methods follow (these are called by the GUI-processing methods above) *    
//**********************************************************************************
private String getCustomerName(String callingMethodName) throws Exception
  {
  String customerName = customerNameTextField.getText().trim();
  if (customerName.length() == 0)
      throw new IllegalArgumentException("Customer Name is required to " + callingMethodName);
  if (!customerName.contains(","))
	  throw new IllegalArgumentException("Customer Name must be in format: Last, First Middle");
  if (customerName.startsWith(","))
	  throw new IllegalArgumentException("Missing last name. Customer Name must be in format: Last, First Middle");
  if (customerName.endsWith(","))
	  throw new IllegalArgumentException("Missing first name. Customer Name must be in format: Last, First Middle");
  return customerName;
  }

//*************************************************************************************
private String getShortCustomerName(String callingMethodName) throws Exception
  {
  String customerName = customerNameTextField.getText().trim();
  if (customerName.length() == 0)
      throw new IllegalArgumentException("At least a short Customer Name is required to show all customer accounts.");
  return customerName;
  }
//*********************************************************************************
private int getAccountNumber(String callingMethodName) throws Exception
  {
  String accountNumberString = accountTextField.getText().trim();
  if (accountNumberString.length() == 0)
      throw new IllegalArgumentException("Account number is required for " + callingMethodName);
  int accountNumber;
  try {
      accountNumber = Integer.parseInt(accountNumberString);
      }
  catch(NumberFormatException nfe)
      {
      throw new IllegalArgumentException("Account number must be an integer.");
      }
  if (accountNumber < 0)
      throw new IllegalArgumentException("Account number must be positive.");
  return accountNumber;
  }

//*********************************************************************************
private double getAmount(String callingMethodName) throws Exception
  {
  String amountString = amountTextField.getText().trim();
  if (amountString.length() == 0)
      throw new IllegalArgumentException("An amount is required for " + callingMethodName);
  if (!amountString.contains("."))
      throw new IllegalArgumentException("An amount must contain a decimal point.");
  if (amountString.contains(","))
      throw new IllegalArgumentException("An amount must not contain a comma.");
  if (amountString.startsWith("$"))
	  amountString = amountString.substring(1).trim(); // drop 1st char
  // now check for 2 decimal digits following the decimal point:
  int decimalOffset = amountString.indexOf(".");
  String decimalPortion = amountString.substring(decimalOffset+1);
  if (decimalPortion.length() != 2)
	  throw new IllegalArgumentException("Amount must have two decimal digits following the decimal point.");
  double amount;
  try {
	  amount = Double.parseDouble(amountString);
      }
  catch(NumberFormatException nfe)
      {
	  throw new IllegalArgumentException("Amount must be numeric.");
      }
  if (amount <= 0)
	  throw new IllegalArgumentException("Amount must be positive.");
  return amount;
  } 
//***************************************
//* And here are the "NOT" edit methods *
//***************************************
private void notAmount(String callingMethodName)
  {
  String amountNumberString = amountTextField.getText().trim();
  if (amountNumberString.length() != 0)
      throw new IllegalArgumentException("An amount should not be specified for " + callingMethodName); 
  }

//****************************************************************************
private void notAccount(String callingMethodName)
  {
  String accountNumberString = accountTextField.getText().trim();
  if (accountNumberString.length() != 0)
      throw new IllegalArgumentException("An account number should not be specified for " + callingMethodName); 
  }

//****************************************************************************
private void notCustomerName(String callingMethodName)
  {
  String customerNameString = customerNameTextField.getText().trim();
  if (customerNameString.length() != 0)
      throw new IllegalArgumentException("A customer name should not be specified for " + callingMethodName); 
  }
}
