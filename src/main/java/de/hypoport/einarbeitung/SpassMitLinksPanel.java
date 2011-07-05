package de.hypoport.einarbeitung;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;


public class SpassMitLinksPanel extends Panel {

	public SpassMitLinksPanel(String id, IModel<Integer> counter, final Label label) {
		super(id, counter);
		
		add(new AjaxFallbackLink<Integer>("link",counter) {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				if (target!=null) {
					target.addComponent(label);
				}
				setModelObject(1+getModelObject());
			}
		});
	}

}
