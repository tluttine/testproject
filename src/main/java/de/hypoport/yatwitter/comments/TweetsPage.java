package de.hypoport.yatwitter.comments;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.comments.TweetContainer.Tweet;
import de.hypoport.yatwitter.login.LogoutLink;

public final class TweetsPage extends WebPage{
	
	public TweetsPage() {
		
		IModel<? extends List<? extends Tweet>> model=new LoadableDetachableModel<List<? extends Tweet>>() {
			@Override
			protected List<? extends Tweet> load() {
				return TweetContainer.getTweets(10);
			}
		};
		
		add(new ListView<Tweet>("tweets",model) {
			@Override
			protected void populateItem(ListItem<Tweet> item) {
				item.add(new Label("user",item.getModelObject().getUser()));
				item.add(new Label("message",item.getModelObject().getMessage()));
				item.add(new Label("date",Model.of(item.getModelObject().getDate())));
			}
		});
		add(new Link<String>("commentLink") {
			@Override
			public void onClick() {
				setResponsePage(TwitterCommentPage.class);
			}
		});
		add(new LogoutLink("logoutLink"));
//		TextArea<String> textArea = new TextArea<String>("textAreaComments",Model.of(""));
//		
//		add(textArea);
		
	}	
}
