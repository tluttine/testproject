package de.hypoport.yatwitter.comments;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.comments.TweetContainer.Tweet;


public class TweetPanel extends Panel {

	public TweetPanel(String id, IModel<? extends Tweet> tweetModel) {
		super(id);
		
		Tweet tweet = tweetModel.getObject();
		
		add(new Label("user",tweet.getUser()));
		add(new Label("message",tweet.getMessage()));
		add(new Label("date",Model.of(tweet.getDate())));

	}

}
