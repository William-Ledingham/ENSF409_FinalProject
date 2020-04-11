package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.model.*;

public class ServerCommController {
	
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
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

				aSocket = serverSocket.accept(); 
				System.out.println("Connection accepted by server!");

				//We could also do serialization to straight up send objects back and forth.
				//Or we just do the communication with Strings.
				socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter(aSocket.getOutputStream(), true);

				databaseController = new DBController(socketIn, socketOut);
				pool.execute(databaseController);
				
				
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
		
		closeConnection();
	}
	
	private void closeConnection()
	{
		try 
		{
			socketIn.close();
			socketOut.close();
		}
		catch(IOException e) {
			e.getStackTrace();
		}
	}
}
