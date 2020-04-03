import java.awt.BorderLayout;

import javax.swing.*;


public class UserInterface extends JFrame{

	
	private JLabel programTitle = new JLabel("An Application to Maintain Student Records");
	private JLabel testLabel = new JLabel("Test Label");
	
	private JTextArea textArea = new JTextArea("");
	private JScrollPane scrollPane = new JScrollPane(textArea);
	
	private JButton insertButton = new JButton("Insert");
	private JButton findButton = new JButton("Find");
	private JButton browseButton = new JButton("Browse");
	private JButton createTreeButton = new JButton("Create Tree from File");
	
	
	public UserInterface()
	{
		super("Student Records");
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		setSize(500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//mainPanel.doLayout();
		
		mainPanel.add("North", programTitle);
		//mainPanel.add("North", testLabel);
		

		scrollPane.setBounds(10, 60, 780, 500);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add("Center", scrollPane);
		
		mainPanel.add(insertButton, BorderLayout.SOUTH);
		mainPanel.add( findButton, BorderLayout.SOUTH);
		//mainPanel.add("South", browseButton);
		//mainPanel.add("South", createTreeButton);

		add(mainPanel);
	}
	
	
	public static void main( String[] args)
	{
		System.out.println("Start Program");
		UserInterface myApp = new UserInterface();
		myApp.setVisible(true);
	}
	
}
