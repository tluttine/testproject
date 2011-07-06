package de.hypoport.einarbeitung;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class StartInsGlueckPage extends WebPage {
	
	public StartInsGlueckPage(PageParameters pageParameters) {
		
		add(new FeedbackPanel("feedback"));
		
		Model<UserName> userModel=Model.of(new UserName());
		Form<UserName> form = new Form<UserName>("form",userModel){
			@Override
			protected void onSubmit() {
				UserName user = getModelObject();
				info("User:" +user.getName());
				setModelObject(new UserName());
			}
		};
		
		form.add(new RequiredTextField("name", new PropertyModel(userModel, "name")));
		
		add(form);
		
		UserName user = new UserName();
		PersonForm personForm = new PersonForm("username",Model.of(""));
		personForm.add(new RequiredTextField("personname", new PropertyModel(user, "name")));
		add(personForm);

		// Seitenparameter
		String greeting=pageParameters.getString("greeting", user.getGreeting());
		add(new Label("label",new Model<String>(greeting)));
		
		// Komponenten
		add(new HalloWeltPanel("hallo",Model.of("Hallo")));
		add(new HalloWeltPanel("hallo2",Model.of("Klaus")));
		
		// Links
		Model<Integer> counter = Model.of(0);
		Model<Integer> inc1 = Model.of(1);
		Model<Integer> inc2 = Model.of(2);

		Label label = new Label("counter",counter);
		label.setOutputMarkupId(true);
		add(label);
		
		add(new SpassMitLinksPanel("link1", counter,inc1,label));
		add(new SpassMitLinksPanel("link2", counter,inc2,label));
		
		add(new BookmarkablePageLink<ZweitePage>("zweite", ZweitePage.class));
		
		add(new Link<Void>("zweiteAnders") {
			@Override
			public void onClick() {
				setResponsePage(ZweitePage.class);
			}
		});
	}

	public final class PersonForm extends Form {

		public PersonForm(String id, IModel model) {
			super(id);
		}
		
	}
}
