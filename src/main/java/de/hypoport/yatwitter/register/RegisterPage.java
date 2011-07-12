package de.hypoport.yatwitter.register;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.hypoport.yatwitter.comments.TwitterCommentPage;
import de.hypoport.yatwitter.dao.LoginDataDao;
import de.hypoport.yatwitter.entity.LoginData;
import de.hypoport.yatwitter.login.LoginPage;
import de.hypoport.yatwitter.login.sessions.TwitterSession;
import de.hypoport.yatwitter.pages.AbstractBasePage;

public final class RegisterPage extends AbstractBasePage {

	private Form<RegisterData> registerDataForm;
	private final IModel<RegisterData> registerFormModel = new CompoundPropertyModel<RegisterData>(new RegisterData());

	@SpringBean(name = LoginDataDao.BEAN_ID)
	private LoginDataDao loginDataDao;

	public RegisterPage() {
		initializeComponents();
	}

	private void initializeComponents() {
		setTitle("Register");
		setHeadline("Registrieren");
		registerDataForm = new Form<RegisterData>("registerForm", registerFormModel) {

			@Override
			protected void onSubmit() {
				String username = registerFormModel.getObject().getUsername();
				if (null != loginDataDao.get(username)) {
					warn("user exists " + username);
					return;
				}
				String password = registerFormModel.getObject().getPassword();
				final LoginData loginData = new LoginData(username, password);
				loginDataDao.save(loginData);
				TwitterSession.get().setUser(loginData);

				setResponsePage(TwitterCommentPage.class);
			}

		};

		final TextField<String> textFieldUserName = new TextField<String>("username");
		textFieldUserName.setRequired(true);

		final PasswordTextField textFieldPassword = new PasswordTextField("password");
		textFieldPassword.setRequired(true);

		final PasswordTextField textFieldPasswordRepeat = new PasswordTextField("passwordRepeat");
		textFieldPasswordRepeat.setRequired(true);

		registerDataForm.add(new Label("labelUsername", Model.of("Benutzername")));
		registerDataForm.add(new Label("labelPassword", Model.of("Passwort")));
		registerDataForm.add(new Label("labelPasswordRepeat", Model.of("Passwort wiederholen")));

		registerDataForm.add(textFieldUserName);
		registerDataForm.add(textFieldPassword);
		registerDataForm.add(textFieldPasswordRepeat);

		add(registerDataForm);
		add(new Link<String>("linkToLogin", Model.of("zurück zur Anmeldung")) {

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		});

		registerDataForm.add(new EqualPasswordInputValidator(textFieldPassword, textFieldPasswordRepeat));
	}
}
