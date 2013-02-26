import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.*;

import org.junit.*;



public class BoardTests {
	//Setup Board
	private final int NUM_ROOMS = 11;
	private final int NUM_ROWS =25,  NUM_COLUMNS = 25;
	private Board board;
	
	@Before
	public void setUpBoard(){
			board = new Board();
	}

	@Test
	public void testCalcIndex() {
//		assertEquals(3, board.calcIndex(0,3));
//		assertEquals(2, board.calcIndex(0,2));
//		assertEquals(10, board.calcIndex(2,2));
//		assertEquals(14, board.calcIndex(3,2));
		
		// Test each corner of the board
		assertEquals(0, board.calcIndex(0, 0));
		assertEquals(NUM_COLUMNS-1, board.calcIndex(0, NUM_COLUMNS-1));
		assertEquals(483, board.calcIndex(NUM_ROWS-1, 0));
		assertEquals(505, board.calcIndex(NUM_ROWS-1, NUM_COLUMNS-1));
		// Test a couple others
		assertEquals(24, board.calcIndex(1, 1));
		assertEquals(66, board.calcIndex(2, 20));		
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
	public void testTargets4_2(){
		LinkedList testList = board.getAdjList(0);
		board.startTargets(4, 2);
		Set targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
	}
	
	@Test
	public void testTargets0_3(){
		LinkedList testList = board.getAdjList(0);
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
		LinkedList testList = board.getAdjList(6);
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
		LinkedList testList = board.getAdjList(7);
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
		LinkedList testList = board.getAdjList(15);
		board.startTargets(15, 4);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(13));
	}
	
	@Test 
	public void testTargets10_2(){
		LinkedList testList = board.getAdjList(10);
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
		LinkedList testList = board.getAdjList(8);
		board.startTargets(8, 6);
		Set targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(15));
		
	}
	
	//TESTS FOR ROOMS AND BOARD 
	@Test
	public void testCorrectNumOfRooms(){
		//Testing size of Map rooms
		Map<Character, String> testMapRooms = board.getRooms();
		assertEquals(NUM_ROOMS, testMapRooms.size());
		
		//test that all the characters map to their respective room (C to 'Conservatory')
		assertEquals("Walkway", testMapRooms.get('W'));
		assertEquals("Conservatory", testMapRooms.get('C'));
		assertEquals("Billiard room", testMapRooms.get('R'));
		assertEquals("Library", testMapRooms.get('L'));
		assertEquals("Dining room", testMapRooms.get('D'));

	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	
	
	// Test a doorway in each direction, plus two cells that are not
	// a doorway.
	// These cells are white on the planning spreadsheet
	@Test
	public void FourDoorDirections() {
		// Test one each RIGHT/LEFT/UP/DOWN
		RoomCell room = board.getRoomCellAt(3, 3);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(2, 10);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(9, 18);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		room = board.getRoomCellAt(19, 12);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getRoomCellAt(14, 14);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCells(board.calcIndex(12, 16));
		assertFalse(cell.isDoorway());		

	}
	
	// Test that we have the correct number of doors
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		Assert.assertEquals(506, totalCells);
		for (int i=0; i<totalCells; i++)
		{
			BoardCell cell = board.getCells(i);
			if (cell.isDoorway())
				numDoors++;
		}
		Assert.assertEquals(16, numDoors);
	}
	
	
	// Test a few room cells to ensure the room initial is
	// correct.
	@Test
	public void testRoomInitials() {
		assertEquals('C', board.getRoomCellAt(0, 0).getRoomInitial());
		assertEquals('R', board.getRoomCellAt(4, 8).getRoomInitial());
		assertEquals('B', board.getRoomCellAt(9, 0).getRoomInitial());
		assertEquals('O', board.getRoomCellAt(21, 22).getRoomInitial());
		assertEquals('K', board.getRoomCellAt(21, 0).getRoomInitial());
	}
	
	
	
	// Test that an exception is thrown for a bad config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		// overloaded Board ctor takes config file names
		Board b = new Board("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		// You may change these calls if needed to match your function names
		// My loadConfigFiles has a try/catch, so I can't call it directly to
		// see test throwing the BadConfigFormatException
		b.loadConfigFiles();
	}
	
	
	// Test that an exception is thrown for a bad config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		// overloaded Board ctor takes config file name
		Board b = new Board("ClueLayoutBadRoom.csv", "ClueLegend.txt");
		b.loadConfigFiles();
	}
	
	
	// Test that an exception is thrown for a bad config file
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		// overloaded Board ctor takes config file name
		Board b = new Board("ClueLayout.csv", "ClueLegendBadFormat.txt");
		b.loadConfigFiles();
	}

}
