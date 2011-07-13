package de.hypoport.yatwitter.dao;

import org.junit.Before;
import org.junit.Test;

import de.hypoport.AbstractTest;
import de.hypoport.yatwitter.dto.Tweet;

public class TweetDaoTest extends AbstractTest {
	private TweetDao tweetDao;

	@Before
	public void before() {
		tweetDao = getBean(TweetDao.BEAN_ID, TweetDao.class);
	}

	@Test
	public void testAddTweet() {
		tweetDao = getBean(TweetDao.BEAN_ID, TweetDao.class);
		Tweet object = new Tweet("Steffen", "kluge Worte");
		tweetDao.save(object);
		assertNotNull(tweetDao.get(object.getId()));
	}

	@Test
	public void testNoTweet() {
		tweetDao = getBean(TweetDao.BEAN_ID, TweetDao.class);
		assertTrue(tweetDao.findAll(0, 100).isEmpty());
	}

}
