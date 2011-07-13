package de.hypoport.yatwitter.session;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import de.hypoport.yatwitter.dto.User;


public class TwitterSession extends WebSession {

	private User loginData;

	public TwitterSession(Request request) {
		super(request);
	}

	public static TwitterSession get() {
		return (TwitterSession) Session.get();
	}

	public void setUser(User loginData) {
		this.loginData = loginData;
	}
	
	public boolean hasValidLogin() {
		return loginData!=null;
	}

	public String getLoggedUsername() {
		return (null == loginData) ? null : loginData.getName();
	}

	public boolean isLoggedUser(String user) {
		return ((loginData!=null) && (loginData.getName().equals(user)));
	}
	
}
