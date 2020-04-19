package server.controller;

import server.model.*;
import shared.model.*;

import java.io.*;
/**
 * Implements the communication between the client, server and data base.
 * @version 1.0
 * @since 12-04-2020
 * @author Parker, Michaela, William
 *
 */
public class DBController implements Runnable {
	/**
	 * For sending objects to client (server-to-client).
	 */
	private ObjectOutputStream socketSend = null;
	
	/**
	 * For receiving objects from client (client-to-server).
	 */
	private ObjectInputStream socketReceive = null;
	/**
	 * object to manage sql database
	 */
	DBManager databaseManager;
	/**
	 * creates a new DBController with the specified input stream and output stream
	 * @param socketReceive the stream for receiving data
	 * @param socketSend the stream for sending data
	 */
	public DBController(ObjectInputStream socketReceive, ObjectOutputStream socketSend)
	{
		databaseManager = new DBManager();		
		this.socketReceive = socketReceive;
		this.socketSend = socketSend;
	}
	/**
	 * Executes the communication between the client and server. Waits for a transmission object
	 * and then parses the object and executes the action contained in the object.
	 */
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

			switch(rx.getAction())
			{
			case "AddCourse":
				addCourse(rx);
				break;
			case "RemoveCourse":
				removeCourse(rx);
				break;
			case "SearchCourse":
				searchCourse(rx);
				break;
			case "RefreshCatalogue":
				refreshCatalogue(rx);
				break;
			case "RefreshStudent":
				refreshStudent(rx);
				break;
			case "CheckStudentID":
				checkStudentID(rx);
				break;
			default:
				System.err.println("Unknown Transmission Action: " + rx.getAction());
				break;
			}
		}
	}
	
	/**
	 * Adds course to the students course list by parsing transmission information calling student methods.
	 * Sends response if successful or failed.
	 * @param rx The Transmission object received.
	 */
	private void addCourse(Transmission rx)
	{
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
	/**
	 * Removes course from the students course list by parsing transmission information calling student methods.
	 * Sends response if successful or failed.
	 * @param rx The Transmission object received.
	 */
	private void removeCourse(Transmission rx)
	{
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
	
	/**
	 * Search's the course catalogue for a course specified in the transmission.
	 * @param rx The Transmission object received.
	 */
	private void searchCourse(Transmission rx)
	{
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
	
	/**
	 * Creates new Transmission containing the course catalogue to send in a response to the client.
	 * @param rx Transmission object received.
	 */
	private void refreshCatalogue(Transmission rx)
	{
		System.out.println("Refreshing Course Catalogue");
		databaseManager.readDatabase();
		
		Transmission tx = new Transmission("RespondCatalogue", (Object)databaseManager.getCourseCatalogue().toString());

		sendResponse(tx);
	}
	/**
	 * Creates new Transmission containing courses of student to send in a response to the client.
	 * @param rx Transmission object received.
	 */
	private void refreshStudent(Transmission rx)
	{
		System.out.println("Refreshing Student");
		
		int studentID = (Integer)rx.getContents();
		Student student = databaseManager.getStudentByID(studentID);
		// WARNING: Sending the student does not get updated client-size, because of some weird Serialized duplication issue oof.
		
		Transmission tx = new Transmission("RespondStudent", (Object)student.getAllCourseRegistrations());

		sendResponse(tx);
	}
	
	/**
	 * Checks if student ID in the transmission exists and sends response.
	 * @param rx Transmission object received.
	 */
	private void checkStudentID(Transmission rx)
	{
		System.out.println("Checking Student ID");
		int studentID = (Integer)rx.getContents();
		Student student = databaseManager.getStudentByID(studentID);
		
		boolean result = student != null; // true if valid student, false if invalid student id
		Transmission tx = new Transmission("StudentIDExists", (Object)result);
		
		sendResponse(tx);
	}
	
	
	/**
	 * Tries to send a transmission object to the output stream (client).
	 * @param tx the transmission object to be sent
	 */
	private void sendResponse(Transmission tx) {
		try {
			socketSend.writeObject((Object) tx);
		} catch (IOException e) {
			System.err.println("Error sending response (server-to-client)");
			e.printStackTrace();
		}
	}
	
	

	
	

}
