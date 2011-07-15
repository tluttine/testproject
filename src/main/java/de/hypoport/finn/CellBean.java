package de.hypoport.finn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CellBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int row;
	public int col;
	public int rowCount, colCount;
	
	public CellBean(int row, int col, int rowCount, int colCount) {
		this.row = row;
		this.col = col;
		this.rowCount = rowCount;
		this.colCount = colCount;
	}
	
	public int getCellNumber() {
		return row * colCount + col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return "CellBean [row=" + row + ", col=" + col + "]";
	}

	public List<Integer> getNeighbours() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (row > 0) { // upper neighbour
			result.add((row - 1) * colCount + col);
		}
		if (col > 0) { //left neighbour
			result.add((row * colCount) + col - 1);
		}
		result.add(getCellNumber()); // me
		if (col < colCount - 1) { //right neighbour
			result.add((row * colCount) + col + 1);
		}
		if (row < rowCount - 1) { // lower neighbour
			result.add((row + 1) * colCount + col);
		}
		return result;
	}

	private Integer calculateNeighbourNumbers() {
		return null;
	}
	
	
}