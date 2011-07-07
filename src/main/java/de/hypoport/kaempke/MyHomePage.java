package de.hypoport.kaempke;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import de.hypoport.kaempke.view.UserView;

public class MyHomePage extends WebPage {
	// TODO Add any page properties or variables here

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
	public MyHomePage(final PageParameters parameters) {
		add(new UserView("userView"));
	}
}
