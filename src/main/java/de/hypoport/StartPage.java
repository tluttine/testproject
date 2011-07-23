package de.hypoport;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import de.hypoport.finn.RandomNumberPage;
import de.hypoport.kaempke.MyHomePage;



public class StartPage extends WebPage {

	public StartPage() {
		super();
		add(new StartPagePanel("panel"));	
	}
}
