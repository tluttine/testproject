package de.hypoport.yatwitter.page.tweet.panel;

import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.hypoport.yatwitter.dto.Tweet;
import de.hypoport.yatwitter.event.AbstractEvent;
import de.hypoport.yatwitter.event.NewTweetEvent;
import de.hypoport.yatwitter.listener.IEventListener;


public class TweetListPanel extends Panel implements IEventListener {

	private WebMarkupContainer ajaxUpdate;

	public TweetListPanel(String id, IModel<? extends List<? extends Tweet>> model) {
		super(id);
		
		ajaxUpdate = new WebMarkupContainer("ajaxUpdate");
		ajaxUpdate.setOutputMarkupId(true);	
		
		ajaxUpdate.add(new ListView<Tweet>("tweets",model) {
			@Override
			protected void populateItem(ListItem<Tweet> item) {
				IModel<Tweet> tweetModel = item.getModel();
				item.add(new TweetPanel("tweet", tweetModel));
			}
		});
		
		add(ajaxUpdate);

	}

	@Override
	public void notify(AbstractEvent event) {
		if (event instanceof NewTweetEvent) {
			event.update(ajaxUpdate);
		}
	}
}
