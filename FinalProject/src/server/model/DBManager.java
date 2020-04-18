package server.model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Manages the database of everything, on the server side.
 * Connects to the MySQL server, loads in content, and writes it back to the database.
 * @author Parker
 *
 */
public class DBManager implements SQLCredentials {
	
	private ArrayList <Course> courseList;
	private ArrayList <Student> studentList;
	private CourseCatalogue cat;

	private Connection conn;
	private ResultSet rs;

	/**
	 * Initializes the connection to the MySQL database, as per the connection details
	 * defined in the SQLCredentials interface.
	 */
	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Problem Opening MySQL Connection");
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the connection to the MySQL database server.
	 */
	public void close() {
		try {
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DBManager () {
		// Initialize Connection to MySQL Server
		initializeConnection();
		
		// Read the courses
		readCoursesFromDatabase();
		
		
		studentList = new ArrayList<Student>();
		studentList = readStudentsFromDatabase();
	}

	
	public void readCoursesFromDatabase() {
		// Start with a blank version of the course lists/course catalogue
		courseList = new ArrayList<Course>();
		cat = new CourseCatalogue();
		cat.setCourseList(courseList);
		
		try {
			String query = "SELECT * FROM courses";
			PreparedStatement pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) { // iterate through each row in the table
				Course thisCourse = new Course(rs.getString("name"), rs.getInt("courseNum"));
				for (String offeringStr : rs.getString("sections").split(",")) { // loop through each offering
					thisCourse.addOffering(new CourseOffering(Integer.parseInt(offeringStr), 10)); // 10 is the hardcoded capacity, look away
				}
				courseList.add(thisCourse);
			}
			pStat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
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
