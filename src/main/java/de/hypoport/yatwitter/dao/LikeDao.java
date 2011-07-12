package de.hypoport.yatwitter.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import de.hypoport.yatwitter.entity.LikeDo;
import de.hypoport.yatwitter.entity.Tweet;

public class LikeDao extends AbstractDao<LikeDo.Key, LikeDo> {

	public static final String BEAN_ID = "likeDao";

	public LikeDao() {
		super(LikeDo.class);
	}

	public List<LikeDo> getLikesForTweet(Tweet tweet) {
		return getCriteria().add(Restrictions.eq("id.tweet", tweet.getId())).list();
	}
}
