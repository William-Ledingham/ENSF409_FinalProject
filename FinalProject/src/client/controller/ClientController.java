package client.controller;

import client.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
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

	
	public ClientController(GUI theView)
	{
		this.theView = theView;
		
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
		System.out.println("Start Program");
		GUI myApp = new GUI();
		myApp.setVisible(true);
		
		ClientController con = new ClientController(myApp);
		/*
		BSTModel model = new BSTModel();
		Controller controller = new Controller(myApp, model);
		*/
	}
	
}
