package shared.model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Represents a single transmission from client-to-server, or from server-to-client. 
 * Each transmission has an action, and contains the associated object(s) used for the transmission.
 * 
 * @author Parker Link
 * @version 1.0
 * @since 13-03-2020
 *
 */
public class Transmission implements Serializable {
	/**
	 * The obejct serialization ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The action the transmission is requesting. Supported actions include:
	 * - CheckStudentID: contents = (Integer)studentID
	 * - StudentIDExists: contents = (Boolean)StudentIDExists
	 * 
	 * - AddStudent
	 * - AddCourse: contents = (Integer)studentID, options specified in options docstring
	 * - RemoveCourse: same as AddCourse
	 * 
	 * - RefreshCatalogue: empty
	 * - RespondCatalogue: contents = String
	 * - RefreshStudent: contents = (Integer)studentID
	 * - RespondStudent: contents = String
	 * 
	 * - SearchCourse: options specified in options docstring
	 * 
	 * - Success (return that it was successful). Unused Right Now
	 * - Message: contents = (String)Success/Failure Message
	 */
	private String action;
	
	/**
	 * Contents of the transmission. This can be a Student, Class, ArrayList, etc.
	 */
	private Object contents;
	
	/**
	 * Options for the action, as any types of Strings.
	 * - For AddCourse/RemoveCourse action: [faculty, courseNumber, lect number]
	 * - For SearchCourse action: [faculty, courseNumber]
	 */
	private ArrayList<String> options;

	/**
	 * Creates a new transmission object with the given action, contents, and options
	 * @param action the action the transmission is requesting
	 * @param contents the contents of the transmission
	 * @param options the options for the action
	 */
	public Transmission(String action, Object contents, ArrayList<String> options) {
		this.action = action;
		this.contents = contents;
		this.options = options;
	}
	/**
	 * Creates a new transmission object with the given action
	 * @param action the action the transmission is requesting
	 */
	public Transmission(String action) {
		this.action = action;
		this.contents = null;
		this.options = null;
	}
	/**
	 * Creates a new transmission with the given action and options
	 * @param action the action the transmission is requesting
	 * @param options the options for the action
	 */
	public Transmission(String action, ArrayList<String> options) {
		this.action = action;
		this.contents = null;
		this.options = options;
	}
	/**
	 * Creates a new transmission with a given action and contents
	 * @param action the action the transmission is requesting
	 * @param contents the contents of the transmission
	 */
	public Transmission(String action, Object contents) {
		this.action = action;
		this.contents = contents;
		this.options = null;
	}
	
	/**
	 * Gets the action. See action attribute doc for more info.
	 * @return The action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Gets the contents of the transmission.
	 * @return the contents of the transmission
	 */
	public Object getContents() {
		return contents;
	}
	
	/**
	 * Gets the options of the transmission.
	 * @return the options of the transmission
	 */
	public ArrayList<String> getOptions() {
		return options;
	}

}
