import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Provides data members and methods to create the Model for this program to manage student records.
 * Maintains a Binary Search Tree of Students.
 * @author William Ledingham
 * @version 1.0
 * @since 2020-04-03
 *
 */
public class BSTModel {

	/**
	 * Binary Search Tree used to maintain Student Records.
	 */
	private BinSearchTree BST;
	
	/**
	 * Constructs the Model with a new BinSearchTree.
	 */
	public BSTModel()
	{
		BST = new BinSearchTree();
	}
	
	/**
	 * Inserts a new Student to the BinSearchTree.
	 * @param id New Student ID
	 * @param faculty New Student Faculty
	 * @param major New Student Major
	 * @param year New Student Year
	 */
	public void insertBST(String id, String faculty, String major, String year)
	{
		BST.insert(id, faculty, major, year);
	}
	
	/**
	 * Prints the BinSearchTree to a String
	 * @return String of all the information in the BinSearchTree.
	 */
	public String printBST()
	{
		String s = BST.toString();
		return s;
	}
	/**
	 * Sends Student id to BinSearchTree to be searched for.
	 * @param id Student ID to search for.
	 * @return Returns the Data (Student Record) that matches the Student ID.
	 */
	public Data find(String id)
	{
		return BST.find(BST.root, id).data;
	}
	
}
