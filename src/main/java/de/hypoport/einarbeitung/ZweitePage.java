package de.hypoport.einarbeitung;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;


public class ZweitePage extends WebPage{

	
	public ZweitePage() {
		
		add(new BookmarkablePageLink<WebPage>("erste", StartInsGlueckPage.class));

	}
}
