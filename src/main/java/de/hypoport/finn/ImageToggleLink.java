package de.hypoport.finn;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ImageToggleLink<Boolean> extends AjaxLink<Boolean> {

	private CellBean _cell;
	private List<ImageToggleLink<Boolean>> _neighbours = Collections.EMPTY_LIST;

	public ImageToggleLink(String id, CellBean cell) {
		super(id, (IModel<Boolean>) Model.of(cell.isLightOn()));
		_cell = cell;
		add(makeImage());
	}

	private Image makeImage() {
		DefaultButtonImageResource dbir = new DefaultButtonImageResource(100, 100,
				"");
		int light = (_cell.isLightOn()) ? 200 : 0;
		dbir.setColor(new Color(20 + light, 20 + light, 20 + light));
		return new Image("image", dbir);
	}

	public void setNeighbours(List<ImageToggleLink<Boolean>> neighbours) {
		_neighbours = neighbours;
	}
	
	public void toggleCell() {
		_cell.toggleLight();
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		for (ImageToggleLink<Boolean> neighbour : _neighbours) {
			neighbour.toggleCell();
			target.addComponent(neighbour);
		}
	}
}
