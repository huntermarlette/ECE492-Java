
import java.rmi.Naming;

public class RMIQuiz2AnswerClient 
{

	public RMIQuiz2AnswerClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		RMIQuiz2AnswerInterface server = (RMIQuiz2AnswerInterface) Naming.lookup("rmi//localhost/RMIQuiz2AnswerServerSTUB"); // actual server address should replace "localhost" 
		
		System.out.println("Server _Stub file " + server.getClass().getName() + " has been retrieved from the rmiregistry!");
		
		System.out.println("");
		System.out.println(server.getQuiz2Answer("Marlette"));
	} // end of main

} // end of class
