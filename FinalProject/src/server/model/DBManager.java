package server.model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.controller.IDBCredentials;

/**
 * Manages the database of everything, on the server side.
 * @author Parker
 *
 */
public class DBManager implements IDBCredentials {
	
	private ArrayList <Course> courseList;
	private ArrayList <Student> studentList;
	private CourseCatalogue cat;

	//database
	private Connection conn;
	private ResultSet rs;
	
	public DBManager () {
		initializeDatabaseConnection();
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		cat = new CourseCatalogue();
		cat.setCourseList(courseList);
		cat = readCoursesFromDatabase();
		studentList = readStudentsFromDatabase();
	}
	
	
	public void initializeDatabaseConnection()
	{
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}
	}
	
	public void closeDatabase() {
		try {
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Student readStudent()
	{
		
	}

	
	public CourseCatalogue readCoursesFromDatabase() {
		ObjectInputStream input = null;
		String fileName = "courseCatalogue.ser";
		CourseCatalogue courseCat = null;		        
		try
		{
			input = new ObjectInputStream(new FileInputStream( fileName ) );
		}
		catch ( IOException ioException )
		{
			System.err.println( "Error opening file." );
		}		        
		try
		{
			while ( true )
		    {
				courseCat = (CourseCatalogue)input.readObject();
				// System.out.println(courseCat.toString()); // DEBUG 
		     }
		 }catch(EOFException e) {
			 System.out.println("Done reading file (course catalogue)");
		 }
		catch (Exception e) {
			System.err.println("Other Error");
			e.printStackTrace();
		}
		return courseCat;
		
	}
	public void WriteCourseCatalogue() {
		
	}
	public ArrayList<Student> readStudentsFromDatabase() {
		ObjectInputStream input = null;
		String fileName = "studentList.ser";
		ArrayList <Student> studentList = new ArrayList<Student>();	        
		//StudentList list = new StudentList();
		Student s;
		
		try
		{
			input = new ObjectInputStream(new FileInputStream( fileName ) );
		 }catch(EOFException e) {
			 System.out.println("Done reading file (student list)");
		 }
		catch (Exception e) {
			System.err.println("Other Error");
			e.printStackTrace();
		}	        
		try
		{
			while ( true )
		    {
				
				s = (Student)input.readObject();
		       // System.out.println(s.toString()); // DEBUG
		        studentList.add(s);
		     }   
		 }catch(Exception e) {
			 System.out.println("Done reading file (student list)");
		 }
		return studentList;
		
	}
	
	/**
	 * Searches the students list for a single student, but their ID.
	 * @param studentID ID of Student
	 * @return The student, or null if not found
	 */
	public Student getStudentByID(int studentID)
	{
		for(Student student : studentList)
		{
			if(studentID == student.getStudentId())
			{
				return student;
			}
		}
		return null;
	}
	
	/**
	 * Gets the course catalogue
	 * @return the CourseCatalogue
	 */
	public CourseCatalogue getCourseCatalogue()
	{
		return cat;
	}
	
	/**
	 * Adds a course offering (and course) to the database.
	 * @param faculty
	 * @param courseNumber
	 * @param lectNumber
	 */
	public void addCourseOffering(String faculty, int courseNumber, int lectNumber) {
		cat.createCourseOffering(new Course(faculty, courseNumber), lectNumber, 100);
		
		System.out.println("With new course added, we having the following courses: ");
		System.out.println(cat);
	}
	
	

}
