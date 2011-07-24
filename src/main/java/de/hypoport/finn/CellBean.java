package de.hypoport.finn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CellBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _row, _col;
	private int _rowCount, _colCount;
	private boolean _lightOn = false;
	
	public CellBean(int row, int col, int rowCount, int colCount) {
		_row = row;
		_col = col;
		_rowCount = rowCount;
		_colCount = colCount;
	}
	
	public int getCellNumber() {
		return _row * _colCount + _col;
	}

	public int getRow() {
		return _row;
	}

	public void setRow(int row) {
		this._row = row;
	}

	public int getCol() {
		return _col;
	}

	public void setCol(int col) {
		this._col = col;
	}

	
	public boolean isLightOn() {
		return _lightOn;
	}

	@Override
	public String toString() {
		return "CellBean [row=" + _row + ", col=" + _col + "]";
	}

	public List<Integer> getNeighbours() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (_row > 0) { // upper neighbour
			result.add((_row - 1) * _colCount + _col);
		}
		if (_col > 0) { //left neighbour
			result.add((_row * _colCount) + _col - 1);
		}
		result.add(getCellNumber()); // me
		if (_col < _colCount - 1) { //right neighbour
			result.add((_row * _colCount) + _col + 1);
		}
		if (_row < _rowCount - 1) { // lower neighbour
			result.add((_row + 1) * _colCount + _col);
		}
		return result;
	}

	private Integer calculateNeighbourNumbers() {
		return null;
	}

	public void toggleLight() {
		_lightOn = !_lightOn;
	}
}