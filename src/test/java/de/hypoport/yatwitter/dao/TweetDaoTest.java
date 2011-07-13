package de.hypoport.yatwitter.dao;

import org.junit.Test;

import de.hypoport.AbstractTest;
import de.hypoport.yatwitter.dto.Tweet;

public class TweetDaoTest extends AbstractTest {
	private TweetDao tweetDao;

	@Test
	public void testAddTweet() {
		Tweet object = new Tweet("Steffen", "kluge Worte");
		tweetDao.save(object);
		assertNotNull(tweetDao.get(object.getId()));
	}

	@Test
	public void testNoTweet() {
		assertTrue("Liste?", tweetDao.findAll(0, 100).isEmpty());
	}

	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		tweetDao = getBean(TweetDao.BEAN_ID, TweetDao.class);
	}
}
