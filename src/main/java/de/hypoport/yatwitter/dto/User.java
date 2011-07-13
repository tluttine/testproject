package de.hypoport.yatwitter.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "twitter_user")
public final class User implements Serializable, IDataTransferObject<String> {
	@Id
	String name;
	String password;

	public User() {
		super();
	}

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	@Override
	public String getId() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	@Column(nullable = false, name = "password")
	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
