package de.hypoport.yatwitter.event;

import org.apache.wicket.MarkupContainer;

public class NewTweetEvent extends AbstractEvent {

	public NewTweetEvent(MarkupContainer source) {
		super(source);
	}

}
