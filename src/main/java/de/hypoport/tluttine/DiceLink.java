package de.hypoport.tluttine;

import java.util.Random;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public class DiceLink extends CounterLink {

	private final static int DICE_MAX = 6;

	Random dice;
	
	DiceLink(String id, IModel<Integer> model, Label label, IModel<Integer> inc) {
		super(id, model, label, inc);
		dice = new Random();
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		if (target!=null) {
			target.addComponent(label);
		}
		setModelObject(dice.nextInt(DICE_MAX) + 1);
	}

}
