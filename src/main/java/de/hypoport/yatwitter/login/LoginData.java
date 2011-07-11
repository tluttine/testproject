package de.hypoport.yatwitter.login;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import de.hypoport.yatwitter.persistence.DoInterface;

@Entity
@Table(name="loginData")
public final class LoginData implements Serializable, DoInterface<String> {
	String name;
	String password;
	
	public LoginData() {
		super();
	}

	public LoginData(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

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

	@Override
	public String getId() {
		return this.name;
	}
	
}
