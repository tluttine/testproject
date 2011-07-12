package de.hypoport.yatwitter.persistence;

import de.hypoport.yatwitter.login.LoginData;

public class LoginDataDao extends AbstractDao<String, LoginData> {

	public static final String BEAN_ID = "loginDataDao";
	public LoginDataDao() {
		super(LoginData.class);
	}
}
