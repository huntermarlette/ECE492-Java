import java.rmi.Remote;
import java.rmi.RemoteException;
// Feb 23, 2022

public interface Lecture14_TellerServerInterface extends Remote
{
String createNewAccount(String  accountType, // CHECKING or SAVINGS
                        String  customerName) throws RemoteException;

String showAccount(int    accountNumber,     // if 0, show all accounts
                   String customerName)      throws RemoteException;

String  depositToAccount(int    accountNumber,
                        double amount,
                        String customerName)  throws RemoteException;

String withdrawFromAccount(int    accountNumber,
                           double amount,
                           String customerName)  throws RemoteException;

String closeOutAccount(int    accountNumber, // BALANCE must be 0
           String customerName) throws RemoteException;
}
