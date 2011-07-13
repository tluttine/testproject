package de.hypoport.yatwitter.dao;

import de.hypoport.yatwitter.dto.User;

public class UserDao extends AbstractDao<String, User> {

	public static final String BEAN_ID = "userDao";

	public UserDao() {
		super(User.class);
	}
}
