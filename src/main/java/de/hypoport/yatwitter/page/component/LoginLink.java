package de.hypoport.yatwitter.page.component;

import org.apache.wicket.markup.html.link.Link;

import de.hypoport.yatwitter.page.tweet.TwitterPage;
import de.hypoport.yatwitter.session.TwitterSession;

public final class LoginLink extends Link<String> {

	public LoginLink(String id) {
		super(id);

	}

	@Override
	public void onClick() {
		setResponsePage(TwitterPage.class);
	}

	@Override
	protected boolean callOnBeforeRenderIfNotVisible() {
		return true;
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();

		setVisible(!TwitterSession.get().hasValidLogin());
	}

}
