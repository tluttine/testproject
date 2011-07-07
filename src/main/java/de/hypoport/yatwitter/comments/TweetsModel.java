package de.hypoport.yatwitter.comments;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;


public class TweetsModel extends LoadableDetachableModel<List<? extends Tweet>> {

	@Override
	protected List<? extends Tweet> load() {
		return TweetContainer.getTweets(10);
	}
}