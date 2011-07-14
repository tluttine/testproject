package de.hypoport.tluttine.dice;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.IModel;

public class DicePage extends WebPage {

	private int diceCount = 3;
	
	public DicePage(PageParameters pageParameters) {

		Model<Integer> counter = Model.of(diceCount);
		Label label = new Label("diceCounter",counter);
		label.setOutputMarkupId(true);

		Form<Integer> form = new Form<Integer>("diceForm")
		{
			@Override
			protected void onSubmit() {
				
			}
		};

		form.add(new Button("dicePlus", Model.of("+")) {
			@Override
			public void onSubmit() {
				diceCount++;
			}
		});
		form.add(new Button("diceMinus", Model.of("-")) {
			@Override
			public void onSubmit() {
				if (diceCount > 1)
					diceCount--;
			}
		});

		form.add(label);
		add(form);

	}

}
