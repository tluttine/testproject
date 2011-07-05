package de.hypoport;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	// TODO Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {

        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));

        // TODO Add your page's components here
        final WebMarkupContainer ajaxUpdate = new WebMarkupContainer("ajaxUpdate");
        ajaxUpdate.setOutputMarkupId(true);
        
        Model<Integer> counter = Model.of(0);
		Label counterLabel = new Label("counter",counter);
		ajaxUpdate.add(counterLabel);
		
		ajaxUpdate.add(new AjaxLink<Integer>("link",counter) {
			@Override
			public void onClick(AjaxRequestTarget target) {
				setModelObject(1+getModelObject());
				target.addComponent(ajaxUpdate);
			}
		});
		add(ajaxUpdate);
    }
}
