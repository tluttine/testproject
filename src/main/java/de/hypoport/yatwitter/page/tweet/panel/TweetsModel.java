package de.hypoport.yatwitter.page.tweet.panel;

import java.util.List;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.hypoport.yatwitter.dao.TweetDao;
import de.hypoport.yatwitter.dto.Tweet;

public class TweetsModel extends LoadableDetachableModel<List<? extends Tweet>> {

	@SpringBean(name = TweetDao.BEAN_ID)
	private TweetDao tweetDao;

	public TweetsModel() {
		InjectorHolder.getInjector().inject(this);
	}

	@Override
	protected List<? extends Tweet> load() {
		return tweetDao.getLatestTweets(10);
	}
}