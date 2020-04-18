package server.model;
/**
 * Used to access the SQL Database
 * 
 * @author Parker Link
 * @version 1.0
 * @since 16-05-20
 *
 */
public interface SQLCredentials {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/records";
	
	//  Database credentials
	static final String USERNAME = "user";
	static final String PASSWORD = "user";
	// (for maximum security)

}
