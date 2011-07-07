package de.hypoport.yatwitter.comments;

import java.util.ArrayList;
import java.util.Collections;
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
		List<Tweet> subList = new ArrayList<Tweet>(tweets.subList(start, ende));	
		
		Collections.reverse(subList);
		return Collections.unmodifiableList(subList);
	}
}
