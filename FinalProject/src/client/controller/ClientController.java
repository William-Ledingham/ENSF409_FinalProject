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
 * Main entry point for the client. Contains the {@code static main} method
 * 
 * @author William Ledingham
 * @version 1.0
 * @since 2020-04-10
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
	
	private int studentID;
	
	/**
	 * 
	 * @param theView
	 */
	public ClientController(GUI theView)
	{
		this.theView = theView;
		this.clientComm = new ClientCommunicator();
		
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

	class SearchCatButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			theView.openSearchCatPanel();
		}
	}
	

	class RefreshButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			refreshAction();
		}

	}

	class AddCourseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{

			theView.openAddCourseFrame();
		}
	}
	

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
		
		ClientController con = new ClientController(myApp);
		/*
		BSTModel model = new BSTModel();
		Controller controller = new Controller(myApp, model);
		*/
	}

	/**
	 * Refreshes the two text panes in the GUI window, by calling the server with the associated information.
	 * This method could be broken into two separate methods: refreshCourseCatalogue and refreshStudentRegList.
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
	 * @return
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
	

	class MyWindowListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Closing Window");
			theView.dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class MyAddWindowListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Closing Window");
			theView.getAddCoursePanel().dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
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
