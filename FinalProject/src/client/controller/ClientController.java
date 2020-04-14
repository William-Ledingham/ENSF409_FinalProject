package client.controller;

import client.view.*;
import shared.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		theView.addRemoveCoursePanelButtonListener(new RemoveCoursePanelListener());
		theView.addSearchCatPanelButtonListener(new SearchCatPanelListener());

		// Prompt the User for Student ID
		studentID = Integer.parseInt(theView.inputDialogBoxStudentID());
		//System.out.println("Working for StudentID=" + studentID); // DEBUG
		
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
			System.out.println("Adding a new course!");
			AddCoursePanel panel = theView.getAddCoursePanel();
			Transmission transmission = new Transmission("AddCourse", 
					new ArrayList<String>(Arrays.asList(panel.getFaculty(), panel.getCourseId(), panel.getSection())));
			
					//new Course(theView.getAddCoursePanel().getFaculty(), theView.getAddCoursePanel().getCourseId()));
			clientComm.sendTransmission(transmission, false);
			
			//Close the window when the add course button is hit
			theView.getAddCoursePanel().dispose();
		}
	}
	
	class RemoveCoursePanelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{

			//Close the window when the add course button is hit
			theView.getRemoveCoursePanel().dispose();
		}
	}
	
	class SearchCatPanelListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

			//Close the window when the add course button is hit
			theView.getSearchCatPanel().dispose();
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
	 * This method could be broken into two seperate methods: refreshCourseCatalogue and refreshStudentRegList.
	 */
	public void refreshAction() {

		System.out.println("Refreshing...");
		Transmission rx;
		
		// Refresh the Catalogue
		rx = clientComm.sendTransmission(new Transmission("RefreshCatalogue"), true);
		CourseCatalogue rxCat = (CourseCatalogue)rx.getContents();
		theView.printToCourseCatTextArea(rxCat.toString()); // Display the Catalogue to the User 
															// (in the future, possibly consider some sort of table instead)
		
		// Refresh the Student Registration List
		rx = clientComm.sendTransmission(new Transmission("RefreshStudent", (Object)studentID), true);
		Student student = (Student)rx.getContents();
		String studentListStr = student.getStudentRegList().size() + " Course Registrations for Student: '" + studentID + "' (" + student.getStudentName() + ")\n";
		for (Registration reg : student.getStudentRegList()) {
			//studentListStr += reg.getTheOffering().getTheCourse().toString(); // EXTRA
			studentListStr += reg.getTheOffering().toString();
			studentListStr += "\n"; // RIP CPSC
		}
		theView.printToStudentCoursesTextArea(studentListStr); // Display the StudentList to the User
		
	}
	
}
