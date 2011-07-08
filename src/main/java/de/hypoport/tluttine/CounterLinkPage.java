package de.hypoport.tluttine;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class CounterLinkPage extends WebPage {
	
	public CounterLinkPage(PageParameters pageParameters) {

		// Links
		Model<Integer> counter = Model.of(0);
		Label label = new Label("counter",counter);
		label.setOutputMarkupId(true);
		add(label);
		
		add(new CounterLink("link1", counter, label, Model.of(1)));
		add(new CounterLink("link2", counter, label, Model.of(2)));
		add(new DiceLink("link3", counter, label, Model.of(3)));

	}

}
