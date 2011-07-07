package de.hypoport.yatwitter.login;

import de.hypoport.yatwitter.comments.TweetsModel;
import de.hypoport.yatwitter.comments.TweetsPanel;
import de.hypoport.yatwitter.pages.AbstractBasePage;


public class LoginPage extends AbstractBasePage {

	
	public LoginPage() {
		
		setTitle("Loginseite");
		setHeadline("Bitte anmelden");
		
		add( new LoginPanel("loginPanel"));
		add(new TweetsPanel("tweetsPanel",  new TweetsModel()));
	}
}
