package termproj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	protected String firstName, lastName, dateofbirth, username, password;
	protected List<PatientMessage> inbox = new ArrayList<PatientMessage>();

	public User() {
		
	}
	
	public String toString() {
		return ("" + firstName + " " + lastName);
	}
	
	public String getUsername() {
		return username;
	}
	public void addMessage(PatientMessage msg) {
		inbox.add(msg);
	}
	public User(String fName, String lName) {
		firstName = fName;
		lastName = lName;
	}

}
