package de.hypoport.yatwitter.dao;

import de.hypoport.yatwitter.dto.LoginData;

public class LoginDataDao extends AbstractDao<String, LoginData> {

	public static final String BEAN_ID = "loginDataDao";
	public LoginDataDao() {
		super(LoginData.class);
	}
}
