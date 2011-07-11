package de.hypoport;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import de.hypoport.kaempke.MyHomePage;
import de.hypoport.yatwitter.comments.TwitterCommentPage;
import de.hypoport.yatwitter.login.LoginPage;
import de.hypoport.yatwitter.login.sessions.TwitterAuth;
import de.hypoport.yatwitter.login.sessions.TwitterAuthListener;
import de.hypoport.yatwitter.login.sessions.TwitterSession;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see de.hypoport.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/**
	 * Constructor
	 */
	public WicketApplication() {
	}

	@Override
	protected void init() {
		super.init();

		getSecuritySettings().setAuthorizationStrategy(new TwitterAuth());
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(
				new TwitterAuthListener(LoginPage.class));

		mountBookmarkablePage("login", LoginPage.class);
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override

	public Class<? extends WebPage> getHomePage() {
		return MyHomePage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new TwitterSession(request);
	}

}
