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
			}
			catch (EOFException e) {
				System.out.println("Client Disconnected...");
				break; // or "return;"
			}
			catch (ClassNotFoundException | IOException e) {
				System.err.println("Error receiving from client.");
				e.printStackTrace();
			}
			
			if (rx.getAction().equals("AddCourse")) {
				System.out.println("Adding new course to student...");
				String message = "";
				
				String courseName = rx.getOptions().get(0);
				int courseNum = 0, courseSec = 0;
				try {
					courseNum = Integer.parseInt(rx.getOptions().get(1));
					courseSec = Integer.parseInt(rx.getOptions().get(2));
				}
				catch (NumberFormatException e) {
					message = "Invalid Course Number/Section Number. Please provide numbers.";
				}
				if (message.equals("")) {
					int studentID = (Integer)rx.getContents();
					
					// Add the Course
					Student student = databaseManager.getStudentByID(studentID);
					message = databaseManager.registerStudentInCourse(student, databaseManager.getCourseCatalogue(), courseName, courseNum, courseSec);
				}
				
				Transmission tx = new Transmission("Message", (Object)message);

				sendResponse(tx);
			}
			
			else if (rx.getAction().equals("RemoveCourse")) {
				System.out.println("Delete course from student...");
				String message = "";
				
				String courseName = rx.getOptions().get(0);
				int courseNum = 0, courseSec = 0;
				try {
					courseNum = Integer.parseInt(rx.getOptions().get(1));
					courseSec = Integer.parseInt(rx.getOptions().get(2));
				}
				catch (NumberFormatException e) {
					message = "Invalid Course Number/Section Number. Please provide numbers.";
				}
				if (message.equals("")) {
					int studentID = (Integer)rx.getContents();
					
					// Add the Course
					Student student = databaseManager.getStudentByID(studentID);
					message = databaseManager.deleteStudentFromCourse(student, databaseManager.getCourseCatalogue(), courseName, courseNum, courseSec);
				}
				
				Transmission tx = new Transmission("Message", (Object)message);

				sendResponse(tx);
			}

			else if (rx.getAction().equals("SearchCourse")) {
				System.out.println("Searching for a course...");
				String message = "";
				
				String courseName = rx.getOptions().get(0);
				int courseNum = 0;
				try {
					courseNum = Integer.parseInt(rx.getOptions().get(1));
				}
				catch (NumberFormatException e) {
					message = "Invalid Course Number. Please provide numbers.";
				}
				if (message.equals("")) {
					Course courseFound = databaseManager.getCourseCatalogue().searchCat(courseName, courseNum);
					if (courseFound == null) {
						message = "Course Not Found, please search again.";
					}
					else {
						message = "Search Results:\n\n" + courseFound.toString();
					}
				}
				
				Transmission tx = new Transmission("Message", (Object)message);

				sendResponse(tx);
			}
			
			else if (rx.getAction().equals("RefreshCatalogue")) {
				System.out.println("Refreshing Course Catalogue");
				databaseManager.readDatabase();
				
				Transmission tx = new Transmission("RespondCatalogue", (Object)databaseManager.getCourseCatalogue().toString());

				sendResponse(tx);
			}
			
			else if (rx.getAction().equals("RefreshStudent")) {
				System.out.println("Refreshing Student");
				
				int studentID = (Integer)rx.getContents();
				Student student = databaseManager.getStudentByID(studentID);
				// WARNING: Sending the student does not get updated client-size, because of some weird Serialized duplication issue oof.
				
				Transmission tx = new Transmission("RespondStudent", (Object)student.getAllCourseRegistrations());

				sendResponse(tx);
			}
			
			else if (rx.getAction().equals("CheckStudentID")) {
				System.out.println("Checking Student ID");
				int studentID = (Integer)rx.getContents();
				Student student = databaseManager.getStudentByID(studentID);
				
				boolean result = student != null; // true if valid student, false if invalid student id
				Transmission tx = new Transmission("StudentIDExists", (Object)result);
				
				sendResponse(tx);
			}
			
			else {
				System.err.println("Unknown Transmission Action: " + rx.getAction());
			}
		}
	}
	
	public void sendResponse(Transmission tx) {
		try {
			socketSend.writeObject((Object) tx);
		} catch (IOException e) {
			System.err.println("Error sending response (server-to-client)");
			e.printStackTrace();
		}
	}
	
	
	

}
