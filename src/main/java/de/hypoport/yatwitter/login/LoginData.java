package de.hypoport.yatwitter.login;

import java.io.Serializable;


public class LoginData implements Serializable {
	String name;
	String password;
	
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
	
}
