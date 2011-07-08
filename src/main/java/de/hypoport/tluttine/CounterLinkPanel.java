package de.hypoport.tluttine;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


public class CounterLinkPanel extends Panel {

	public CounterLinkPanel(String id, IModel<Integer> counter,final IModel<Integer> inc, final Label label) {
		super(id, counter);
		
		add(new AjaxFallbackLink<Integer>("counterLink",counter) {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				if (target!=null) {
					target.addComponent(label);
				}
				setModelObject(inc.getObject()+getModelObject());
			}
		});
	}

}
