package de.hypoport.finn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightsOutGrid implements Serializable{
	int _rowCount, _colCount;
	List<CellBean> grid = new ArrayList<CellBean>();
	
	public LightsOutGrid(int rowCount, int colCount) {
		_rowCount = rowCount;
		_colCount = colCount;
		initGrid();
	}
	
	private void initGrid() {
		for (int row = 0; row < _rowCount; row++) {
			for (int col = 0; col < _colCount; col++) {
				grid.add(new CellBean(row, col, _rowCount, _colCount));
			}
		}
	}
   
	public int size() {
		return _rowCount * _colCount;
	}
	
	public int getRowCount() {
		return _rowCount;
	}

	public int getColCount() {
		return _colCount;
	}
	
	public CellBean getCellBean(int cellBeanNumber) {
		return grid.get(cellBeanNumber);
	}
	
	public List<CellBean> getGrid() {
		return Collections.unmodifiableList(grid);
	}
}
