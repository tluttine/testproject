package de.hypoport.yatwitter.page.tweet.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import de.hypoport.yatwitter.dao.TweetDao;
import de.hypoport.yatwitter.dto.Tweet;
import de.hypoport.yatwitter.event.NewTweetEvent;
import de.hypoport.yatwitter.session.TwitterSession;

public class TweetFormPanel extends Panel {

	@SpringBean(name = TweetDao.BEAN_ID)
	private TweetDao tweetDao;

	public TweetFormPanel(String id) {
		super(id);

		final Model<String> commentAreaModel = Model.of("");
		Form<String> commentForm = new Form<String>("commentFormId") {

			@Override
			protected void onSubmit() {
				final String comment = commentAreaModel.getObject();
				final TwitterSession session = (TwitterSession) getSession();
				final String username = session.getLoggedUsername();
				final Tweet tweet = new Tweet(username, comment);
				tweetDao.save(tweet);
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
