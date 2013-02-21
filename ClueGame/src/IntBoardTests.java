import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;


public class IntBoardTests {
	//Setup board
	IntBoard board;
	
	@Before
	public void setUpBoard(){
		board = new IntBoard();
	} 
	
	// testing index
	@Test
	public void testCalcIndex(){
		assertEquals(3, board.calcIndex(0,3));
		assertEquals(8, board.calcIndex(0,2));
		assertEquals(10, board.calcIndex(2,2));
		assertEquals(14, board.calcIndex(3,2));
	}
	
	//testing adjacency lists
	@Test
	public void testAdjacencyCorner(){
		LinkedList testList = board.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacencyEdge(){
		LinkedList testList = board.getAdjList(2);
		Assert.assertTrue(testList.contains(1));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(3));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test 
	public void testAdjacencyOffBy1(){
		LinkedList testList = board.getAdjList(5);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(4, testList.size());
	}
	
	//testing path creation
	
	//testTargets"index"_"numOfSteps"(index, number of Steps)
	@Test
	public void testTargets0_3(){
		board.startTargets(0, 3);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}
	
	@Test 
	public void testTargets6_3(){
		board.startTargets(6, 3);
		Set targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(15));
	}
	
	@Test 
	public void testTargets7_2(){
		board.startTargets(7, 2);
		Set targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(15));
	}
	
	@Test 
	public void testTargets15_4(){
		board.startTargets(15, 4);
		Set targets= board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(10));
	}
	
	@Test 
	public void testTargets10_2(){
		board.startTargets(10, 2);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(15));
	}
	
	@Test 
	public void testTargets8_6(){
		board.startTargets(8, 6);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(15));
		
	}
	

}
