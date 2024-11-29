

public class Server {
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	private final int portNumber = 12345;
	private final int backlogLimit = 100;
	
	
	public void runServer() {
		
		try {
			
			server = new ServerSocket(portNumber, backlogLimit);
			while () {
				
				
			}
			
		}
		catch {
			
			
			
		}
		
		
	}
	
	
}