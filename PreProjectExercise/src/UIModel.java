import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class UIModel {

	private BinSearchTree BST;
	
	public UIModel()
	{
		BST = new BinSearchTree();
	}
	
	
	public void insertBST(String id, String faculty, String major, String year)
	{
		BST.insert(id, faculty, major, year);
	}
	
	public String printBST()
	{
		String s = BST.toString();
		return s;
	}
	public Data find(String id)
	{
		return BST.find(BST.root, id).data;
	}
	
}
