package de.hypoport.finn;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import de.hypoport.finn.CellBean;

public class CellBeanTest extends TestCase{
	private LightsOutGrid log;
	
	@Before
	public void setUp() {
		log = new LightsOutGrid(5,5);
	}
	
	@Test
	public void testStart() {
		assertEquals(5, log.getRowCount());
		assertEquals(5, log.getColCount());
	}

	@Test
	public void testCornerCellBeans() {
		CellBean upperLCorner = log.getCellBean(0);
		CellBean upperRCorner = log.getCellBean(4);
		CellBean lowerLCorner = log.getCellBean(20);
		CellBean lowerRCorner = log.getCellBean(24);
		assertEquals("row ulc", 0, upperLCorner.getRow());
		assertEquals("col ulc", 0, upperLCorner.getCol());

		assertEquals("row urc", 0, upperRCorner.getRow());
		assertEquals("col urc", 4, upperRCorner.getCol());
		
		assertEquals("row llc", 4, lowerLCorner.getRow());
		assertEquals("col llc", 0, lowerLCorner.getCol());
		
		assertEquals("row urc", 4, lowerRCorner.getRow());
		assertEquals("col urc", 4, lowerRCorner.getCol());
	}
	
	@Test
	public void testNeigborsInCorner() {
		CellBean upperLCorner = log.getCellBean(0);
		List<Integer> neighbours = upperLCorner.getNeighbours();
		assertEquals("upper corner", 3, neighbours.size());
		assertTrue("me is contained", neighbours.contains(0));
		assertTrue("right neigbour is contained", neighbours.contains(1));
		assertTrue("lower neighbour is contained", neighbours.contains(5));
	}
	
	@Test
	public void testNeigborsTopRow() {
		CellBean upperRowcell = log.getCellBean(2);
		List<Integer> neighbours = upperRowcell.getNeighbours();
		assertEquals(4, upperRowcell.getNeighbours().size());
		assertTrue("left neigbour is contained", neighbours.contains(1));
		assertTrue("me is contained", neighbours.contains(2));
		assertTrue("right neigbour is contained", neighbours.contains(3));
		assertTrue("lower neighbour is contained", neighbours.contains(7));
	}
	
	@Test
	public void testNeigborsLeftSide() {
		CellBean leftSideCell = log.getCellBean(15);
		List<Integer> neighbours = leftSideCell.getNeighbours();
		assertEquals(4, leftSideCell.getNeighbours().size());
		assertTrue("upper neigbour is contained", neighbours.contains(10));
		assertTrue("me is contained", neighbours.contains(15));
		assertTrue("right neigbour is contained", neighbours.contains(16));
		assertTrue("lower neighbour is contained", neighbours.contains(20));
	}
	
	@Test
	public void testNeigborsInTheCenter() {
		CellBean leftSideCell = log.getCellBean(18);
		List<Integer> neighbours = leftSideCell.getNeighbours();
		assertEquals(5, leftSideCell.getNeighbours().size());
		assertTrue("upper neigbour is contained", neighbours.contains(13));
		assertTrue("left neigbour is contained", neighbours.contains(17));
		assertTrue("me is contained", neighbours.contains(18));
		assertTrue("right neigbour is contained", neighbours.contains(19));
		assertTrue("lower neighbour is contained", neighbours.contains(23));
	}
}
