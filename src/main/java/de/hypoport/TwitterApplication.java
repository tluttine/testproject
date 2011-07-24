package de.hypoport;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import de.hypoport.finn.LightsOutGrid;
import de.hypoport.finn.LightsOutPage;
import de.hypoport.yatwitter.authority.TwitterAuth;
import de.hypoport.yatwitter.dto.User;
import de.hypoport.yatwitter.listener.TwitterAuthListener;
import de.hypoport.yatwitter.page.login.LoginPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see de.hypoport.Start#main(String[])
 */
public class TwitterApplication extends WebApplication {

	public static final MetaDataKey<User> USER_KEY = new MetaDataKey<User>() {
	};

	/**
	 * Constructor
	 */
	public TwitterApplication() {
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		//leave this and set the link in StartPagePanel.java instead
		return StartPage.class;
	}

	@Override
	protected void init() {
		super.init();

		getSecuritySettings().setAuthorizationStrategy(new TwitterAuth());
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(new TwitterAuthListener(LoginPage.class));

		mountBookmarkablePage("login", LoginPage.class);
		addComponentInstantiationListener(new SpringComponentInjector(this));
	}
}
