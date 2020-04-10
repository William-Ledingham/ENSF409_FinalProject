package server.controller;

import server.model.*;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class DBController implements Runnable{

	DBManager databaseManager;
	public DBController(BufferedReader socketIn, PrintWriter socketOut)
	{
		databaseManager = new DBManager();
	}
	
	@Override
	public void run() 
	{
		
	}
	
	
	

}
