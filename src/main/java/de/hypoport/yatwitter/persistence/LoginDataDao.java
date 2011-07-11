package de.hypoport.yatwitter.persistence;

import de.hypoport.yatwitter.login.LoginData;

public class LoginDataDao extends AbstractDao<String, LoginData> {

	public static final String BEAN_ID = "loginDataDao";
	protected LoginDataDao(Class<LoginData> domainClass) {
		super(domainClass);
	}
	public LoginDataDao() {

	}
	
	
}
