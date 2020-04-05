import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Provides data members and methods for a GUI that is suppose to be auxiliary to the main panel.
 * Gives text fields to enter information for another student to be added to the records.
 * @author William Ledingham
 * @version 1.0
 * @since 2020-04-03
 *
 */
public class InsertPanel extends JFrame{

	/**
	 * Title at top of the panel.
	 */
	private JLabel title = new JLabel("Insert a New Node");
	/**
	 * Label for the Student ID TextField.
	 */
	private JLabel idLabel = new JLabel("Enter the Student ID");
	/**
	 * TextField for the new Student ID.
	 */
	private JTextField idTextField = new JTextField(10);
	
	/**
	 * Label for the Student Faculty TextField.
	 */
	private JLabel facultyLabel = new JLabel("Enter Faculty");
	/**
	 * TextField for the new Student Faculty.
	 */
	private JTextField facultyTextField = new JTextField(10);
	
	/**
	 * Label for the Student Major TextField.
	 */
	private JLabel majorTitle = new JLabel("Enter Student's Major");
	/**
	 * TextField for the new Student Major.
	 */
	private JTextField majorTextField = new JTextField(10);
	
	/**
	 * Label for the Student Year TextFile.
	 */
	private JLabel yearTitle = new JLabel("Enter year");
	/**
	 * TextField for the new Student Year.
	 */
	private JTextField yearTextField = new JTextField(10);
	
	/**
	 * Insert Button that starts action to add information to the record.
	 */
	private JButton insertButton = new JButton("Insert");
	/**
	 * Return to Main Button that starts action close this InsertPanel.
	 */
	private JButton returnToMainButton = new JButton("Return to Main Window");
	
	/**
	 * Constructs InserPanel with all components
	 */
	public InsertPanel()
	{
		super("Insert");

		JPanel insertPanel = new JPanel(new BorderLayout());
		setSize(500, 175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel northPanel = new JPanel(new FlowLayout());
		JPanel centerPanel = new JPanel(new FlowLayout());
		JPanel southPanel = new JPanel(new FlowLayout());
		
		northPanel.add(title);
		centerPanel.add(idLabel);
		centerPanel.add(idTextField);
		centerPanel.add(facultyLabel);
		centerPanel.add(facultyTextField);
		centerPanel.add(majorTitle);
		centerPanel.add(majorTextField);
		centerPanel.add(yearTitle);
		centerPanel.add(yearTextField);
		southPanel.add(insertButton);
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
	public void addInsertButtonInsertFrameListener(ActionListener actionListener)
	{
		insertButton.addActionListener(actionListener);
	}
	
	/**
	 * Gets the String in the idTextField.
	 * @return String of the ID.
	 */
	public String getId()
	{
		return idTextField.getText();
	}
	/**
	 * Gets the String in the facultyTextField.
	 * @return String of the Faculty.
	 */
	public String getFaculty()
	{
		return facultyTextField.getText();
	}
	/**
	 * Gets the String in the majorTextField.
	 * @return String of the Major.
	 */
	public String getMajor()
	{
		return majorTextField.getText();
	}
	/**
	 * Gets the String in the yearTextField.
	 * @return String for the Year.
	 */
	public String getYear()
	{
		return yearTextField.getText();
	}
}
