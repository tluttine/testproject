package de.hypoport.yatwitter.comments;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.comments.TweetContainer.Tweet;
import de.hypoport.yatwitter.login.sessions.TwitterSession;


public class TweetPanel extends Panel {

	private String variation;

	public TweetPanel(String id, IModel<? extends Tweet> tweetModel) {
		super(id);
		
		Tweet tweet = tweetModel.getObject();
		
		String user = tweet.getUser();
		Label userLabel = new Label("user",user);
		add(userLabel);
		add(new Label("message",tweet.getMessage()));
		add(new Label("date",Model.of(tweet.getDate())));

		if (TwitterSession.get().isLoggedUser(user)) {
			userLabel.add(new AttributeModifier("style", true, Model.of("font-weight:bold")));
			variation="active";
		}

	}
	
	@Override
	public String getVariation() {
		return variation;
	}

}
