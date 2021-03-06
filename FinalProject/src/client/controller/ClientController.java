package client.controller;

import client.view.*;
import shared.model.Transmission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main entry point for the client. Contains the {@code static main} method.
 * 
 * @author William Ledingham
 * @version 1.0
 * @since 10-04-2020
 *
 */
public class ClientController {

	/**
	 * The View that implements the GUI for the program
	 */
	private GUI theView;
	
	/**
	 * Connection from client to server. Made in constructor (composition relationship).
	 */
	private ClientCommunicator clientComm;
	/**
	 * the student ID of the student that logged in
	 */
	private int studentID;	
	/**
	 * creates a new client controller with a GUI
	 * @param theView the GUI being used
	 */
	public ClientController(GUI theView)
	{
		this.theView = theView;
		this.clientComm = new ClientCommunicator(theView.inputDialogBoxAddress());

		theView.addChangeUserButtonListener(new ChangeUserButtonListener());
		theView.addSearchCatButtonListener(new SearchCatButtonListener());
		theView.addRefreshButtonListener(new RefreshButtonListener());
		theView.addAddCourseButtonListener(new AddCourseButtonListener());
		theView.addRemoveCourseButtonListener(new RemoveCourseButtonListener());		

		theView.addAddCoursePanelButtonListener(new AddCoursePanelListener());
		theView.addRemoveCoursePanelButtonListener(new RemoveCoursePanelListener());
		theView.addSearchCatPanelButtonListener(new SearchCatPanelListener());
		
		theView.addWindowListener(new MyWindowListener());
		theView.getAddCoursePanel().addWindowListener(new MyAddWindowListener());
		theView.getRemoveCoursePanel().addWindowListener(new MyRemoveWindowListener());
		theView.getSearchCatPanel().addWindowListener(new MySearchWindowListener());
		
		// Repeatedly Prompt for Student ID until valid
		studentID = promptForStudentID();
		
		// Refresh the Screen
		refreshAction();		
	}
	
	/**
	 * Closes all socket connections to server.
	 */
	public void close() {
		clientComm.close();
	}
	
	/**
	 * Class used to listen for changeUserButton Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class ChangeUserButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			studentID = promptForStudentID();
			refreshAction();
		}
	}
	
	/**
	 * Class used to listen for openSearchCatPanel Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class SearchCatButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			theView.openSearchCatPanel();
		}
	}
	
	/**
	 * Class used to listen for refershAction Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class RefreshButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			refreshAction();
		}

	}
	/**
	 * Class used to listen for openAddCourseFrame Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class AddCourseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{

			theView.openAddCourseFrame();
		}
	}
	
	/**
	 * Class used to listen for openRemoveCourseFrame Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class RemoveCourseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			theView.openRemoveCourseFrame();
		}
	}
	

	/**
	 * Class acts as the ActionListener for the Add Course Button of the AddCourse GUI
	 */
	class AddCoursePanelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Adding a new course...");
			AddCoursePanel panel = theView.getAddCoursePanel();
			Transmission transmission = new Transmission("AddCourse", (Object)studentID,
					new ArrayList<String>(Arrays.asList(panel.getFaculty(), panel.getCourseId(), panel.getSection())));
			
			String resultMessage = (String)clientComm.sendTransmission(transmission, true).getContents();
			
			//Close the window when the add course button is hit
			panel.clearFields();
			panel.dispose();
			
			theView.displayMessageBox(resultMessage);
			
			refreshAction();
		}
	}
	/**
	 * Class acts as the ActionListener for the remove Course Button of the RemoveCourse GUI.
	 */
	class RemoveCoursePanelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Removing a course...");
			RemoveCoursePanel panel = theView.getRemoveCoursePanel();
			Transmission transmission = new Transmission("RemoveCourse", (Object)studentID,
					new ArrayList<String>(Arrays.asList(panel.getFaculty(), panel.getCourseId(), panel.getSection())));
			
			String resultMessage = (String)clientComm.sendTransmission(transmission, true).getContents();
			
			//Close the window when the add course button is hit
			panel.clearFields();
			panel.dispose();
			
			theView.displayMessageBox(resultMessage);
			
			refreshAction();
		}
	}
	/**
	 * Class acts as the ActionListener for the search Courses Button of the SearchCourse GUI.
	 */
	class SearchCatPanelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

			System.out.println("Searching for a course...");
			SearchCatPanel panel = theView.getSearchCatPanel();
			Transmission transmission = new Transmission("SearchCourse",
					new ArrayList<String>(Arrays.asList(panel.getFaculty(), panel.getCourseId())));
			
			String resultMessage = (String)clientComm.sendTransmission(transmission, true).getContents();
			
			//Close the window when the add course button is hit
			panel.clearFields();
			panel.dispose();
			
			theView.displayMessageBox(resultMessage);
			
			refreshAction();
		}
	}

	
	public static void main( String[] args)
	{
		System.out.println("Starting Client...");
		GUI myApp = new GUI();
		myApp.setVisible(true);
		
		@SuppressWarnings("unused")
		ClientController con = new ClientController(myApp);
		
	}

	/**
	 * Refreshes the two text panes in the GUI window, by calling the server with the associated information.
	 */
	public void refreshAction() {

		System.out.println("Refreshing...");
		Transmission rx;
		
		// Refresh the Catalogue
		rx = clientComm.sendTransmission(new Transmission("RefreshCatalogue"), true);
		String catalogueStr = (String)rx.getContents();
		theView.printToCourseCatTextArea(catalogueStr); // Display the Catalogue to the User 
														// (in the future, possibly consider some sort of table instead)
		
		// Refresh the Student Registration List
		rx = clientComm.sendTransmission(new Transmission("RefreshStudent", (Object)studentID), true);
		String studentCoursesStr = (String)rx.getContents();
		theView.printToStudentCoursesTextArea(studentCoursesStr); // Display the StudentList to the User	
		
	}

	/**
	 * Prompts the user for their student ID, until they enter a valid one.
	 * @return the valid student ID entered
	 */
	private int promptForStudentID() {
		int studentID = -1;
		
		while (studentID < 0) {
			try {
				studentID = Integer.parseInt(theView.inputDialogBoxStudentID());
			}
			catch (NumberFormatException e) {
				studentID = -1;
				theView.displayMessageBox("Invalid Student ID Number entered. Enter a number, like '1'.");
				continue;
			}
			
			Transmission rx = clientComm.sendTransmission(new Transmission("CheckStudentID", (Object)studentID), true);
			if ((Boolean)rx.getContents() == false) {
				theView.displayMessageBox("Invalid Student ID Number entered. Enter a number, like '1'.");
				studentID = -1;
			}
		}
		
		return studentID;
	}
	
	
	/**
	 * Class used to listen for the windowClosed Call
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class MyWindowListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) { }

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Closing Window");
			clientComm.close();
			theView.dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) { }

		@Override
		public void windowIconified(WindowEvent e) { }

		@Override
		public void windowDeiconified(WindowEvent e) { }

		@Override
		public void windowActivated(WindowEvent e) { }

		@Override
		public void windowDeactivated(WindowEvent e) { }
		
	}
	/**
	 * Class used to listen for windowClosed Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class MyAddWindowListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) { }

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Closing Window");
			theView.getAddCoursePanel().dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) { }

		@Override
		public void windowIconified(WindowEvent e) { }

		@Override
		public void windowDeiconified(WindowEvent e) { }

		@Override
		public void windowActivated(WindowEvent e) { }

		@Override
		public void windowDeactivated(WindowEvent e) { }
		
	}
	/**
	 * Class used to listen for the windowClosed Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class MyRemoveWindowListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) { }

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Closing Window");
			theView.getRemoveCoursePanel().dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) { }

		@Override
		public void windowIconified(WindowEvent e) { }

		@Override
		public void windowDeiconified(WindowEvent e) { }

		@Override
		public void windowActivated(WindowEvent e) { }

		@Override
		public void windowDeactivated(WindowEvent e) { }
		
	}
	/**
	 * Class used to listen for the windowClosed Call.
	 * @author William Ledingham
	 * @version 1.0
	 * @since 10-04-2020
	 *
	 */
	class MySearchWindowListener implements WindowListener{
		@Override
		public void windowOpened(WindowEvent e) { }

		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println("Closing Window");
			theView.getSearchCatPanel().dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) { }

		@Override
		public void windowIconified(WindowEvent e) { }

		@Override
		public void windowDeiconified(WindowEvent e) { }

		@Override
		public void windowActivated(WindowEvent e) { }

		@Override
		public void windowDeactivated(WindowEvent e) { }
		
	}
	
}
