package de.hypoport.yatwitter.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.login.LoginPage;
import de.hypoport.yatwitter.login.LoginPanel;
import de.hypoport.yatwitter.login.LogoutLink;


public abstract class AbstractBasePage extends WebPage {

	
	private Model<String> titleModel;
	private Model<String> headlineModel;

	public AbstractBasePage() {
		titleModel = Model.of("Title");
		headlineModel = Model.of("Headline");
		
		add(new Label("title",titleModel));
		add(new Label("headline",headlineModel));
		add(new FeedbackPanel("feedback"));
		add(new LogoutLink("logoutLink"));
	}
	
	protected void setTitle(String title) {
		titleModel.setObject(title);
	}
	
	protected void setHeadline(String headline) {
		headlineModel.setObject(headline);
	}
}
