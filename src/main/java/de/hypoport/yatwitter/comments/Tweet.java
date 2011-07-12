package de.hypoport.yatwitter.comments;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import de.hypoport.yatwitter.persistence.DoInterface;

@Entity
@Table(name = "Tweet")
public class Tweet implements Serializable, DoInterface<Integer> {
	private String user;
	private String message;
	private Date date;

	private final Set<String> likes = new HashSet<String>();
	private int likesCount;
	private Integer id;

	public Tweet() {

	}

	public Tweet(String username, String comment) {
		user = username;
		message = comment;
		date = new Date();

	}

	public void addLike(String userId) {
		this.likes.add(userId);
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

	@Transient
	public int getLikes() {
		return likes.size();
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