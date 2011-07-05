package de.hypoport.einarbeitung;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;


public class ZweitePage extends WebPage{

	
	public ZweitePage() {
		BookmarkablePageLink<WebPage> link = new BookmarkablePageLink<WebPage>("erste", StartInsGlueckPage.class);
		link.setParameter("greeting","Willkommen zurück!");
		add(link);

	}
}
