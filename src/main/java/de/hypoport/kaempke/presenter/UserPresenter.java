package de.hypoport.kaempke.presenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hypoport.kaempke.model.User;

public final class UserPresenter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserPresenter.Display display;
	private User selectedUser;
	private final List<User> users = new ArrayList<User>();

	/**
	 * 
	 * @param display
	 */
	public UserPresenter(UserPresenter.Display display) {
		assert null != display;
		this.display = display;
	}

	public interface Display {
		String getFirstname();

		String getLastname();

		void setErrorMessage(String error);

		void setWarningMessage(String warning);

		void setInfoMessage(String info);

		void setUsers(List<User> users);

		void setSelectedIndex(int i);

		int getSelectedIndex();

		void clearForm();

		void clearSelection();

		void setSelectedUser(User user);

	}

	public void setSelectedUserAction(int index) {
		if (index < 0) {
			return;
		}

		selectedUser = users.get(index);
		display.setSelectedUser(selectedUser);
	}

	public void saveAction() {
		if (!displayHasValidForm()) {
			return;
		}

		final String firstname = display.getFirstname();
		final String lastname = display.getLastname();

		if (null == selectedUser) {
			selectedUser = new User(firstname, lastname);
		}

		users.add(selectedUser);
		display.setUsers(users);
		final int selectedUserIndex = users.indexOf(selectedUser);
		display.setSelectedIndex(selectedUserIndex);
		display.setInfoMessage("Der Benutzer " + selectedUser.getFirstname() + " " + selectedUser.getLastname() + " wurde erfolreich erstellt.");
	}

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
	}

	public void editAction() {
		if (null == selectedUser) {
			display.setWarningMessage("Es wurde kein Benutzer zum bearbeiten ausgewählt.");
			return;
		}

		if (!displayHasValidForm()) {
			return;
		}

		final String firstname = display.getFirstname();
		final String lastname = display.getLastname();

		selectedUser.setFirstname(firstname);
		selectedUser.setLastname(lastname);

		display.setInfoMessage("Der Benutzer wurde erfolgreich bearbeitet.");
	}

	private boolean displayHasValidForm() {

		final String firstname = display.getFirstname();
		final String lastname = display.getLastname();

		if (firstname.isEmpty()) {
			display.setWarningMessage("Du hast vergessen den Vornamen einzugeben.");
			return false;
		}

		if (lastname.isEmpty()) {
			display.setWarningMessage("Du hast vergessen den Nachnamen einzugeben.");
			return false;
		}

		return true;
	}

}
