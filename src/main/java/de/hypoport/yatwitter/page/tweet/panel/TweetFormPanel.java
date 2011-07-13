package de.hypoport.yatwitter.page.tweet.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import de.hypoport.yatwitter.dao.TweetDao;
import de.hypoport.yatwitter.dto.Tweet;
import de.hypoport.yatwitter.event.NewTweetEvent;
import de.hypoport.yatwitter.page.component.LogoutLink;
import de.hypoport.yatwitter.session.TwitterSession;

public class TweetFormPanel extends Panel {

	@SpringBean(name = TweetDao.BEAN_ID)
	private TweetDao tweetDao;

	public TweetFormPanel(String id) {
		super(id);

		final IModel<String> commentAreaModel = Model.of("");
		final Form<String> commentForm = new Form<String>("commentFormId");

		TextArea<String> textArea = new TextArea<String>("textAreaComment", commentAreaModel);
		textArea.setRequired(true);
		textArea.add(new StringValidator.MinimumLengthValidator(2));
		textArea.add(new StringValidator.MaximumLengthValidator(140));
		commentForm.add(textArea);

		commentForm.add(new AjaxButton("submit") {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				final String comment = commentAreaModel.getObject();
				final TwitterSession session = (TwitterSession) getSession();
				final String username = session.getLoggedUsername();
				final Tweet tweet = new Tweet(username, comment);
				tweetDao.save(tweet);
				commentAreaModel.setObject("");
				new NewTweetEvent(this).fire();
				target.addComponent(commentForm);
			}
		});
		commentForm.add(new LogoutLink("logoutLink", Model.of("abmelden")));
		commentForm.add(new Label("tweetCommentTitle", Model.of("Was machst Du?")));
		add(commentForm);
	}
}
