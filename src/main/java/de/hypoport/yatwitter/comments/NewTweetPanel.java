package de.hypoport.yatwitter.comments;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import de.hypoport.yatwitter.events.NewTweetEvent;
import de.hypoport.yatwitter.login.sessions.TwitterSession;


public class NewTweetPanel extends Panel {

	public NewTweetPanel(String id) {
		super(id);
		
		final Model<String> commentAreaModel = Model.of("");
		Form<String> commentForm = new Form<String>("commentFormId") {

			@Override
			protected void onSubmit() {
				final String comment = commentAreaModel.getObject();
				final TwitterSession session = (TwitterSession) getSession();
				final String username = session.getLoggedUsername();

				TweetContainer.addTweet(username, comment);
//				setResponsePage(TweetsPage.class);
				commentAreaModel.setObject("");
			}
		};
		
		TextField<String> textArea = new TextField<String>("textAreaComment", commentAreaModel);
		textArea.setRequired(true);
		textArea.add(new StringValidator.MinimumLengthValidator(2));
		textArea.add(new StringValidator.MaximumLengthValidator(140));
		commentForm.add(textArea);
		
		commentForm.add(new AjaxButton("submit") {
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				new NewTweetEvent(this).fire();
			}
		});
		
		add(commentForm);
	}

}