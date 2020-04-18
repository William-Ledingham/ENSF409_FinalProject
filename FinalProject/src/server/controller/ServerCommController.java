package server.controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the main entry point for the entire server, and runs the server.
 * The server runs on port 9090.
 * 
 * @author Parker
 * @version 1.0
 * @since 12-05-2020
 *
 */
public class ServerCommController {
	
	/**
	 * object used to communicate between client and server
	 */
	private Socket socket;
	/**
	 * socket to manage server
	 */
	private ServerSocket serverSocket;

	/**
	 * For sending objects to client (server-to-client).
	 */
	private ObjectOutputStream socketSend = null;
	
	/**
	 * For receiving objects from client (client-to-server).
	 */
	private ObjectInputStream socketReceive = null;
	
	/**
	 * object to manage the thread pool
	 */
	private ExecutorService pool;
	/**
	 * object containing the run method to be executed by the server
	 */
	private DBController databaseController;
	/**
	 * creates a new serverCommController with the specified port
	 * @param port the port to create the connection at
	 */
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
	/**
	 * method to begin running the server
	 */
	public void runServer()
	{
		try 
		{
			while(true)
			{

				socket = serverSocket.accept(); 
				System.out.println("Connection accepted by server!");
				
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
		System.out.println("Starting server...");
		
		ServerCommController serverComm = new ServerCommController(9090);
		
		serverComm.runServer();
	}
}
