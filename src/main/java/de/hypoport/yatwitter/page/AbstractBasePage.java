package de.hypoport.yatwitter.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

public abstract class AbstractBasePage extends WebPage {

	private final Model<String> titleModel;

	public AbstractBasePage() {
		titleModel = Model.of("Title");
		add(new Label("title", titleModel));
		add(new FeedbackPanel("feedback"));
		info("Willkommen bei yatwitter");
	}

	protected void setTitle(String title) {
		titleModel.setObject(title);
	}
}
