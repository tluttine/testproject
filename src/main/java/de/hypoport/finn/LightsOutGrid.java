package de.hypoport.finn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightsOutGrid {
	int rowCount, colCount;
	List<CellBean> grid = new ArrayList<CellBean>();
	
	public LightsOutGrid(int rowCount, int colCount) {
		this.rowCount = rowCount;
		this.colCount = colCount;
		initGrid();
	}
	
	private void initGrid() {
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				grid.add(new CellBean(row, col, rowCount, colCount));
			}
		}
	}
   
	public int getRowCount() {
		return rowCount;
	}

	public int getColCount() {
		return colCount;
	}
	
	public CellBean getCellBean(int cellBeanNumber) {
		return grid.get(cellBeanNumber);
	}
	
	public List<CellBean> getGrid() {
		return Collections.unmodifiableList(grid);
	}
}
