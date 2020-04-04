import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class UserInterface extends JFrame{

	
	
	private JLabel programTitle = new JLabel("An Application to Maintain Student Records");
	
	private JTextArea textArea = new JTextArea("");
	private JScrollPane scrollPane = new JScrollPane(textArea);
	
	private JButton insertButton = new JButton("Insert");
	private JButton findButton = new JButton("Find");
	private JButton browseButton = new JButton("Browse");
	private JButton createTreeButton = new JButton("Create Tree from File");
	
	private InsertPanel insertPanel;
	
	public UserInterface()
	{
		super("Student Records");
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		setSize(500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//mainPanel.doLayout();
		
		JPanel northPanel = new JPanel(new FlowLayout());
		
		
		northPanel.add(programTitle);
		//mainPanel.add("North", testLabel);
		
		scrollPane.setBounds(10, 60, 780, 500);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setEditable(false);
		textArea.setText("Testing the Text Area");
		mainPanel.add("Center", scrollPane);
		
		JPanel southPanel = new JPanel(new FlowLayout());
		
		southPanel.add(insertButton);
		southPanel.add(findButton);
		southPanel.add(browseButton);
		southPanel.add(createTreeButton);

		add(mainPanel);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		insertPanel = new InsertPanel();
		
	}
	
	public void openInsertPanel()
	{

		insertPanel.setVisible(true);
	}
	
	public String inputDialogBoxFileName()
	{
		return JOptionPane.showInputDialog("Enter the file name:");
	}
	
	public void printToTextArea(String s)
	{
		textArea.setText(s);
	}
	
	public String inputDialogBoxStudentID()
	{
		return JOptionPane.showInputDialog("Please enter the student's id:");
	}
	
	public void outputMessageStudent(String id, String faculty, String major, String year)
	{
		JOptionPane.showMessageDialog(this, "id: " + id + "\nfaculty: " + faculty + "\nmajor: " + major + "\nyear: " + year);
	}
	
	
	public void addCreateTreeListener(ActionListener actionListener)
	{
		createTreeButton.addActionListener(actionListener);
	}
	public void addBrowseButtonListener(ActionListener actionListener)
	{
		browseButton.addActionListener(actionListener);
	}
	public void addFindButtonListener(ActionListener actionListener)
	{
		findButton.addActionListener(actionListener);
	}
	public void addInsertButtonListener(ActionListener actionListener)
	{
		insertButton.addActionListener(actionListener);
	}
	public void addInsertButtonInsertFrameListener(ActionListener actionListener)
	{
		insertPanel.addInsertButtonInsertFrameListener(actionListener);
	}
	
	public InsertPanel getInsertPanel()
	{
		return insertPanel;
	}
	
	
	
	public static void main( String[] args)
	{
		System.out.println("Start Program");
		UserInterface myApp = new UserInterface();
		myApp.setVisible(true);
		
		UIModel model = new UIModel();
		
		UIController controller = new UIController(myApp, model);
	}
	
}
