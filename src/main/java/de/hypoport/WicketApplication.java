package de.hypoport;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;


import de.hypoport.einarbeitung.StartInsGlueckPage;
import de.hypoport.kaempke.view.UserView;
import de.hypoport.finn.RandomNumberPage;
import de.hypoport.einarbeitung.edittest.EditPage;

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
		return HomePage.class;
	}

}
