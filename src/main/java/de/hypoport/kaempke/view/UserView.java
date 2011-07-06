package de.hypoport.kaempke.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import de.hypoport.kaempke.model.User;
import de.hypoport.kaempke.presenter.UserPresenter;

public class UserView extends Panel implements UserPresenter.Display {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final UserPresenter presenter;

	private final static String ID_PANEL = "PanelId";
	private final static String ID_PANEL_FEEDBACK_PANEL = "panelFeedback" + ID_PANEL;
	private final static String ID_PANEL_FORM = "form" + ID_PANEL;
	private final static String ID_PANEL_FORM_LABEL_FIRSTNAME = "firstnameLabel" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_LABEL_LASTNAME = "lastnameLabel" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_TEXTFIELD_FIRSTNAME = "firstnameTextfield" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_TEXTFIELD_LASTNAME = "lastnameTextfield" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_LIST_USERS = "usersList" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_BUTTON_REMOVE = "removeButton" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_BUTTON_EDIT = "editButton" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_BUTTON_NEW = "newButton" + ID_PANEL_FORM;
	private final static String ID_PANEL_FORM_BUTTON_SAVE = "saveButton" + ID_PANEL_FORM;

	private FeedbackPanel feedbackPanel;
	private Form<User> form;
	private ListChoice<String> userList;
	private AjaxButton createNewUserButton;
	private AjaxButton editUserButton;
	private AjaxButton removeUserButton;
	private AjaxButton saveUserButton;
	private Label labelFirstname;
	private Label labelLastname;
	private IModel<User> formModel = new CompoundPropertyModel<User>(new User("", ""));

	private String selectedUser;

	public UserView(String id) {
		super(ID_PANEL);
		presenter = new UserPresenter(this);
		initiaslize();
	}

	private void initiaslize() {

		feedbackPanel = new FeedbackPanel(ID_PANEL_FEEDBACK_PANEL);
		add(feedbackPanel);

		saveUserButton = new AjaxButton(ID_PANEL_FORM_BUTTON_SAVE) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.saveAction();
			}
		};

		form = new Form<User>(ID_PANEL_FORM, formModel);
		form.add(saveUserButton);
		userList = new ListChoice<String>(ID_PANEL_FORM_LIST_USERS, new PropertyModel<String>(this, "selectedUser"), new ArrayList<String>());
		userList.setMaxRows(5);
		form.add(userList);
		labelFirstname = new Label(ID_PANEL_FORM_LABEL_FIRSTNAME, "Vorname");
		labelLastname = new Label(ID_PANEL_FORM_LABEL_LASTNAME, "Nachname");
		form.add(labelFirstname);
		form.add(labelLastname);
		form.add(new TextField<String>(ID_PANEL_FORM_TEXTFIELD_FIRSTNAME));
		form.add(new TextField<String>(ID_PANEL_FORM_TEXTFIELD_LASTNAME));
		form.add(saveUserButton);

		editUserButton = new AjaxButton(ID_PANEL_FORM_BUTTON_EDIT) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.editAction();
			}
		};

		add(editUserButton);

		removeUserButton = new AjaxButton(ID_PANEL_FORM_BUTTON_REMOVE) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.removeAction();
			}
		};
		add(removeUserButton);
		createNewUserButton = new AjaxButton(ID_PANEL_FORM_BUTTON_NEW) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.createNewUserAction();
			}
		};
		add(createNewUserButton);
	}

	public String getFirstname() {
		return form.getModelObject().getFirstname();
	}

	public String getLastname() {
		return form.getModelObject().getLastname();
	}

	public void setErrorMessage(String error) {
		error(error);
	}

	public void setWarningMessage(String warning) {
		warn(warning);
	}

	public void setInfoMessage(String info) {
		info(info);
	}

	public void setUsers(List<User> users) {
		userList.getChoices().clear();
		final List<String> userNames = new ArrayList<String>();
		for (int i = 0; i < users.size(); i++) {
			userNames.add(users.get(i).getFirstname() + " " + users.get(i).getLastname());

		}

		userList.setChoices(userNames);

	}

	public void setSelectedIndex(int i) {
		selectedUser = userList.getChoices().get(i);
	}

	public int getSelectedIndex() {
		return userList.getChoices().indexOf(selectedUser);
	}

	public void clearForm() {
		form.getModelObject().setFirstname("");
		form.getModelObject().setLastname("");
	}

	public void clearSelection() {
		selectedUser = null;
	}

	public void setSelectedUser(User user) {
		// TODO Auto-generated method stub

	}

}
