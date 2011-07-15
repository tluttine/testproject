package de.hypoport.finn;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.Model;

public class LightsOutPage extends WebPage {

	public LightsOutPage() {
		super();
		LightsOutGrid log = new LightsOutGrid(5, 5);
		add(new PropertyListView<CellBean>("grid", Model.of(log.getGrid())) {

			@Override
			protected void populateItem(ListItem<CellBean> item) {
				 	item.add(new Label("cellNumber"));			
			}
				
		});
	}	
}
