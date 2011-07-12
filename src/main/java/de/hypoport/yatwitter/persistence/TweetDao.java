package de.hypoport.yatwitter.persistence;

import de.hypoport.yatwitter.comments.Tweet;

public class TweetDao extends AbstractDao<Integer, Tweet> {

	public static final String BEAN_ID = "tweetDao";

	public TweetDao() {
		super(Tweet.class);
	}

}
