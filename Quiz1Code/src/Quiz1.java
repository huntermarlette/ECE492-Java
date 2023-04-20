import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Quiz1 {

	public Quiz1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception
	{
		
//Question 5.)
		//int[] numbers = new int[] {7,8,9};
		//int numbers[] = {7,8,9};
		//int[] numbers = {7,8,9};					// all ways above work just fine
		//System.out.println(Arrays.toString(numbers));
		
// Question 24.)
		String serverAddress = "192.168.0.1";
		Socket s = new Socket(serverAddress, 1234);
		String[] Data = {args[0], args[1], args[2],args[3]};
		
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(Data);
		
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		String[] reply = (String[]) ois.readObject();
		//String[] reply = {"Smith", "2", "Today8PM", "47"};
		System.out.println(reply[3]);
		
		
	} // end of main

} // end of class
