package de.hypoport.yatwitter.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tweet")
public class Tweet implements Serializable, IDataTransferObject<Integer> {
	private String user;
	private String message;
	private Date date;
	private int likesCount;
	private Integer id;

	public Tweet() {

	}

	public Tweet(String username, String comment) {
		user = username;
		message = comment;
		date = new Date();

	}

	@Column(nullable = false, name = "date")
	public Date getDate() {
		return date;
	}

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public int getLikesCount() {
		return likesCount;
	}

	@Column(nullable = false, name = "message")
	public String getMessage() {
		return message;
	}

	@Column(nullable = false, name = "user")
	public String getUser() {
		return user;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(Integer newId) {
		id = newId;
	}

	@Column(nullable = false, name = "likes_count")
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUser(String user) {
		this.user = user;
	}
}