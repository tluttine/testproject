package de.hypoport.einarbeitung;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class HalloWeltPanel extends Panel {

	public HalloWeltPanel(String id,IModel<String> labelData) {
		super(id);
		
		add(new Label("label",labelData));
		
	}

}
