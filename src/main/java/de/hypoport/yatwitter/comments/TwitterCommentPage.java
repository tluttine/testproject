package de.hypoport.yatwitter.comments;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import de.hypoport.yatwitter.login.LoginLink;
import de.hypoport.yatwitter.login.LogoutLink;
import de.hypoport.yatwitter.login.sessions.OnlyWithLogin;
import de.hypoport.yatwitter.login.sessions.TwitterSession;

@OnlyWithLogin
public class TwitterCommentPage extends WebPage {

	private Form<String> commentForm;

	public TwitterCommentPage() {
		add(new FeedbackPanel("feedback"));
		
		final Model<String> commentAreaModel = Model.of("");
		commentForm = new Form<String>("commentFormId") {

			@Override
			protected void onSubmit() {
				final String comment = commentAreaModel.getObject();
				final TwitterSession session = (TwitterSession) getSession();
				final String username = session.getLoggedUsername();

				TweetContainer.addTweet(username, comment);
				setResponsePage(TweetsPage.class);
			}
		};
		
		TextField<String> textArea = new TextField<String>("textAreaComment", commentAreaModel);
		textArea.setRequired(true);
		textArea.add(new StringValidator.MinimumLengthValidator(2));
		textArea.add(new StringValidator.MaximumLengthValidator(140));
		commentForm.add(textArea);
		
		add(commentForm);
		add(new LogoutLink("logoutLink"));
		add(new LoginLink("loginLink"));
	}
}
