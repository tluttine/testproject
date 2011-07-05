package de.hypoport.einarbeitung;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

public class StartInsGlueckPage extends WebPage {
	
	public StartInsGlueckPage(PageParameters pageParameters) {
		// Seitenparameter
		String greeting=pageParameters.getString("greeting", "Nix da");
		add(new Label("label",new Model<String>(greeting)));
		
		// Komponenten
		add(new HalloWeltPanel("hallo",Model.of("Hallo")));
		add(new HalloWeltPanel("hallo2",Model.of("Klaus")));
		
		// Links
		Model<Integer> counter = Model.of(0);
		
		Label label = new Label("counter",counter);
		label.setOutputMarkupId(true);
		add(label);
		
		add(new SpassMitLinksPanel("link1", counter,label));
		add(new SpassMitLinksPanel("link2", counter,label));
		
		add(new BookmarkablePageLink<ZweitePage>("zweite", ZweitePage.class));
		
		add(new Link<Void>("zweiteAnders") {
			@Override
			public void onClick() {
				setResponsePage(ZweitePage.class);
			}
		});
	}
}
