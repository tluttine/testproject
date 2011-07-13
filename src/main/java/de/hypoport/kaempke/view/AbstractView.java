package de.hypoport.kaempke.view;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class AbstractView extends Panel {

	private FeedbackPanel feedbackPanel;

	public AbstractView(String id) {
		super(id);
		initializeComponents();
	}

	private void initializeComponents() {
		feedbackPanel = new FeedbackPanel("feedbackPanel") {
			@Override
			protected String getCSSClass(FeedbackMessage message) {
				switch (message.getLevel()) {
				case FeedbackMessage.INFO:
					return "infoFeedback";
				case FeedbackMessage.WARNING:
					return "warningFeedback";
				case FeedbackMessage.ERROR:
					return "errorFeedback";
				default:
					return super.getCSSClass(message);
				}
			}
		};
		feedbackPanel.setOutputMarkupId(true);
		feedbackPanel.setMaxMessages(1);
		add(feedbackPanel);
		// add(new Label("labelTitle", Model.of("Benutzer Management")));
	}

}
