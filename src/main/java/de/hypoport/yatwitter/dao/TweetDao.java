package de.hypoport.yatwitter.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import de.hypoport.yatwitter.entity.Tweet;

public class TweetDao extends AbstractDao<Integer, Tweet> {

	public static final String BEAN_ID = "tweetDao";

	public TweetDao() {
		super(Tweet.class);
	}

	public List<Tweet> getLatestTweets(int count) {
		return getCriteria().addOrder(Order.desc("date")).setMaxResults(count).list();
	}
}