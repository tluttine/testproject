package de.hypoport.yatwitter.events;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;


public class NewTweetEvent extends AbstractEvent {

	public NewTweetEvent(MarkupContainer source) {
		super(source);
	}

}
