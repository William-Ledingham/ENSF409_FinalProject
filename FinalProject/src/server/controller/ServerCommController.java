package server.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.model.*;

/**
 * This is the main entry point for the entire server, and runs the server.
 * The server runs on port 9090.
 * 
 * @author Parker
 *
 */
public class ServerCommController {
	
	private Socket socket;
	private ServerSocket serverSocket;

	/**
	 * For sending objects to client (server-to-client).
	 */
	private ObjectOutputStream socketSend = null;
	
	/**
	 * For receiving objects from client (client-to-server).
	 */
	private ObjectInputStream socketReceive = null;
	
	
	private ExecutorService pool;
	private DBController databaseController;
	
	
	
	public ServerCommController(int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void runServer()
	{
		try 
		{

			while(true)
			{

				socket = serverSocket.accept(); 
				System.out.println("Connection accepted by server!");

				//We could also do serialization to straight up send objects back and forth.
				//Or we just do the communication with Strings.
				//socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				//socketOut = new PrintWriter(aSocket.getOutputStream(), true);
				
				// Create connections to server
				try {
					socketSend = new ObjectOutputStream(socket.getOutputStream());
					socketReceive = new ObjectInputStream(socket.getInputStream());
				}
				catch (IOException e) {
					System.err.println("Error opening sockets on Server");
					e.printStackTrace();
				}

				databaseController = new DBController(socketReceive, socketSend);
				pool.execute(databaseController);
				
				
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
		
		close();
	}
	
	/**
	 * Close all socket connections.
	 */
	private void close()
	{
		try 
		{
			socketSend.close();
			socketReceive.close();
		}
		catch(IOException e) {
			System.err.println("Error closing sockets.");
			e.getStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ServerCommController serverComm = new ServerCommController(9090);
		
		serverComm.runServer();
	}
}
