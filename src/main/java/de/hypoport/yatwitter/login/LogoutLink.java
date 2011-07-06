package de.hypoport.yatwitter.login;

import org.apache.wicket.markup.html.link.Link;

import de.hypoport.yatwitter.login.sessions.TwitterSession;

public class LogoutLink extends Link<String> {
	public LogoutLink(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		TwitterSession.get().invalidate();
		setResponsePage(LoginPage.class);
	}
	
	@Override
	protected void onBeforeRender() {
		// TODO Auto-generated method stub
		super.onBeforeRender();
		
		final TwitterSession twitterSession = TwitterSession.get();
		boolean hasValidLogin = twitterSession.hasValidLogin();
		setVisible(hasValidLogin);
	}
	
	@Override
	protected boolean callOnBeforeRenderIfNotVisible() {
		return true;
	}
}
