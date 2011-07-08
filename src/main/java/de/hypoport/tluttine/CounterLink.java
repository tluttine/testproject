package de.hypoport.tluttine;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class CounterLink extends AjaxFallbackLink<Integer> {
	protected final Label label;
	protected final IModel<Integer> inc;

	CounterLink(String id, IModel<Integer> model, Label label,
			IModel<Integer> inc) {
		super(id, model);
		this.label = label;
		this.inc = inc;
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		if (target!=null) {
			target.addComponent(label);
		}
		setModelObject(inc.getObject()+getModelObject());
	}

}