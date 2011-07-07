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
	private User user = new User("", "");
	private IModel<User> formModel = new CompoundPropertyModel<User>(this.user);
	private TextField<String> textFieldFirstname;
	private TextField<String> textFieldLastname;

	private String selectedUser;

	public UserView(String id) {
		super(id);
		this.presenter = new UserPresenter(this);
		initiaslize();
	}

	private void initiaslize() {

		feedbackPanel = new FeedbackPanel("userViewFeedbackPanel");
		feedbackPanel.setOutputMarkupId(true);

		this.saveUserButton = new AjaxButton("userViewSaveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> userForm) {
				presenter.saveAction();
				target.addComponent(feedbackPanel);
				target.addComponent(userList);
			}
		};

		this.editUserButton = new AjaxButton("userViewEditButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> userForm) {
				presenter.editAction();
			}
		};

		removeUserButton = new AjaxButton("userViewRemoveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> userForm) {
				presenter.removeAction();
			}
		};

		createNewUserButton = new AjaxButton("userViewCreateButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> userForm) {
				presenter.createNewUserAction();
			}
		};

		this.textFieldFirstname = new TextField<String>("firstname");
		this.textFieldLastname = new TextField<String>("lastname");

		this.userList = new ListChoice<String>("userViewUserlist", new PropertyModel<String>(this, "selectedUser"), new ArrayList<String>());
		this.userList.setMaxRows(5);
		userList.setOutputMarkupId(true);
		this.labelFirstname = new Label("userViewLabelFirstname", "Vorname");
		this.labelLastname = new Label("userViewLabelLastname", "Nachname");
		this.form = new Form<User>("formPanelId", formModel);

		this.form.add(this.removeUserButton);
		this.form.add(this.editUserButton);
		
		this.form.add(createNewUserButton);

		this.form.add(saveUserButton);
		this.form.add(userList);
		this.form.add(labelFirstname);
		this.form.add(labelLastname);

		this.form.add(textFieldFirstname);
		this.form.add(textFieldLastname);
		this.form.add(saveUserButton);

		add(feedbackPanel);
		add(form);
	}

	public String getFirstname() {
		return this.form.getModelObject().getFirstname();
	}

	public String getLastname() {
		return this.form.getModelObject().getLastname();
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

		this.userList.setChoices(userNames);

	}

	public void setSelectedIndex(int i) {
		this.selectedUser = userList.getChoices().get(i);
	}

	public int getSelectedIndex() {
		return this.userList.getChoices().indexOf(selectedUser);
	}

	public void clearForm() {
		this.form.getModelObject().setFirstname("");
		this.form.getModelObject().setLastname("");
	}

	public void clearSelection() {
		this.selectedUser = null;
	}

	public void setSelectedUser(User user) {
		// TODO Auto-generated method stub

	}

}
