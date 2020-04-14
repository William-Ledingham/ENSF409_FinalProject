package client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Provides data members and methods to create a JFrame that is meant to be auxiliary to the main GUI frame.
 * Provides TextFields to enter information to search the course catalogue for a course.
 * 
 * @author William Ledingham
 * @version 1.0
 * @since 2020-04-10
 *
 */
public class SearchCatPanel extends JFrame {

	
	/**
	 * Serial ID for Serialization, never used here.
	 */
	private static final long serialVersionUID = 722563940237963896L;

	/**
	 * Title at top of the panel.
	 */
	private JLabel title = new JLabel("Search Course Catalogue");
	
	/**
	 * Label for the Faculty TextField.
	 */
	private JLabel facultyLabel = new JLabel("Faculty:");
	/**
	 * TextField for the Faculty.
	 */
	private JTextField facultyTextField = new JTextField(10);
	/**
	 * Label for the course id TextField.
	 */
	private JLabel courseIdLabel = new JLabel("Course ID:");
	/**
	 * TextField to enter the course id.
	 */
	private JTextField courseIdTextField = new JTextField(10);
	/**
	 * Button to start searching for a course using the information provided.
	 */
	private JButton searchButton = new JButton("Search");
	/**
	 * Return to Main Button that starts action close this InsertPanel.
	 */
	private JButton returnToMainButton = new JButton("Return to Main Window");
	
	/**
	 * Constructs InserPanel with all components
	 */
	public SearchCatPanel()
	{
		super("Search Course Catalogue");

		JPanel insertPanel = new JPanel(new BorderLayout());
		setSize(400, 175);
		
		JPanel northPanel = new JPanel(new FlowLayout());
		JPanel centerPanel = new JPanel(new FlowLayout());
		JPanel southPanel = new JPanel(new FlowLayout());
		
		northPanel.add(title);
		centerPanel.add(facultyLabel);
		centerPanel.add(facultyTextField);
		centerPanel.add(courseIdLabel);
		centerPanel.add(courseIdTextField);

		
		southPanel.add(searchButton);
		southPanel.add(returnToMainButton);
		
		add(insertPanel);
		insertPanel.add(northPanel, BorderLayout.NORTH);
		insertPanel.add(centerPanel, BorderLayout.CENTER);
		insertPanel.add(southPanel, BorderLayout.SOUTH);
		
		
		returnToMainButton.addActionListener((ActionEvent e) -> {
			
			this.dispose();
			
		});
		
	}
	
	/**
	 * Adds Listener to the Insert Button.
	 * @param actionListener Action Listener for the button.
	 */
	public void addSearchPanelActionListener(ActionListener actionListener)
	{
		searchButton.addActionListener(actionListener);
	}

	/**
	 * Gets the faculty entered in the faculty TextField.
	 * @return String of text in the faculty TextField.
	 */
	public String getFaculty()
	{
		return facultyTextField.getText();
	}
	/**
	 * Gets the course id entered in the course id TextField.
	 * @return Integer of the course id entered in the TextField.
	 */
	public String getCourseId()
	{
		return courseIdTextField.getText();
	}

	public void clearFields() {
		facultyTextField.setText("");
		courseIdTextField.setText("");
	}
	
	
}
