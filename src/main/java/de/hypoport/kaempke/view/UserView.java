package de.hypoport.kaempke.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.hypoport.kaempke.model.User;
import de.hypoport.kaempke.presenter.UserPresenter;

public class UserView extends Panel implements UserPresenter.Display {

	/**
	 * static member
	 */
	public static final AttributeModifier TEXTFIELD_VALID_MODUFIER = new AttributeModifier("style", true, Model.of("background-color:#c0f56e"));
	public static final AttributeModifier TEXTFIELD_WARNINING_MODIFIER = new AttributeModifier("style", true, Model.of("background-color:#ff8073"));
	public static final AttributeModifier TEXTFIELD_DEFAULT_MODIFIER = new AttributeModifier("style", true, Model.of("background-color:#cccccc"));

	private static final long serialVersionUID = 1L;
	private final UserPresenter presenter;
	private FeedbackPanel feedbackPanel;
	private Form<User> form;
	private ListChoice<String> userList;
	private AjaxButton createNewUserButton;
	private AjaxButton removeUserButton;
	private AjaxButton saveUserButton;
	private Label labelFirstname;
	private Label labelLastname;
	private final User user = new User("", "");
	private final IModel<User> formModel = new CompoundPropertyModel<User>(this.user);
	private TextField<String> textFieldFirstname;
	private TextField<String> textFieldLastname;

	private String selectedUser;

	public UserView(String id) {
		super(id);

		initializeComponents();
		this.presenter = new UserPresenter(this);
	}

	@Override
	public void clearForm() {
		this.form.setModelObject(new User("", ""));
		textFieldFirstname.add(TEXTFIELD_DEFAULT_MODIFIER);
		textFieldLastname.add(TEXTFIELD_DEFAULT_MODIFIER);
	}

	@Override
	public void clearSelection() {
		this.selectedUser = null;
	}

	@Override
	public String getFirstname() {
		return this.form.getModelObject().getFirstname();
	}

	@Override
	public TextField<String> getFirstnameTextfield() {
		return textFieldFirstname;
	}

	@Override
	public Form<User> getForm() {
		return form;
	}

	@Override
	public String getLastname() {
		return this.form.getModelObject().getLastname();
	}

	@Override
	public TextField<String> getLastnameTextField() {
		return textFieldLastname;
	}

	@Override
	public int getSelectedIndex() {
		return this.userList.getChoices().indexOf(selectedUser);
	}

	@Override
	public String getSelectedUser() {
		return selectedUser;
	}

	@Override
	public ListChoice<String> getuserListChoise() {
		return userList;
	}

	@Override
	public void setInfoMessage(String info) {
		info(info);
	}

	@Override
	public void setSelectedIndex(int i) {
		this.selectedUser = userList.getChoices().get(i);
	}

	@Override
	public void setSelectedUser(User user) {
		formModel.setObject(new User(user.getFirstname(), user.getLastname()));
	}

	@Override
	public void setUsers(List<User> users) {
		userList.getChoices().clear();
		final List<String> userNames = new ArrayList<String>();
		for (int i = 0; i < users.size(); i++) {
			userNames.add(users.get(i).getFirstname() + " " + users.get(i).getLastname());

		}

		this.userList.setChoices(userNames);

	}

	@Override
	public void setWarningMessage(String warning) {
		warn(warning);
	}

	private void initializeComponents() {

		feedbackPanel = new FeedbackPanel("userViewFeedbackPanel") {
			@Override
			protected String getCSSClass(FeedbackMessage message) {
				switch (message.getLevel()) {
				case FeedbackMessage.INFO:
					return "infoFeedback";
				case FeedbackMessage.WARNING:
					return "warningFeedback";
				case FeedbackMessage.ERROR:
					return "errorFeedback";
				default:
					return super.getCSSClass(message);
				}

			}
		};
		feedbackPanel.setOutputMarkupId(true);
		feedbackPanel.setMaxMessages(1);

		this.saveUserButton = new AjaxButton("userViewSaveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> userForm) {
				presenter.saveAction(target);
				target.addComponent(feedbackPanel);
				target.addComponent(userList);
			}
		};

		removeUserButton = new AjaxButton("userViewRemoveButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> userForm) {
				presenter.removeAction();
				target.addComponent(form);
				target.addComponent(feedbackPanel);
			}
		};

		createNewUserButton = new AjaxButton("userViewCreateButton") {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> userForm) {
				presenter.createNewUserAction();
				target.addComponent(feedbackPanel);
				target.addComponent(form);
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
		info("Hier kannst Du Benutzer hinzufügen, bearbeiten oder löschen.");
	}
}
