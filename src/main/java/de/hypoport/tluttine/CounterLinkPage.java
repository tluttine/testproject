package de.hypoport.tluttine;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class CounterLinkPage extends WebPage {
	
	public CounterLinkPage(PageParameters pageParameters) {

		// Links
		Model<Integer> counter = Model.of(0);
		Model<Integer> inc1 = Model.of(1);
		Model<Integer> inc2 = Model.of(2);

		Label label = new Label("counter",counter);
		label.setOutputMarkupId(true);
		add(label);
		
		add(new CounterLinkPanel("link1", counter,inc1,label));
		add(new CounterLinkPanel("link2", counter,inc2,label));

	}

}
