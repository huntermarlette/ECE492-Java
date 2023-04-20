import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIQuiz2AnswerInterface extends Remote
{
	String getQuiz2Answer(String lastName);		// since I am not in class, I have to assume how my last name was typed
	
} // end of interface
