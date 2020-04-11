import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Provides data members and methods to create the GUI for the Student Records program. Acts as the View in the MVC architecture.
 * 
 * @author William Ledingham, Parker Link, Michaela Gartner
 * @version 1.0
 * @since 2020-04-03
 *
 */
public class GUI extends JFrame {

	/**
	 * Title at top of the panel.
	 */
	private JLabel programTitle = new JLabel("An Application to Maintain Student Records");
	
	/**
	 * Text Area in the middle of the panel to display the student records.
	 */
	private JTextArea textArea = new JTextArea("");
	/**
	 * Scroll bar for the text area to view all of the student records.
	 */
	private JScrollPane scrollPane = new JScrollPane(textArea);
	
	/**
	 * Insert Button at bottom of panel to start action to insert a new student record.
	 */
	private JButton insertButton = new JButton("Insert");
	/**
	 * Find Button at bottom of panel to start action to search for a record.
	 */
	private JButton findButton = new JButton("Find");
	/**
	 * Browse Button at bottom of panel to start action to refresh the text area with all records.
	 */
	private JButton browseButton = new JButton("Browse");
	/**
	 * Create Tree from File Button at bottom of panel to start action to read textfile for records.
	 */
	private JButton createTreeButton = new JButton("Create Tree from File");
	/**
	 * Auxiliary panel with fields to add a record.
	 */
	private InsertPanel insertPanel;
	
	/**
	 * Constructs the GUI with all components.
	 */
	public GUI()
	{
		super("Student Records");
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		setSize(500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel northPanel = new JPanel(new FlowLayout());
				
		northPanel.add(programTitle);
		
		JPanel centerPanel = new JPanel(new BorderLayout());//new FlowLayout());
		
		scrollPane.setBounds(10, 60, 780, 500);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setEditable(false);
		//mainPanel.add("Center", scrollPane);
		centerPanel.add(scrollPane, BorderLayout.CENTER);

		
		JPanel southPanel = new JPanel(new FlowLayout());
		
		southPanel.add(insertButton);
		southPanel.add(findButton);
		southPanel.add(browseButton);
		southPanel.add(createTreeButton);

		add(mainPanel);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		//mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		insertPanel = new InsertPanel();
		
	}
	
	/**
	 * Opens or makes visible the insertPanel to prompt user for new record information.
	 */
	public void openInsertPanel()
	{
		insertPanel.setVisible(true);
	}
	
	/**
	 * Prompts user with a dialog box for the a textfile name.
	 * @return A String of the name entered.
	 */
	public String inputDialogBoxFileName()
	{
		return JOptionPane.showInputDialog("Enter the file name:");
	}
	
	/**
	 * Sets the text in the text area.
	 * @param s String of text to written to the text area.
	 */
	public void printToTextArea(String s)
	{
		textArea.setText(s);
	}
	
	/**
	 * Prompts user with a dialog box for a students id.
	 * @return A string of the student id entered.
	 */
	public String inputDialogBoxStudentID()
	{
		return JOptionPane.showInputDialog("Please enter the student's id:");
	}
	/**
	 * Shows message box with information of a student.
	 * @param id Students id
	 * @param faculty Students faculty
	 * @param major Students major
	 * @param year Students year
	 */
	public void outputMessageStudent(String id, String faculty, String major, String year)
	{
		JOptionPane.showMessageDialog(this, "id: " + id + "\nfaculty: " + faculty + "\nmajor: " + major + "\nyear: " + year);
	}
	
	/**
	 * Adds Listener to the Create Tree Button.
	 * @param actionListener Action Listener for the button.
	 */
	public void addCreateTreeListener(ActionListener actionListener)
	{
		createTreeButton.addActionListener(actionListener);
	}
	/**
	 * Adds Listener to the Browse Button.
	 * @param actionListener Action Listener for the button.
	 */
	public void addBrowseButtonListener(ActionListener actionListener)
	{
		browseButton.addActionListener(actionListener);
	}
	/**
	 * Adds Listener to the Find Button.
	 * @param actionListener Action Listener for the button.
	 */
	public void addFindButtonListener(ActionListener actionListener)
	{
		findButton.addActionListener(actionListener);
	}
	/**
	 * Adds Listener to the Insert Button.
	 * @param actionListener Action Listener for the button.
	 */
	public void addInsertButtonListener(ActionListener actionListener)
	{
		insertButton.addActionListener(actionListener);
	}
	/**
	 * Sends ActionListener to the insertPanel for use.
	 * @param actionListener Action Listener for the button.
	 */
	public void addInsertButtonInsertFrameListener(ActionListener actionListener)
	{
		insertPanel.addInsertButtonInsertFrameListener(actionListener);
	}
	
	/**
	 * Gets the insertPanel.
	 * @return Returns InsertPanel
	 */
	public InsertPanel getInsertPanel()
	{
		return insertPanel;
	}
	
	
	
	public static void main( String[] args)
	{
		System.out.println("Start Program");
		GUI myApp = new GUI();
		myApp.setVisible(true);
		
		BSTModel model = new BSTModel();
		
		Controller controller = new Controller(myApp, model);
	}
	
}
