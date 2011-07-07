package de.hypoport.einarbeitung.edittest;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import de.hypoport.einarbeitung.UserName;

public class EditPage extends WebPage {

	public EditPage() {
		
		add(new FeedbackPanel("feedback"));
		
		final Model<User> userModel = Model.of(new User());
		
		add(new Label("name",new PropertyModel<String>(userModel, "name")));
		add(new Label("vorname",new PropertyModel<String>(userModel, "vorname")));
		
		final Model<User> userEditModel = Model.of(new User());
		final Form<User> form = new Form<User>("form",new CompoundPropertyModel<User>(userEditModel)) {
			private static final long serialVersionUID = 4585412852783406625L;

			@Override
			protected void onSubmit() {
				userModel.setObject(userEditModel.getObject());
				userEditModel.setObject(new User());
				setVisible(false);
			}
		};
		
		TextField<String> nameField = new TextField<String>("name");
		TextField<String> prenameField = new TextField<String>("vorname");
		nameField.setRequired(true);
		nameField.add(new StringValidator.MinimumLengthValidator(3));
		prenameField.setRequired(true);
		prenameField.add(new StringValidator.MinimumLengthValidator(3));
		form.add(nameField);
		form.add(prenameField);
		form.setVisible(false);
		add(form);

		add(new Link<Void>("editlink") {

			@Override
			public void onClick() {
				userEditModel.setObject(userModel.getObject());
				form.setVisible(true);
			}
		});
		
	}
}
