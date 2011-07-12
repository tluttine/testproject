package de.hypoport.yatwitter.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.hypoport.yatwitter.dto.Like;
import de.hypoport.yatwitter.dto.Tweet;

public class TweetDao extends AbstractDao<Integer, Tweet> {

	public static final String BEAN_ID = "tweetDao";

	public TweetDao() {
		super(Tweet.class);

	}

	public List<Tweet> getLatestTweets(int count) {
		List<Tweet> list = getCriteria().addOrder(Order.desc("date")).setMaxResults(count).list();
		for (Tweet tweet : list) {
			Criteria likeCriteria = getSession().createCriteria(Like.class);
			likeCriteria.add(Restrictions.eq("id.tweet", tweet.getId()));
			likeCriteria.setProjection(Projections.rowCount());
			tweet.setLikesCount((Integer) likeCriteria.uniqueResult());
		}
		return list;
	}
}
