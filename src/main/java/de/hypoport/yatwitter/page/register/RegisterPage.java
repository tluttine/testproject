package de.hypoport.yatwitter.page.register;

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

import de.hypoport.yatwitter.dao.IUserDao;
import de.hypoport.yatwitter.dto.User;
import de.hypoport.yatwitter.model.RegisterData;
import de.hypoport.yatwitter.page.AbstractBasePage;
import de.hypoport.yatwitter.page.login.LoginPage;
import de.hypoport.yatwitter.page.tweet.TwitterPage;
import de.hypoport.yatwitter.session.TwitterSession;

public final class RegisterPage extends AbstractBasePage {

	private Form<RegisterData> registerDataForm;
	private final IModel<RegisterData> registerFormModel = new CompoundPropertyModel<RegisterData>(new RegisterData());

	@SpringBean(name = IUserDao.BEAN_ID)
	private IUserDao loginDataDao;

	public RegisterPage() {
		initializeComponents();
	}

	private void initializeComponents() {
		setTitle("Register");
		registerDataForm = new Form<RegisterData>("registerForm", registerFormModel) {

			@Override
			protected void onSubmit() {
				String username = registerFormModel.getObject().getUsername();
				if (null != loginDataDao.get(username)) {
					warn("user exists " + username);
					return;
				}
				String password = registerFormModel.getObject().getPassword();
				final User loginData = new User(username, password);
				loginDataDao.save(loginData);
				TwitterSession.get().setUser(loginData);

				setResponsePage(TwitterPage.class);
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
		registerDataForm.add(new Link<String>("linkToLogin", Model.of("zurück zur Anmeldung")) {

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		});

		registerDataForm.add(new EqualPasswordInputValidator(textFieldPassword, textFieldPasswordRepeat));
	}
}
