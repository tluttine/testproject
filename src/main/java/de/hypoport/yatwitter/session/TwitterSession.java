package de.hypoport.yatwitter.session;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import de.hypoport.TwitterApplication;
import de.hypoport.yatwitter.dto.User;

public class TwitterSession extends WebSession {

	public static TwitterSession get() {
		return (TwitterSession) Session.get();
	}

	public static String getLoggedUsername() {
		User loginData = getLoginData();
		return (null == loginData) ? null : loginData.getName();
	}

	// private User loginData;

	public static User getLoginData() {
		return Session.get().getMetaData(TwitterApplication.USER_KEY);
	}

	public static boolean hasValidLogin() {
		User loginData = getLoginData();
		return loginData != null;
	}

	public static boolean isLoggedUser(String user) {
		User loginData = getLoginData();
		return ((loginData != null) && (loginData.getName().equals(user)));
	}

	public static void setUser(User loginData) {
		Session.get().setMetaData(TwitterApplication.USER_KEY, loginData);
	}

	public TwitterSession(Request request) {
		super(request);
	}

}
