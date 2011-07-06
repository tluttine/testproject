package de.hypoport;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import page.ExcitabilityPage;

import de.hypoport.einarbeitung.StartInsGlueckPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see de.hypoport.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<? extends WebPage> getHomePage()
	{
		return ExcitabilityPage.class;
	}

}
