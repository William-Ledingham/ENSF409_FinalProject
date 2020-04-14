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
			
			if (rx.getAction().equals("AddCourse")) {
				System.out.println("Options Length: " + rx.getOptions().size() + ": " + rx.getOptions().get(0) + "|" + rx.getOptions().get(1));
				
				databaseManager.addCourseOffering(rx.getOptions().get(0), 
						Integer.parseInt(rx.getOptions().get(1)), Integer.parseInt(rx.getOptions().get(2)));
			}
			
			if (rx.getAction().equals("RefreshCatalogue")) {
				System.out.println("Refreshing Course Catalogue");
				
				Transmission tx = new Transmission("RespondCatalogue", (Object)databaseManager.getCourseCatalogue());
				
				try {
					socketSend.writeObject((Object) tx);
				} catch (IOException e) {
					System.err.println("Error sending response (server-to-client)");
					e.printStackTrace();
				}
			}
		}
	}
	
	
	

}
