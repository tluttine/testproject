package de.hypoport.yatwitter.page.login;

import de.hypoport.yatwitter.page.AbstractBasePage;
import de.hypoport.yatwitter.page.login.panel.LoginPanel;
import de.hypoport.yatwitter.page.tweet.panel.TweetListPanel;
import de.hypoport.yatwitter.page.tweet.panel.TweetsModel;


public class LoginPage extends AbstractBasePage {

	
	public LoginPage() {
		
		setTitle("Loginseite");
		setHeadline("Bitte anmelden");
		
		add( new LoginPanel("loginPanel"));
		add(new TweetListPanel("tweetsPanel",  new TweetsModel()));
	}
}
