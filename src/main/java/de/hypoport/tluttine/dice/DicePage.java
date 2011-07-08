package de.hypoport.tluttine.dice;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import de.hypoport.tluttine.CounterLink;

public class DicePage extends WebPage {
	
	public DicePage(PageParameters pageParameters) {

		// Links
		Model<Integer> counter = Model.of(3);
		Label label = new Label("diceCounter",counter);
		label.setOutputMarkupId(true);
		add(label);
		
		add(new CounterLink("addLink", counter, label, Model.of(1)));
		add(new CounterLink("decLink", counter, label, Model.of(-1)));
	}

}
