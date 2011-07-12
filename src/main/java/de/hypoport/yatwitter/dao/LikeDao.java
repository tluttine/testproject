package de.hypoport.yatwitter.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import de.hypoport.yatwitter.dto.Like;
import de.hypoport.yatwitter.dto.Tweet;

public class LikeDao extends AbstractDao<Like.Key, Like> {

	public static final String BEAN_ID = "likeDao";

	public LikeDao() {
		super(Like.class);
	}

	public List<Like> getLikesForTweet(Tweet tweet) {
		return getCriteria().add(Restrictions.eq("id.tweet", tweet.getId())).list();
	}
}
