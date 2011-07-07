package de.hypoport.yatwitter.login.sessions;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import de.hypoport.yatwitter.login.LoginData;


public class TwitterSession extends WebSession {

	private LoginData loginData;

	public TwitterSession(Request request) {
		super(request);
	}

	public static TwitterSession get() {
		return (TwitterSession) Session.get();
	}

	public void setUser(LoginData loginData) {
		this.loginData = loginData;
	}
	
	public boolean hasValidLogin() {
		return loginData!=null;
	}

	public String getLoggedUsername() {
		return loginData.getName();
	}

	public boolean isLoggedUser(String user) {
		return ((loginData!=null) && (loginData.getName().equals(user)));
	}
	
}
