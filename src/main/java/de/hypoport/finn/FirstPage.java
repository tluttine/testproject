package de.hypoport.finn;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class FirstPage extends WebPage {

	public FirstPage() {
		add(new BookmarkablePageLink<WebPage>("firstPageLink", SecondPage.class));
	}
}
