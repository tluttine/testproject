package de.hypoport.yatwitter.comments;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.hypoport.yatwitter.dao.TweetDao;
import de.hypoport.yatwitter.entity.Tweet;
import de.hypoport.yatwitter.login.sessions.TwitterSession;

public class TweetPanel extends Panel {

	private String variation;
	private Model<Integer> counter;
	private Label labelLikeCounter;

	@SpringBean(name = TweetDao.BEAN_ID)
	private TweetDao tweetDao;

	public TweetPanel(String id, IModel<? extends Tweet> tweetModel) {
		super(id);

		final Tweet tweet = tweetModel.getObject();

		String tweeter = tweet.getUser();
		Label userLabel = new Label("user", tweeter);
		add(userLabel);
		add(new Label("message", tweet.getMessage()));
		add(new Label("date", Model.of(tweet.getDate())));

		counter = Model.of(new Integer(tweet.getLikesCount()));
		labelLikeCounter = new Label("counter", counter);
		labelLikeCounter.setOutputMarkupId(true);
		add(labelLikeCounter);

		add(new AjaxLink<String>("ajaxLink", Model.of("Gefällt mir")) {

			@Override
			public void onClick(AjaxRequestTarget target) {
				String loggedUser = TwitterSession.get().getLoggedUsername();
				if (null == loggedUser) {
					return;
				}

				// final Like like = new Like(new Like.Key(tweet.getId(),
				// loggedUser));
				// if (tweet.getLikes().contains(like)) {
				// return;
				// }
				//
				// tweet.addLike(like);
				// tweetDao.save(tweet);
				// counter.setObject(tweet.getLikes().size());
				// target.addComponent(labelLikeCounter);

			}

			@Override
			protected void onBeforeRender() {
				super.onBeforeRender();
				setRenderBodyOnly(null == TwitterSession.get().getLoggedUsername());
			}

		});
	}

	@Override
	public String getVariation() {
		return variation;
	}

}
