package de.hypoport.yatwitter.persistence;

import de.hypoport.yatwitter.comments.Tweet;

public class TweetDao extends AbstractDao<Integer, Tweet> {

	protected TweetDao(Class<Tweet> domainClass) {
		super(domainClass);
		
	}

	public TweetDao() {
	}

}
