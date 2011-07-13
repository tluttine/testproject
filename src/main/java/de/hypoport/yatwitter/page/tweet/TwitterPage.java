package de.hypoport.yatwitter.page.tweet;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converters.DateConverter;
import org.apache.wicket.util.time.Duration;

import de.hypoport.yatwitter.annotation.OnlyWithLogin;
import de.hypoport.yatwitter.event.AbstractEvent;
import de.hypoport.yatwitter.event.NewTweetEvent;
import de.hypoport.yatwitter.listener.IEventListener;
import de.hypoport.yatwitter.page.AbstractBasePage;
import de.hypoport.yatwitter.page.tweet.panel.TweetFormPanel;
import de.hypoport.yatwitter.page.tweet.panel.TweetListPanel;
import de.hypoport.yatwitter.page.tweet.panel.TweetsModel;

@OnlyWithLogin
public class TwitterPage extends AbstractBasePage implements IEventListener {

	private Label dateLabel;

	public TwitterPage() {

		setTitle("Eigene Tweets");

		add(new TweetFormPanel("newtweets"));
		add(new TweetListPanel("tweets", new TweetsModel()));

		IModel<Date> dateModel = new LoadableDetachableModel<Date>() {
			@Override
			protected Date load() {
				return new Date();
			}
		};
		dateLabel = new Label("datum", dateModel) {
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

	@Override
	public void notify(AbstractEvent event) {
		if (event instanceof NewTweetEvent) {
			event.update(dateLabel);
		}

	}
}
