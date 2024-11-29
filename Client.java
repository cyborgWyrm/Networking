import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;


// Client class was largely copied/referenced by instructor code
public class Client {
	
	private final Scanner userInput = new Scanner(System.in);
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket client;

	//Use local host and port 12345
	private final String host = "127.0.0.1";
	private final int portNumber = 12345;
	
	// connect to server and process messages from server
	public void runClient()
	{
		try
		{
			connectToServer();
			getStreams();
			processConnection();
		}
		catch (EOFException e) 
		{
			System.out.println("\nClient terminated connection");
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			closeConnection();
		}
	}
	
	
	// connect to server
	private void connectToServer() throws IOException
	{
		System.out.println("Attempting connection\n");

		// create Socket to make connection to server
		client = new Socket(InetAddress.getByName(host), portNumber);

		// display connection information
		System.out.println("Connected to: " + client.getInetAddress().getHostName());
	}

	// get streams to send and receive data
	private void getStreams() throws IOException
	{
		// set up output stream for objects
		output = new ObjectOutputStream(client.getOutputStream());
		
		output.flush(); // flush output buffer to send header information

		// set up input stream for objects
		input = new ObjectInputStream(client.getInputStream());

		System.out.println("\nGot I/O streams\n");
	}

	// process connection with server
	private void processConnection() throws IOException
	{
		ClientJFrame window = new ClientJFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("waiting on input");
		int[][] matrix1;
		int[][] matrix2;
		
		while (true) {
			if (window.getMatrix(0) != null && window.getMatrix(1) != null) {
				System.out.println("getting");
				matrix1 = window.getMatrix(0);
				matrix2 = window.getMatrix(1);
				break;
			}
		}
		
		// oooookkkay so this is my current iteration of banging my
		// head against the wall. client is never receiving the info
		// from client j frame. but it kinda needs the info. What
		// should i do? What am I supposed to do? Maybe I should look
		// at the instructor examples? he probably has an example with
		// server-client gui. Maybe theyre supposed to be in the same
		// file? Somehow?
		sendData(matrix1);
		sendData(matrix2);
	}

	// close streams and socket
	private void closeConnection()
	{
		System.out.println("\nClosing connection");
		try
		{
			output.close(); // close output stream
			input.close(); // close input stream
			client.close(); // close socket
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// send message to server
	private void sendData(Object message)
	{
		try
		{
			output.writeObject(message);
			output.flush();
		}
		catch (IOException e)
		{
			System.out.println("\nError writing object");
		}
	}
}