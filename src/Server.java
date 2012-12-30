import java.net.*;
import java.io.*;

public class Server {
    static int count = 0;

    public static void main(String[] args) throws IOException {
	Integer port = 9000;
	ServerSocket serverSocket = null;
	try {
	    serverSocket = new ServerSocket(port);
	} catch (IOException e) {
	    System.err.format("Port error", port);
	    System.exit(1);
	}
	System.out.println("server: " + "started");

	Socket clientSocket = null;
	try {
	    clientSocket = serverSocket.accept();
	    count += 1;
	    System.out.format("client_count: %d%n", count);
	} catch (IOException e) {
	    System.err.println("Accept error");
	    System.exit(1);
	}

	OutputStream toClient = clientSocket.getOutputStream();
	InputStream fromClient = clientSocket.getInputStream();
	

	byte[] message = new byte[1];
	fromClient.read(message);
	while (message[0] != (byte) 0xFF) {
	    toClient.write(new byte[] {(byte) 0x01});
	    System.out.println("client: " + new String(message));
	    fromClient.read(message);
	}
	System.out.println("server: " + "finished");
	
	//Disconnect client
	toClient.write(new byte[] {(byte) 0xFF});
	
	toClient.close();
	fromClient.close();
	clientSocket.close();
	serverSocket.close();
    }
}