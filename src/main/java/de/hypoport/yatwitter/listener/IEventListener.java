package de.hypoport.yatwitter.listener;

import de.hypoport.yatwitter.event.AbstractEvent;


public interface IEventListener {
	void notify(AbstractEvent event);
}
