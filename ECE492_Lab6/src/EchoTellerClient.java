// ECE492 Java Lab 6 - Bank TellerClient - Spring 2023
// Hunter Marlette
// Student ID # 200289830
//From lecture 16

import java.rmi.Naming;

public class EchoTellerClient
{
public static void main(String[] args) throws Exception
	{
	TellerServerInterface server = (TellerServerInterface)Naming.lookup("rmi://localhost/TellerServer"); 
	System.out.println("Server proxy file has been retrieved from the rmiregistry using key 'TellerServer'.");
	System.out.println(server.createNewAccount("CHECKING", "Bubba Smith"));
	System.out.println(server.showAccount(12345, "Bubba Smith"));
	System.out.println(server.depositToAccount(12345, 100, "Bubba Smith"));
	System.out.println(server.withdrawFromAccount(12345, 50, "Bubba Smith"));
	System.out.println(server.closeOutAccount(12345, "Bubba Smith"));
	}
}
