package de.hypoport.yatwitter.page.component;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import de.hypoport.yatwitter.page.login.LoginPage;
import de.hypoport.yatwitter.session.TwitterSession;

public class LogoutLink extends Link<String> {
	public LogoutLink(String id) {
		super(id);

	}

	public LogoutLink(String id, IModel<String> model) {
		super(id, model);

	}

	@Override
	public void onClick() {
		TwitterSession.get().invalidate();
		setResponsePage(LoginPage.class);
	}

	@Override
	protected boolean callOnBeforeRenderIfNotVisible() {
		return true;
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();

		setVisible(TwitterSession.get().hasValidLogin());
	}
}
