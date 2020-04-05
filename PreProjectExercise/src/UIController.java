import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Provides data members and methods to create the Controller for this program to manage Student Records
 * @author William Ledingham
 * @version 1.0
 * @since 2020-04-03
 *
 */
public class UIController {

	/**
	 * The View that implements the GUI for the program
	 */
	private UserInterface theView;
	/**
	 * The Model that implements the management of the Student Records.
	 */
	private UIModel theModel;
	
	
	/**
	 * Constructs the Controller with the View and Model for this program.
	 * @param theView UserInterface to manage panels.
	 * @param theModel UIModel to manage the Student Records.
	 */
	public UIController(UserInterface theView, UIModel theModel)
	{
		this.theView = theView;
		this.theModel = theModel;
		
		theView.addCreateTreeListener(new AddTreeButtonListener());
		theView.addBrowseButtonListener(new AddBrowseButtonListener());
		theView.addFindButtonListener(new AddFindButtonListener());
		theView.addInsertButtonListener(new AddInsertButtonListener());
		
		theView.addInsertButtonInsertFrameListener(new AddInsertButtonInsertFrameListener());
	}
	
	/**
	 * Class that acts as the ActionListener for the Add Tree Button.
	 */
	class AddTreeButtonListener implements ActionListener
	{
		/**
		 * Gets file name from user and then makes call to read the file.
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String s = theView.inputDialogBoxFileName();
			parseTextFile(s);
		}
	}
	
	/**
	 * Class that acts as the ActionListener for the Browse Button
	 */
	class AddBrowseButtonListener implements ActionListener
	{
		/**
		 * Prints the BST to the text area on main panel.
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			printBST();
		}
	}

	/**
	 * Class that acts as the ActionListener for the Find Button
	 */
	class AddFindButtonListener implements ActionListener
	{
		/**
		 * Gets student ID from user, searches the record, and then prints information.
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String s = theView.inputDialogBoxStudentID();
			Data data = theModel.find(s);
			theView.outputMessageStudent( data.id, data.faculty, data.major, data.year );
		}
	}
	
	/**
	 * Class that acts as the ActionListener for the Insert Button
	 */
	class AddInsertButtonListener implements ActionListener
	{
		/**
		 * Calls method to open the auxiliary InserPanel.
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			theView.openInsertPanel();
		}
	}
	
	/**
	 * Class that acts as the ActionListener for the Insert Button of the InsertPanel
	 */
	class AddInsertButtonInsertFrameListener implements ActionListener
	{
		@Override
		/**
		 * Gets information from the InsertPanel and sends the Model for insertion.
		 */
		public void actionPerformed(ActionEvent e) 
		{
			InsertPanel insertPanel = theView.getInsertPanel();
			theModel.insertBST(insertPanel.getId(), insertPanel.getFaculty(), insertPanel.getMajor(), insertPanel.getYear());
		}
	}
	
	/**
	 * Opens the text file and adds student records to the Model
	 * @param s Name of the file to read.
	 */
	public void parseTextFile(String s)
	{
		try 
		{
			Scanner sc = new Scanner(new FileInputStream("C:\\Users\\wille\\Documents\\3rd_year_courses\\ENSF_409\\ENSF409_FinalProject\\PreProjectExercise\\src\\" + s));
			
			while(sc.hasNextLine())
			{
				String [] input = sc.nextLine().split("\\s+");
				//System.out.printf("%s  %s  %s  %s\n",input[1].trim(), input[2].trim(), input[3].trim(), input[4].trim());
				theModel.insertBST(input[1].trim(), input[2].trim(), input[3].trim(), input[4].trim());
			}	
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints the BST to the text area of the main panel.
	 */
	public void printBST()
	{
		String s = theModel.printBST();
		theView.printToTextArea(s);
	}
	
}
