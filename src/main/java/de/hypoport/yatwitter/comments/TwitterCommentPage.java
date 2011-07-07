package de.hypoport.yatwitter.comments;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converters.DateConverter;
import org.apache.wicket.util.time.Duration;

import de.hypoport.yatwitter.comments.TweetContainer.Tweet;
import de.hypoport.yatwitter.events.AbstractEvent;
import de.hypoport.yatwitter.events.IEventListener;
import de.hypoport.yatwitter.events.NewTweetEvent;
import de.hypoport.yatwitter.login.sessions.OnlyWithLogin;
import de.hypoport.yatwitter.pages.AbstractBasePage;

@OnlyWithLogin
public class TwitterCommentPage extends AbstractBasePage implements IEventListener {

	private Label dateLabel;

	public TwitterCommentPage() {
		
		setTitle("Eigene Tweets");
		setHeadline("Was machst Du?");
		
		add(new NewTweetPanel("newtweets"));
		add(new TweetsPanel("tweets", (IModel<? extends List<? extends Tweet>>) new TweetsModel()));
		
		IModel<Date> dateModel=new LoadableDetachableModel<Date>() {
			@Override
			protected Date load() {
				return new Date();
			}
		};
		dateLabel = new Label("datum",dateModel) {
			@Override
			public IConverter getConverter(Class<?> type) {
				return new DateConverter() {
					@Override
					public DateFormat getDateFormat(Locale locale) {
						return DateFormat.getDateTimeInstance();
					}
				};
			}
		};
		dateLabel.setOutputMarkupId(true);
		
		dateLabel.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(5)));
		add(dateLabel);

	}
	
	public void notify(AbstractEvent event) {
		if (event instanceof NewTweetEvent) {
			event.update(dateLabel);
		}
		
	}
}
