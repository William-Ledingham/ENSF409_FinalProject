package shared.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a single transmission from client-to-server, or from server-to-client. 
 * Each transmission has an action, and contains the associated object(s) used for the transmission.
 * 
 * @author Parker Link
 * @since Mar. 13, 2020
 *
 */
public class Transmission implements Serializable {
	/**
	 * Class identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The action the transmission is requesting. Supported actions include:
	 * - AddStudent
	 * - AddClass
	 * - GetClasses
	 * - RefreshCatalogue
	 * - RespondCatalogue
	 * - Success (return that it was successful)
	 */
	private String action;
	
	/**
	 * Contents of the transmission. This can be a Student, Class, ArrayList<Class>, etc.
	 */
	private Object contents;
	
	/**
	 * Options for the action, as any types of Strings.
	 * - For AddClass action: [faculty, courseNumber, lect number]
	 */
	private ArrayList<String> options;


	public Transmission(String action, Object contents, ArrayList<String> options) {
		this.action = action;
		this.contents = contents;
		this.options = options;
	}
	
	public Transmission(String action) {
		this.action = action;
		this.contents = null;
		this.options = null;
	}
	
	public Transmission(String action, ArrayList<String> options) {
		this.action = action;
		this.contents = null;
		this.options = options;
	}
	
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
