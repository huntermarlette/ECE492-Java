// ECE492 Java Lab 6 - Bank TellerClient - Spring 2023
// Hunter Marlette
// Student ID # 200289830
//From lecture 16

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TellerServerInterface extends Remote
{
	String createNewAccount(String  accountType, String  customerName) throws RemoteException; 					// CHECKING or SAVINGS

	String showAccount(int accountNumber, String customerName) throws RemoteException; 							// if 0, show all accounts

	String  depositToAccount(int accountNumber, double amount, String customerName) throws RemoteException;

	String withdrawFromAccount(int accountNumber, double amount, String customerName) throws RemoteException;

	String closeOutAccount(int accountNumber, String customerName) throws RemoteException; 						// BALANCE must be 0

}
