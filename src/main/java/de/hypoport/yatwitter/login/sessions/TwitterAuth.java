package de.hypoport.yatwitter.login.sessions;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;


public class TwitterAuth implements IAuthorizationStrategy {

	public <T extends Component> boolean isInstantiationAuthorized(Class<T> componentClass) {
		if (Page.class.isAssignableFrom(componentClass)) {
			if (componentClass.getAnnotation(OnlyWithLogin.class)!=null) {
				return TwitterSession.get().hasValidLogin();
			}
		}
		return true;
	}

	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

}
