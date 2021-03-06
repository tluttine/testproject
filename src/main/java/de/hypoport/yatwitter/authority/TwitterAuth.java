package de.hypoport.yatwitter.authority;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;

import de.hypoport.yatwitter.annotation.OnlyWithLogin;
import de.hypoport.yatwitter.session.TwitterSession;


public class TwitterAuth implements IAuthorizationStrategy {

	@Override
	public <T extends Component> boolean isInstantiationAuthorized(Class<T> componentClass) {
		if (Page.class.isAssignableFrom(componentClass)) {
			if (componentClass.getAnnotation(OnlyWithLogin.class)!=null) {
				return TwitterSession.get().hasValidLogin();
			}
		}
		return true;
	}

	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

}
