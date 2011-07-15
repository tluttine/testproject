package de.hypoport.yatwitter.dao;

import de.hypoport.yatwitter.dto.User;

public interface IUserDao extends IDataAccessObject<String, User> {
	public static final String BEAN_ID = "userDao";
}
