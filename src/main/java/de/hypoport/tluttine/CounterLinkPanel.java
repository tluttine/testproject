package de.hypoport.tluttine;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class CounterLinkPanel extends Panel {


	public CounterLinkPanel(String id, IModel<Integer> counter, final Label label, final IModel<Integer> inc) {
		super(id, counter);
		
		add(new CounterLink("counterLink", counter, label, inc));
	}
}
