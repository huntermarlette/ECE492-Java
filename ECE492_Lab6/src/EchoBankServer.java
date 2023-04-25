// ECE492 Java Lab 6 - Bank TellerClient - Spring 2023
// Hunter Marlette
// Student ID # 200289830
// Edits also made for Lab 6

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class EchoBankServer extends UnicastRemoteObject implements TellerServerInterface
{
	
	public static void main(String[] args) throws Exception
       {
       new EchoBankServer(); // load server and call constructor to start 	
       }                     // the rmiregistry and register the server. 

	// Constructor Method
	public EchoBankServer() throws Exception
       	{
		super(); // will throw RE if proxy files are not found
	   	LocateRegistry.createRegistry(1099); // find/start the rmiregistry on this computer
       	System.out.println("rmiregistry is up at " + InetAddress.getLocalHost().getHostAddress()); // no need to show RMI port)
	   	Naming.rebind("TellerServer", this); // register EchoBankServer_Stub with the rmiregistry
	   	System.out.println("EchoBankServer is up and registered as 'TellerServer'.");
       	}

	
	public String createNewAccount(String accountType, String customerName)
		{
		if(customerName.equals("Person,Bad") || customerName.equals("person,bad") || customerName.equals("PERSON,BAD")) 
			return "ERROR: Name is Person,Bad";		// check for "Person,Bad"
		
		//System.out.println("Name: " + customerName);
		System.out.println("Creating a new " + accountType + " account for " + customerName + "With Account number 12345");
		return "Creating a new " + accountType + " account for " + customerName + " with Account number 12345";
// return 12345 as the account number of the new account?!?
		//TellerClient.accountTextField.setText("12345");		// does not work
// I have no clue how exactly you want us to implement this but I took a guess...
		} // end of method

	
	public String showAccount(int accountNumber, String customerName)
		{
		if(customerName.equals("Terrorist,Suspected") || customerName.equals("terrorist,suspected") || customerName.equals("TERRORIST,SUSPECTED") ) 
			return "ERROR: Name is Terrorist,Suspected";		// check for "Terrorist,Suspected"
		
		// if the account number is 0 then return that ALL accounts the start with customerName will be shown, otherwise return that accountNumber xxxx will be shown for customerName.
		if(accountNumber == 0) 
			{
			// return that ALL accounts the start with customerName will be shown
			return "ALL accounts that start with " + customerName + " will be shown";
			} else {
			//System.out.println("Showing account #" + accountNumber + " of " + customerName);
			return "Showing account #" + accountNumber + " of " + customerName;
			}
		} // end of method

	
	public String depositToAccount(int accountNumber, double amount, String customerName) 
		{
		if(amount > 100000) return "ERROR: Amount is > $100,000";		// check for large deposit
		
		System.out.println("Depositing $" + amount + " to account #" + accountNumber + " of " + customerName);
		return "Depositing $" + amount + " to account #" + accountNumber + " of " + customerName;
		} // end of method

	
	public String withdrawFromAccount(int accountNumber, double amount, String customerName) 
		{
		if(amount < 1.00) return "ERROR: Amount is < $1.00";		// check for small withdrawal
		
		System.out.println("Withdrawing $" + amount + " from account #" + accountNumber + " of " + customerName);
    	return "Withdrawing $" + amount + " from account #" + accountNumber + " of " + customerName;
		} // end of method

	
	public String closeOutAccount(int accountNumber, String customerName)
		{
		if(customerName.equals("Bird,Jail") || customerName.equals("bird,jail") || customerName.equals("BIRD,JAIL")) 
			return "ERROR: Name is Bird,Jail";			// check for "Bird,Jail"
		
		System.out.println("Closing out account #" + accountNumber + " of " + customerName + " (balance must be 0)");
		return "Closing out account #" + accountNumber + " of " + customerName + " (balance must be 0)";
		} // end of method
	
} // end of class

