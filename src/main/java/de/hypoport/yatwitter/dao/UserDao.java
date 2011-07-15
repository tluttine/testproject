package de.hypoport.yatwitter.dao;

import de.hypoport.yatwitter.dto.User;

public class UserDao extends AbstractDao<String, User> implements IUserDao {

	public UserDao() {
		super(User.class);
	}
}