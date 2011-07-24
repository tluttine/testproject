package de.hypoport.finn;

import java.awt.Color;
import java.util.Iterator;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class LightsOutPage extends WebPage {

	private LightsOutGrid _log;
	private GridView<CellBean> _grid;
	
	public LightsOutPage() {
		super();
		add(new Label("titel", Model.of("The magnificent Lights Out Game")));
		_log = new LightsOutGrid(5, 5);
		
		IDataProvider<CellBean> cells = new IDataProvider<CellBean>() {
			@Override
			public void detach() {
			}

			@Override
			public int size() {
				return _log.size();
			}

			@Override
			public IModel<CellBean> model(CellBean object) {
				return Model.of(object);
			}

			@Override
			public Iterator<? extends CellBean> iterator(int first, int count) {
				return _log.getGrid().iterator();
			}
		};
		
		_grid = new GridView<CellBean>("grid", cells) {
			@Override
			protected void populateItem(final Item<CellBean> item) {
				Link link = new Link("link") {
					@Override
					public void onClick() {
						CellBean cell = item.getModelObject();
						for (Integer neighbour : cell.getNeighbours()) {
							_log.getCellBean(neighbour).toggleLight();
						}
					}
				};
				link.add(makeImage(item.getModelObject()));
				item.add(link);
			}

			@Override
			protected void populateEmptyItem(Item<CellBean> item) {
				item.add(new Label("label", Model.of("empty")));
			}
		};
		_grid.setColumns(_log.getColCount());
		_grid.setRows(_log.getRowCount());
		add(_grid);
	}

	private Image makeImage(CellBean cell) {
		DefaultButtonImageResource dbir = new DefaultButtonImageResource(100, 100,
				"");
		int light = (cell.isLightOn()) ? 200 : 0;
		dbir.setColor(new Color(20 + light, 20 + light, 20 + light));
		return new Image("image", dbir);
	}
}
