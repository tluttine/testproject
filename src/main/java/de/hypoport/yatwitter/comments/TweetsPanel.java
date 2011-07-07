package de.hypoport.yatwitter.comments;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.comments.TweetContainer.Tweet;


public class TweetsPanel extends Panel {

	public TweetsPanel(String id, IModel<? extends List<? extends Tweet>> model) {
		super(id);
		
		add(new ListView<Tweet>("tweets",model) {
			@Override
			protected void populateItem(ListItem<Tweet> item) {
				IModel<Tweet> tweetModel = item.getModel();
				item.add(new TweetPanel("tweet", tweetModel));
			}
		});
		

	}

}
