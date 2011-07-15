package de.hypoport.yatwitter.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.hypoport.AbstractTest;
import de.hypoport.yatwitter.dto.User;

public class UserDaoTest extends AbstractTest {

	private IUserDao userDao;

	@Before
	public void before() {
		userDao = getBean(IUserDao.BEAN_ID, IUserDao.class);
	}

	@Test
	public void testNoUsers() {
		userDao = getBean(IUserDao.BEAN_ID, IUserDao.class);
		assertTrue(userDao.findAll(0, 100).isEmpty());
	}

	@Test
	public void testUserAddition() {
		userDao = getBean(IUserDao.BEAN_ID, IUserDao.class);
		String username = "Steffen";
		String password = "123456";
		userDao.save(new User(username, password));
		List<User> actual = userDao.findAll(0, 100);
		assertFalse(actual.isEmpty());
		assertEquals(username, actual.get(0).getName());
		assertEquals(password, actual.get(0).getPassword());
	}
}
