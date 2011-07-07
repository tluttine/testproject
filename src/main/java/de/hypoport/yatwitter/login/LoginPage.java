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
		
//		IModel<LoginData> formModel = new CompoundPropertyModel<LoginData>(new LoginData());
//		Form<LoginData> form = new Form<LoginData>("form",formModel)
//		{
//			@Override
//			protected void onSubmit() {
//				// login prüfen
//				LoginData loginData = getModelObject();
//				if (Logins.isValid(loginData.getName(), loginData.getPassword())) {
//					info("Nutzer kenn ich");
//					TwitterSession.get().setUser(loginData);
//					
//					if (!continueToOriginalDestination()) {
//						setResponsePage(TweetsPage.class);
//					}
//				}
//				else error("Login oder Passwort falsch");
//				
//			}
//		};
//		TextField<String> nameField = new TextField<String>("name");
//		nameField.setRequired(true);
//		form.add(nameField);
//		form.add(new PasswordTextField("password"));
//		add(form);
//		add(new Link<String>("gotoCommentPageLink") {
//
//			@Override
//			public void onClick() {
//				setResponsePage(TweetsPage.class);
//			}
//
//		});
	}
}
