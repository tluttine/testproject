package de.hypoport.yatwitter.comments;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.comments.TweetContainer.Tweet;
import de.hypoport.yatwitter.login.LogoutLink;
import de.hypoport.yatwitter.pages.AbstractBasePage;

public final class TweetsPage extends AbstractBasePage {
	
	public TweetsPage() {
		
		setTitle("Tweets");
		setHeadline("Tweets");
		
		add(new TweetsPanel("tweets", (IModel<? extends List<? extends Tweet>>) new TweetsModel()));
		
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
