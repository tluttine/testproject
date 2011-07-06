package de.hypoport.einarbeitung;

import java.io.Serializable;

public class UserName implements Serializable {

	private static final long serialVersionUID = 7186086611871010095L;
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getGreeting() {
		if (name != null)
			return "Hallo " + name + "!";
		return "Hallo!";
	}
	
}
