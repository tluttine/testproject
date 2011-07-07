package de.hypoport.yatwitter.comments;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Tweet implements Serializable{
	private String user;
	private String message;
	private Date date;
	private Set<String> likes;
	public Tweet(String username, String comment) {
		user = username;
		message = comment;
		date=new Date();
		likes=new HashSet<String>();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLikes() {
		return likes.size();
	}
	public void addLike(String userId) {
		this.likes.add(userId);
	}
	
}