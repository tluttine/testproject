package de.hypoport.yatwitter.login.sessions;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.markup.html.WebPage;


public class TwitterAuthListener implements IUnauthorizedComponentInstantiationListener {

	private final Class<? extends WebPage> signInPageClass;

	
	public TwitterAuthListener(Class<? extends WebPage> signInPageClass) {
		this.signInPageClass = signInPageClass;
	}
	
	@Override
	public void onUnauthorizedInstantiation(Component component) {
		if (component instanceof Page)
		{
			// Redirect to page to let the user sign in
			throw new RestartResponseAtInterceptPageException(signInPageClass);
		}
		else
		{
			// The component was not a page, so throw exception
			throw new UnauthorizedInstantiationException(component.getClass());
		}

	}

}
