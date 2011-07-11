package de.hypoport.yatwitter.comments;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.hypoport.yatwitter.login.sessions.TwitterSession;


public class TweetPanel extends Panel {

	private String variation;
	private Model<Integer> counter;
	private Label labelLikeCounter; 

	public TweetPanel(String id, IModel<? extends Tweet> tweetModel) {
		super(id);
		
		final Tweet tweet = tweetModel.getObject();
		
		String tweeter = tweet.getUser();
		Label userLabel = new Label("user",tweeter);
		add(userLabel);
		add(new Label("message",tweet.getMessage()));
		add(new Label("date",Model.of(tweet.getDate())));
		
		counter = Model.of(new Integer(tweet.getLikesCount()));
		labelLikeCounter = new Label("counter",counter);
		labelLikeCounter.setOutputMarkupId(true);
		add(labelLikeCounter);
		
		add(new AjaxLink<String>("ajaxLink",Model.of("Gefällt mir")) {

			@Override
			public void onClick(AjaxRequestTarget target) {
				String loggedUser = TwitterSession.get().getLoggedUsername();
				if ( null == loggedUser ) {
					return;
				}
				
				tweet.addLike(loggedUser);
				counter.setObject(tweet.getLikesCount());
				target.addComponent(labelLikeCounter);
				
				
			}
			
			@Override
			protected void onBeforeRender() {
				super.onBeforeRender();
				setRenderBodyOnly(null == TwitterSession.get().getLoggedUsername());
			}
			
		
			
		});
		
//		if (TwitterSession.get().isLoggedUser(loggedUserName)) {
//			userLabel.add(new AttributeModifier("style", true, Model.of("font-weight:bold")));
//			variation="active";
//		}

	}
	
	@Override
	public String getVariation() {
		return variation;
	}

}
