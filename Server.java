import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	private final int portNumber = 12345;
	private final int backlogLimit = 100;
	
	
	// wrote this while looking at teachers reference code
	public void runServer() {
		
		try {
			
			server = new ServerSocket(portNumber, backlogLimit);
			while (true) {
				try
				{
					// this is where most of the work is done
					waitForConnection();
					getStreams();
					processConnection();
				} 
				catch (EOFException e) 
				{
					System.out.println("\nServer terminated connection");
				}
				finally
				{
					closeConnection();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// wait for connection, then display info
	private void waitForConnection() throws IOException {
		System.out.println("Waiting for connection\n");
		
		// this will wait until it finds a connection
		connection = server.accept();
		
		System.out.println("New connection received from: " + connection.getInetAddress().getHostName());
	}
	
	// get streams to send and receive data over
	private void getStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();

		input = new ObjectInputStream(connection.getInputStream());
	}
	
	// these methods were also copied from intructor's server.java, but will
	// require modification.
	private void processConnection() throws IOException {
		try {
			int[][] matrix1 = (int[][]) input.readObject();
			int[][] matrix2 = (int[][]) input.readObject();
			print2dArray(matrix1);
			System.out.println();
			print2dArray(matrix2);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// close streams and socket
	private void closeConnection() 
	{
		System.out.println("\nTerminating connection\n");
		try
		{
			output.close(); // close output stream
			input.close(); // close input stream
			connection.close(); // close socket
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// send message to client
	private void sendData(String message)
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
	
	
	
	// print out a given matrix (taken from matrixaddition)
	public static void print2dArray(int[][] array) {
		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array[row].length; col++) {
				// print out each number
				System.out.printf("%-4d", array[row][col]);
			}
			// new line for every row
			System.out.println();
		}
	}
	
}