import java.rmi.Naming;
// Feb 23, 2022

public class Lecture14_EchoTellerClient
{
public static void main(String[] args) throws Exception
	{
	Lecture14_TellerServerInterface server = (Lecture14_TellerServerInterface)Naming.lookup("rmi://localhost/TellerServer"); 
	System.out.println("Server proxy file has been retrieved from the rmiregistry using key 'TellerServer'.");
	System.out.println(server.createNewAccount("CHECKING", "Bubba Smith"));
	System.out.println(server.showAccount(12345, "Bubba Smith"));
	System.out.println(server.depositToAccount(12345, 100, "Bubba Smith"));
	System.out.println(server.withdrawFromAccount(12345, 50, "Bubba Smith"));
	System.out.println(server.closeOutAccount(12345, "Bubba Smith"));
	}
}
