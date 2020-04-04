import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UIController {

	private UserInterface theView;
	private UIModel theModel;
	
	
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
	
	
	class AddTreeButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String s = theView.inputDialogBoxFileName();
			parseTextFile(s);
		}
	}
	
	class AddBrowseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			printBST();
		}
		
	}

	class AddFindButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String s = theView.inputDialogBoxStudentID();
			Data data = theModel.find(s);
			theView.outputMessageStudent( data.id, data.faculty, data.major, data.year );
		}
		
	}
	
	class AddInsertButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			theView.openInsertPanel();
		}
		
	}
	
	class AddInsertButtonInsertFrameListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			InsertPanel insertPanel = theView.getInsertPanel();
			theModel.insertBST(insertPanel.getId(), insertPanel.getFaculty(), insertPanel.getMajor(), insertPanel.getYear());
		}
		
	}
	
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
	
	public void printBST()
	{
		String s = theModel.printBST();
		theView.printToTextArea(s);
	}
	
}
