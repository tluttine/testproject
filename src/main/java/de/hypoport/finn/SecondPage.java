package de.hypoport.finn;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class SecondPage extends WebPage {
	public SecondPage() {
		add(new BookmarkablePageLink<WebPage>("secondPageLink", FirstPage.class));
	}
}
