package de.hypoport.yatwitter.dao;

import org.junit.Test;

import de.hypoport.AbstractTest;

public class LikeDaoTest extends AbstractTest {

	private LikeDao likeDao;

	@Test
	public void testNoLikes() {
		likeDao = getBean(LikeDao.BEAN_ID, LikeDao.class);
		assertTrue(likeDao.findAll(0, 100).isEmpty());
	}
}
