package de.hypoport.yatwitter.page.login.panel;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.hypoport.yatwitter.dao.UserDao;
import de.hypoport.yatwitter.dto.User;
import de.hypoport.yatwitter.page.register.RegisterPage;
import de.hypoport.yatwitter.page.tweet.TwitterPage;
import de.hypoport.yatwitter.session.TwitterSession;

public final class LoginPanel extends Panel {

	@SpringBean(name = UserDao.BEAN_ID)
	private UserDao userDao;

	public LoginPanel(String id) {
		super(id);

		final IModel<User> formModel = new CompoundPropertyModel<User>(new User());
		final Form<User> form = new Form<User>("form", formModel) {
			@Override
			protected void onSubmit() {
				// login prüfen
				final User loginData = getModelObject();
				User loginDataDb = userDao.get(loginData.getName());
				if (null == loginDataDb) {
					warn("Der Benutzer existiert noch nicht in der Datenbank.");
					return;
				}

				final String password = loginDataDb.getPassword();

				if (!password.equals(loginData.getPassword())) {
					error("Passwort falsch");
					return;
				}

				TwitterSession.get().setUser(loginDataDb);

				if (!continueToOriginalDestination()) {
					setResponsePage(TwitterPage.class);
				}
			}
		};

		TextField<String> nameField = new TextField<String>("name");
		nameField.setRequired(true);
		form.add(nameField);
		form.add(new PasswordTextField("password"));
		form.add(new Link<String>("linkRegister", Model.of("Registrieren")) {

			@Override
			public void onClick() {
				setResponsePage(RegisterPage.class);

			}

		});

		add(form);

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
