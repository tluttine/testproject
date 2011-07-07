package de.hypoport.yatwitter.comments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TweetContainer {
//	private static Map<String, List<String>> tweets = new HashMap<String, List<String>>();
	
	private static List<Tweet> tweets = new ArrayList<Tweet>(); 

	private TweetContainer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void addTweet(String username, String comment) {
		tweets.add(new Tweet(username,comment));
	}
	
	public static List<Tweet> getTweets(int max) {
		int ende = tweets.size();
		int start = Math.max(0, ende - max);
		List<Tweet> subList = tweets.subList(start, ende);
		Collections.reverse(subList);
		return Collections.unmodifiableList(subList);
	}
	
	public static class Tweet {
		String user;
		String message;
		Date date;
		public Tweet(String username, String comment) {
			user = username;
			message = comment;
			date=new Date();
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
		
	}
}
