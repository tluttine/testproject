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
	private TextField<String> textFieldFirstname;
	private TextField<String> textFieldLastname;

	private String selectedUser;

	public UserView(String id) {
		super("userViewPanel");
		presenter = new UserPresenter(this);
		initiaslize();
	}

	private void initiaslize() {

		feedbackPanel = new FeedbackPanel("userViewFeedbackpanel");
		
		saveUserButton = new AjaxButton("userViewSaveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.saveAction();
			}
		};
		
		editUserButton = new AjaxButton("userViewEditButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.editAction();
			}
		};

		

		removeUserButton = new AjaxButton("userViewRemoveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.removeAction();
			}
		};
		
		createNewUserButton = new AjaxButton("userViewCreateButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				presenter.createNewUserAction();
			}
		};
		
		textFieldFirstname = new TextField<String>("userViewTextfieldFirstname");
		textFieldLastname = new TextField<String>("userViewTextfieldLastname");
		
		userList = new ListChoice<String>("userViewUserlist", new PropertyModel<String>(this, "selectedUser"), new ArrayList<String>());
		userList.setMaxRows(5);
		labelFirstname = new Label("userViewLabelFirstname", "Vorname");
		labelLastname = new Label("userViewLabelLastname", "Nachname");
		form = new Form<User>("userViewForm", formModel);
		
		add(removeUserButton);
		add(editUserButton);
		add(feedbackPanel);
		
		form.add(saveUserButton);
		form.add(userList);
		form.add(labelFirstname);
		form.add(labelLastname);
		
		form.add(textFieldFirstname);
		
		form.add(textFieldLastname);
		form.add(saveUserButton);

		
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
