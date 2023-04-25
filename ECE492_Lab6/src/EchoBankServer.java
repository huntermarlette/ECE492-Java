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
// add check for "Person,Bad" here
		System.out.println("Name: " + customerName);
		return "Creating a new " + accountType + " account for " + customerName;
// return 12345 as the account number of the new account?!?
		}

	
	public String showAccount(int accountNumber, String customerName)
		{
// add check for "Terrorist,Suspected" here
		System.out.println("Name: " + customerName);
		return "Showing account #" + accountNumber + " of " + customerName;
// if the account number is 0 then return that ALL accounts the start with customerName will be shown, otherwise return that accountNumber xxxx will be shown for customerName.
		}

	
	public String depositToAccount(int accountNumber, double amount, String customerName) 
		{
// add check for large deposit here
		System.out.println("Amount: " + amount);
		return "Depositing $" + amount + " to account #" + accountNumber + " of " + customerName;
		}

	
	public String withdrawFromAccount(int accountNumber, double amount, String customerName) 
		{
// add check for small withdrawal here
		System.out.println("Amount: " + amount);
    	return "Withdrawing $" + amount + " from account #" + accountNumber + " of " + customerName;
		}

	
	public String closeOutAccount(int accountNumber, String customerName)
		{
// add check for "Bird,Jail" here
		System.out.println("Name: " + customerName);
		return "Closing out account #" + accountNumber + " of " + customerName + " (balance must be 0)";
		}
	
	
}

