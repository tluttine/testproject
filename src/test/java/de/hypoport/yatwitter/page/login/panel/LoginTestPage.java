package de.hypoport.yatwitter.page.login.panel;

import de.hypoport.yatwitter.page.AbstractBasePage;

public class LoginTestPage extends AbstractBasePage {

	public LoginTestPage() {
		super();
		setTitle("Hyvää huomenta");
		// IUserDao mockUserDao = new UserDaoMock();
		add(new LoginPanel("loginPanel"/* , mockUserDao */));
	}

}
