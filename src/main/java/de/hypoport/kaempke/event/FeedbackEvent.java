package de.hypoport.kaempke.event;

import org.apache.wicket.MarkupContainer;

import de.hypoport.yatwitter.event.AbstractEvent;

public final class FeedbackEvent extends AbstractEvent {

	protected FeedbackEvent(MarkupContainer source) {
		super(source);
	}

}
