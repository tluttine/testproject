package de.hypoport.kaempke;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebPage;

import de.hypoport.kaempke.view.UserView;
import de.hypoport.yatwitter.page.HomePage;

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
		
		ResourceReference cssRef = new ResourceReference(MyHomePage.class, "view/resources/css/kaempke.css");
		ResourceReference jsRef = new ResourceReference(MyHomePage.class, "view/resources/js/kaempke.css");
        add(CSSPackageResource.getHeaderContribution(cssRef));
        add(JavascriptPackageResource.getHeaderContribution(jsRef));

	}
}
