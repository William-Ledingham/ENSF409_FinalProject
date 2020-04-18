package server.model;

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

		// Read the students
		readStudentsFromDatabase();
		
		// Read the registrations
		readRegistrationsFromDatabase();
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
				Course thisCourse = new Course(rs.getInt("id"), rs.getString("name"), rs.getInt("courseNum"));
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
	
	public void readStudentsFromDatabase() {
		// Start with a blank version of the student list
		studentList = new ArrayList<Student>();
		
		try {
			String query = "SELECT * FROM students";
			PreparedStatement pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) { // iterate through each row in the table
				Student thisStudent = new Student(rs.getString("name"), rs.getInt("id"));
				studentList.add(thisStudent);
			}
			pStat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads all the registrations from the 'registrations' table, and adds them to the respective students and course offerings.
	 * If we re-designed the system, we would implement the registration tracking system differently.
	 */
	public void readRegistrationsFromDatabase() {		
		try {
			String query = "SELECT * FROM registrations";
			PreparedStatement pStat = conn.prepareStatement(query);
			rs = pStat.executeQuery();
			while (rs.next()) { // iterate through each row in the table (each row is a registration)
				Student student = getStudentByID(rs.getInt("studentID"));
				CourseOffering offering = cat.searchCat(rs.getInt("courseID")).getCourseOfferingAt(rs.getInt("section"));
				Registration reg = new Registration(student, offering);
				reg.addRegistration();
			}
			pStat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
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
	 * Adds a course offering (and course) to the database. Unused.
	 * @param faculty
	 * @param courseNumber
	 * @param lectNumber
	 */
	public void addCourseOffering(int courseID, String faculty, int courseNumber, int lectNumber) {
		cat.createCourseOffering(new Course(courseID, faculty, courseNumber), lectNumber, 100);
		
		System.out.println("With new course added, we having the following courses: ");
		System.out.println(cat);
	}
	
	

}
