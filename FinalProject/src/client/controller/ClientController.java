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
			clientComm.sendTransmission(transmission);
			
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
	
}
