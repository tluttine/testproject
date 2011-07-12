package de.hypoport.yatwitter.event;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;

import de.hypoport.yatwitter.listener.IEventListener;


public class AbstractEvent {

	private final MarkupContainer source;
	private AjaxRequestTarget ajaxRequestTarget;

	protected AbstractEvent(MarkupContainer source) {
		this.source = source;
		this.ajaxRequestTarget = AjaxRequestTarget.get();
	}
	
	public void fire() {
		source.getPage().visitChildren(IEventListener.class, new Component.IVisitor<Component>() {
			@Override
			public Object component(Component component) {
				IEventListener eventCom=(IEventListener) component;
				eventCom.notify(AbstractEvent.this);
				return Component.IVisitor.CONTINUE_TRAVERSAL;
			}
		});
		if (source.getPage() instanceof IEventListener) {
			IEventListener epage=(IEventListener) source.getPage();
			epage.notify(AbstractEvent.this);
		}
	}

	public void update(Component component) {
		if (ajaxRequestTarget!=null) {
			ajaxRequestTarget.addComponent(component);
		}
	}
}
