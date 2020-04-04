import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InsertPanel extends JFrame{

	private JLabel title = new JLabel("Insert a New Node");
	private JLabel idLabel = new JLabel("Enter the Student ID");
	private JTextField idTextField = new JTextField(10);
	
	private JLabel facultyLabel = new JLabel("Enter Faculty");
	private JTextField facultyTextField = new JTextField(10);
	
	private JLabel majorTitle = new JLabel("Enter Student's Major");
	private JTextField majorTextField = new JTextField(10);
	
	private JLabel yearTitle = new JLabel("Enter year");
	private JTextField yearTextField = new JTextField(10);
	
	private JButton insertButton = new JButton("Insert");
	private JButton returnToMainButton = new JButton("Return to Main Window");
	
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
	
	
	public void addInsertButtonInsertFrameListener(ActionListener actionListener)
	{
		insertButton.addActionListener(actionListener);
	}
	
	public String getId()
	{
		return idTextField.getText();
	}
	
	public String getFaculty()
	{
		return facultyTextField.getText();
	}
	
	public String getMajor()
	{
		return majorTextField.getText();
	}
	
	public String getYear()
	{
		return yearTextField.getText();
	}
}
