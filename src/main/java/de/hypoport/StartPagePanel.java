package de.hypoport;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import de.hypoport.finn.LightsOutPage;
import de.hypoport.finn.RandomNumberPage;
import de.hypoport.kaempke.MyHomePage;
import de.hypoport.yatwitter.page.login.LoginPage;

public class StartPagePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StartPagePanel(String id) {
		super(id);
		add(new Label("label", Model.of("Choose the wanted startpage:")));
		add(new BookmarkablePageLink<MyHomePage>("steffenStartPage", MyHomePage.class));
		add(new BookmarkablePageLink<LightsOutPage>("finnStartPage", LightsOutPage.class));
		add(new BookmarkablePageLink<MyHomePage>("tuomasStartPage", MyHomePage.class));
		add(new BookmarkablePageLink<LoginPage>("twitterStartPage", LoginPage.class));
	}

}
