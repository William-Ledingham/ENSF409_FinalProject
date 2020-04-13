package server.controller;

import server.model.*;
import shared.model.*;

import java.io.*;

public class DBController implements Runnable {


	/**
	 * For sending objects to client (server-to-client).
	 */
	private ObjectOutputStream socketSend = null;
	
	/**
	 * For receiving objects from client (client-to-server).
	 */
	private ObjectInputStream socketReceive = null;
	
	DBManager databaseManager;
	public DBController(ObjectInputStream socketReceive, ObjectOutputStream socketSend)
	{
		databaseManager = new DBManager();
		
		this.socketReceive = socketReceive;
		this.socketSend = socketSend;
	}
	
	@Override
	public void run() 
	{
		while (true) {
			Transmission rx = null;
			try {
				rx = (Transmission) socketReceive.readObject();
			} catch (ClassNotFoundException | IOException e) {
				System.err.println("Error receiving from client.");
				e.printStackTrace();
			}
		}
	}
	
	
	

}
