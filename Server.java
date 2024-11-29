

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
		catch {
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
	
	private void processConnection throws IOException {
		
		
		
		
	}
	
	
}