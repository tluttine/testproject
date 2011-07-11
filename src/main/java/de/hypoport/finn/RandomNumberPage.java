package de.hypoport.finn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

public class RandomNumberPage extends WebPage {
<<<<<<< HEAD
    
    public RandomNumberPage() {
        List<Integer> permutation = getNumbers();
        for (int i = 0; i < 9; i++) {
            add(new Label("lbl" + i, Model.of(permutation.get(i))));
        }
        add(new Link("link") {
                @Override
                    public void onClick() {
                    RandomNumberPage.this.visitChildren(new IVisitor<Component>() {
                            public Object component(Component component) {
                                if (component instanceof Label) {
                                    Object data = component.getDefaultModelObject();
                                    if (data instanceof Integer) {
                                        int val = (Integer)data;
                                        if ((val % 3) == 0) {
                                            component.setVisible(!component.isVisible());
                                        }
                                    }
                                }
                                return IVisitor.CONTINUE_TRAVERSAL;
                            };
                        });
                }
            });
        add(new BookmarkablePageLink<RandomNumberPage>("reset", RandomNumberPage.class));
    }
        
    private List<Integer> getNumbers(){
        List<Integer> result = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Collections.shuffle(result, new Random(System.currentTimeMillis()));
        return result;
    }
=======

	public RandomNumberPage() {
		List<Integer> permutation = getNumbers();
		for (int i = 0; i < 9; i++) {
			add(new Label("lbl" + i, Model.of(permutation.get(i))));
		}
		add(new Link("link") {
			@Override
			public void onClick() {
				RandomNumberPage.this.visitChildren(new IVisitor<Component>() {
					@Override
					public Object component(Component component) {
						if (component instanceof Label) {
							Object data = component.getDefaultModelObject();
							if (data instanceof Integer) {
								int val = (Integer)data;
								if ((val % 3) == 0) {
									component.setVisible(!component.isVisible());
								}
							}
						}
						return IVisitor.CONTINUE_TRAVERSAL;
					};
				});
			}
		});
		add(new BookmarkablePageLink<RandomNumberPage>("reset", RandomNumberPage.class));
	}
	
	private List<Integer> getNumbers(){
		List<Integer> result = Arrays.asList(1,2,3,4,5,6,7,8,9);
		Collections.shuffle(result, new Random(System.currentTimeMillis()));
		return result;
	}
>>>>>>> 89dfb44a2f094d80443ed705a01141403057ea9e
}
