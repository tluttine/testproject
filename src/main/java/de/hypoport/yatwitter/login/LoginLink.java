package de.hypoport.yatwitter.login;

import org.apache.wicket.markup.html.link.Link;

import de.hypoport.yatwitter.comments.TwitterCommentPage;
import de.hypoport.yatwitter.login.sessions.TwitterSession;

public final class LoginLink extends Link<String>{

	public LoginLink(String id) {
		super(id);
	}

	@Override
	public void onClick() {
		setResponsePage(TwitterCommentPage.class);		
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		setVisible(!TwitterSession.get().hasValidLogin());
	}
	
	@Override
	protected boolean callOnBeforeRenderIfNotVisible() {
		return true;
	}

}
