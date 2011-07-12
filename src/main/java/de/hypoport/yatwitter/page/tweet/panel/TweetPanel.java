package de.hypoport.yatwitter.page.tweet.panel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.hypoport.yatwitter.dao.LikeDao;
import de.hypoport.yatwitter.dto.Like;
import de.hypoport.yatwitter.dto.Tweet;
import de.hypoport.yatwitter.session.TwitterSession;

public class TweetPanel extends Panel {

	private String variation;
	private Model<Integer> counter;
	private Label labelLikeCounter;

	@SpringBean(name = LikeDao.BEAN_ID)
	private LikeDao likeDao;

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

				final Like like = new Like(new Like.Key(tweet.getId(), loggedUser));

				if (likeDao.get(like.getId()) != null) {
					return;
				}

				likeDao.save(like);
				final int size = likeDao.getLikesForTweet(tweet).size();
				counter.setObject(size);
				target.addComponent(labelLikeCounter);

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
