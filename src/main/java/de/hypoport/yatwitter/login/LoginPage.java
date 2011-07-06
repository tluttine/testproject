package de.hypoport.yatwitter.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.hypoport.HomePage;
import de.hypoport.yatwitter.comments.TwitterCommentPage;
import de.hypoport.yatwitter.login.sessions.TwitterSession;


public class LoginPage extends WebPage {

	
	public LoginPage() {
		
		add(new FeedbackPanel("feedback"));
		
		IModel<LoginData> formModel = new CompoundPropertyModel(new LoginData());
		Form<LoginData> form = new Form<LoginData>("form",formModel)
		{
			@Override
			protected void onSubmit() {
				// login prüfen
				LoginData loginData = getModelObject();
				if (Logins.isValid(loginData.getName(), loginData.getPassword())) {
					info("Nutzer kenn ich");
					TwitterSession.get().setUser(loginData);
					
					if (!continueToOriginalDestination()) {
						setResponsePage(HomePage.class);
					}
				}
				else error("Login oder Passwort falsch");
				
			}
		};
		TextField<String> nameField = new TextField<String>("name");
		nameField.setRequired(true);
		form.add(nameField);
		form.add(new PasswordTextField("password"));
		add(form);
	}
}
