package shared.model;

import java.io.Serializable;

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
	 * - Success (return that it was successful)
	 */
	private String action;
	
	/**
	 * Contents of the transmission. This can be a Student, Class, ArrayList<Class>, etc.
	 */
	private Object contents;


	public Transmission(String action, Object contents) {
		this.action = action;
		this.contents = contents;
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
	
	

}
