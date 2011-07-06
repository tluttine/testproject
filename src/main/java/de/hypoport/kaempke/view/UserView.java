package de.hypoport.kaempke.view;

import java.util.ArrayList;
import java.util.List;


import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import de.hypoport.kaempke.model.User;
import de.hypoport.kaempke.presenter.UserPresenter;

public class UserView extends Panel implements UserPresenter.Display {

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

	private FeedbackPanel feedbackPanel;
	private Form<Void> form;
	private ListChoice<String> userList;
	private AjaxButton createNewUserButton;
	private AjaxButton editUserButton;
	private AjaxButton removeUserButton;
	private AjaxButton saveUserButton;
	private Label labelFirstname;
	private Label labelLastname;

	private String selectedUser;

	public UserView(String id) {
		super(ID_PANEL);
		presenter = new UserPresenter(this);
		initiaslize();
	}

	private void initiaslize() {

		feedbackPanel = new FeedbackPanel(ID_PANEL_FEEDBACK_PANEL);
		add(feedbackPanel);

		form = new Form<Void>(ID_PANEL_FORM);
		userList = new ListChoice<String>(ID_PANEL_FORM_LIST_USERS, new PropertyModel<String>(this, "selectedUser"), new ArrayList<String>());
		userList.setMaxRows(5);
		form.add(userList);
		labelFirstname = new Label(ID_PANEL_FORM_LABEL_FIRSTNAME);
		labelLastname = new Label(ID_PANEL_FORM_LABEL_LASTNAME);
		form.add(labelFirstname);
		form.add(labelLastname);
		form.add(new TextField<String>(ID_PANEL_FORM_TEXTFIELD_FIRSTNAME));
	}

	public String getFirstname() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLastname() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setErrorMessage(String error) {
		// TODO Auto-generated method stub

	}

	public void setWarningMessage(String warning) {
		// TODO Auto-generated method stub

	}

	public void setInfoMessage(String info) {
		// TODO Auto-generated method stub

	}

	public void setUsers(List<User> users) {
		// TODO Auto-generated method stub

	}

	public void setSelectedIndex(int i) {
		// TODO Auto-generated method stub

	}

	public void getSelectedIndex() {
		// TODO Auto-generated method stub

	}

	public void clearForm() {
		// TODO Auto-generated method stub

	}

	public void clearSelection() {
		// TODO Auto-generated method stub

	}

	public void setSelectedUser(User user) {
		// TODO Auto-generated method stub

	}

}
