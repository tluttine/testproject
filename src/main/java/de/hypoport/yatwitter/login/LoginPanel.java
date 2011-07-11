package de.hypoport.yatwitter.login;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.comments.TwitterCommentPage;
import de.hypoport.yatwitter.login.sessions.TwitterSession;
import de.hypoport.yatwitter.register.RegisterPage;

public final class LoginPanel extends Panel{

	public LoginPanel(String id) {
		super(id);
		
		IModel<LoginData> formModel = new CompoundPropertyModel<LoginData>(new LoginData());
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
						setResponsePage(TwitterCommentPage.class);
					}
				}
				else error("Login oder Passwort falsch");
				
			}
		};
		TextField<String> nameField = new TextField<String>("name");
		nameField.setRequired(true);
		form.add(nameField);
		form.add(new PasswordTextField("password"));
		add(new Link<String>("linkRegister", Model.of("Registrieren")) {

			@Override
			public void onClick() {
				setResponsePage(RegisterPage.class);
				
			}
			
		});
		
		add(form);

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
