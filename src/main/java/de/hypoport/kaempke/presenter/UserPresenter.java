package de.hypoport.kaempke.presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import de.hypoport.kaempke.model.MyUser;
import de.hypoport.kaempke.view.UserView;

public final class UserPresenter implements Serializable {

	/**
	 * Display
	 * 
	 * @author kaempke
	 * 
	 */
	public interface Display {
		void clearForm();

		void clearSelection();

		String getFirstname();

		TextField<String> getFirstnameTextfield();

		Form<MyUser> getForm();

		String getLastname();

		TextField<String> getLastnameTextField();

		int getSelectedIndex();

		String getSelectedUser();

		ListChoice<String> getuserListChoise();

		void setInfoMessage(String info);

		void setSelectedIndex(int i);

		void setSelectedUser(MyUser user);

		void setUsers(List<MyUser> users);

		void setWarningMessage(String warning);
	}

	/**
	 * Fields
	 */
	private final UserPresenter.Display display;
	private MyUser selectedUser;
	private final List<MyUser> users = new ArrayList<MyUser>();

	/**
	 * Constructor
	 * 
	 * @param display
	 */
	public UserPresenter(UserPresenter.Display display) {
		assert null != display;
		this.display = display;
		bind();
	}

	/**
	 * Actions
	 */
	public void createNewUserAction() {
		selectedUser = null;
		display.clearForm();
		display.clearSelection();
		display.setInfoMessage("Bitte geben Sie die Daten des neuen Benutzers ein.");
	}

	public void removeAction() {
		if (null == selectedUser) {
			display.setWarningMessage("Es wurde kein Benutzer zum löschen ausgewählt.");
			return;
		}

		assert users.contains(selectedUser);
		users.remove(selectedUser);
		display.clearForm();
		display.clearSelection();
		display.setInfoMessage("Der Benutzer " + selectedUser.getFirstname() + " " + selectedUser.getLastname() + " wurde erfolreich entfernt.");
		selectedUser = null;
		display.setUsers(users);
	}

	public void saveAction(AjaxRequestTarget target) {
		if (!displayHasValidForm(target)) {
			return;
		}

		final String firstname = display.getFirstname();
		final String lastname = display.getLastname();

		if (null == selectedUser) {
			selectedUser = new MyUser(firstname, lastname);
		}

		if (!users.contains(selectedUser)) {
			users.add(selectedUser);
		} else {
			users.remove(selectedUser);
			selectedUser = new MyUser(firstname, lastname);
			users.add(selectedUser);
		}

		display.setUsers(users);
		final int selectedUserIndex = users.indexOf(selectedUser);
		display.setSelectedIndex(selectedUserIndex);
		display.setInfoMessage("Der Benutzer " + selectedUser.getFirstname() + " " + selectedUser.getLastname() + " wurde erfolreich gespeichert.");

	}

	/**
	 * Display bindings
	 */
	private void bind() {
		display.getFirstnameTextfield().add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onBlurFirstname(target);
			}

		});

		display.getLastnameTextField().add(new AjaxFormComponentUpdatingBehavior("onBlur") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onBlurLastname(target);
			}

		});

		display.getFirstnameTextfield().add(new AjaxFormComponentUpdatingBehavior("onFocus") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onFocusFirstname(target);
			}
		});

		display.getLastnameTextField().add(new AjaxFormComponentUpdatingBehavior("onFocus") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onFocusLastname(target);
			}
		});

		display.getuserListChoise().add(new AjaxFormComponentUpdatingBehavior("onchange") {
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onChangeUserListChoise(target);
			}
		});
	}

	/**
	 * Private Member
	 * 
	 * @return
	 */
	private boolean displayHasValidForm(AjaxRequestTarget target) {

		if (!isFirstnameValid()) {
			target.addComponent(display.getFirstnameTextfield().add(UserView.TEXTFIELD_WARNINING_MODIFIER));
			display.setWarningMessage("Du hast vergessen den Vornamen einzugeben.");
			return false;
		}

		if (!isLastnameValid()) {
			target.addComponent(display.getLastnameTextField().add(UserView.TEXTFIELD_WARNINING_MODIFIER));
			display.setWarningMessage("Du hast vergessen den Nachnamen einzugeben.");
			return false;
		}

		target.addComponent(display.getLastnameTextField().add(UserView.TEXTFIELD_VALID_MODUFIER));
		target.addComponent(display.getFirstnameTextfield().add(UserView.TEXTFIELD_VALID_MODUFIER));
		return true;
	}

	private boolean isFirstnameValid() {
		final String firstname = display.getFirstname();
		return (null == firstname || firstname.isEmpty()) ? false : true;
	}

	private boolean isLastnameValid() {
		final String lastname = display.getLastname();
		return (null == lastname || lastname.isEmpty()) ? false : true;
	}

	private void onBlurFirstname(AjaxRequestTarget target) {
		final String firstname = display.getFirstname();
		if (null == firstname || firstname.isEmpty()) {
			display.getFirstnameTextfield().add(UserView.TEXTFIELD_WARNINING_MODIFIER);
			target.addComponent(display.getFirstnameTextfield());
			display.setWarningMessage("Der Vorname ist leer.");
			return;
		}

		display.getFirstnameTextfield().add(UserView.TEXTFIELD_VALID_MODUFIER);
		target.addComponent(display.getFirstnameTextfield());
		display.setInfoMessage("Hier kannst Du Benutzer hinzufügen, bearbeiten oder löschen.");

	}

	private void onBlurLastname(AjaxRequestTarget target) {
		final String lastname = display.getLastname();
		if (null == lastname || lastname.isEmpty()) {
			display.getLastnameTextField().add(UserView.TEXTFIELD_WARNINING_MODIFIER);
			target.addComponent(display.getLastnameTextField());
			display.setWarningMessage("Der Vorname ist leer.");
			return;
		}

		display.getFirstnameTextfield().add(UserView.TEXTFIELD_VALID_MODUFIER);

		target.addComponent(display.getLastnameTextField());
		display.setInfoMessage("Hier kannst Du Benutzer hinzufügen, bearbeiten oder löschen.");

	}

	private void onChangeUserListChoise(AjaxRequestTarget target) {
		final ListChoice<String> userListChoise = display.getuserListChoise();
		final List<? extends String> choices = userListChoise.getChoices();
		final int index = choices.indexOf(display.getSelectedUser());

		if (index < 0) {
			return;
		}

		selectedUser = users.get(index);
		display.setSelectedUser(selectedUser);
		target.addComponent(display.getForm());
	}

	private void onFocusFirstname(AjaxRequestTarget target) {
		display.getFirstnameTextfield().add(new AttributeModifier("style", true, Model.of("background-color:#cccccc")));
		target.addComponent(display.getFirstnameTextfield());
		display.setInfoMessage("Der Nachname wird zur Anzeige in der Benutzerliste benötigt..");
	}

	private void onFocusLastname(AjaxRequestTarget target) {
		display.getLastnameTextField().add(new AttributeModifier("style", true, Model.of("background-color:#cccccc")));
		target.addComponent(display.getLastnameTextField());
		display.setInfoMessage("Der Vorname wird zur Anzeige in der Benutzerliste benötigt..");
	}
}
