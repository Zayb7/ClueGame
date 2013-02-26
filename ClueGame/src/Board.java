import java.io.*;
import java.util.*;


public class Board {

	//variables
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private Map<Character, String> rooms;
	private int numRows, numColumns;

	//Board Info
	private final int TOTAL_ROWS = 25;
	private final int TOTAL_COLS = 25;
	private LinkedList<Integer> adjacencies;
	private ArrayList<LinkedList<Integer>> listOfAdjacencies;
	
	//Scanner and File IO
	private Scanner scan;
	private FileReader fileIn;
	

	boolean [] visited = new boolean[TOTAL_ROWS*TOTAL_COLS];
	Set targets = new HashSet<Integer>();


	//Constructors	
	public Board(){
		//will use a known file that works for the legend and the layout
	}

	public Board(String layoutFile, String legendFile){
		loadConfigFiles(layoutFile, legendFile);
	}


	//methods
	public void calcAdjacencies(){
		for(int i = 0; i < TOTAL_ROWS; ++i) {
			for (int j = 0; j < TOTAL_COLS; ++j) {
				adjacencies = new LinkedList<Integer>();
				if (i-1 >= 0) {
					int xminus1 = calcIndex(i-1, j);
					adjacencies.add(xminus1);
				}
				if (j-1 >= 0) {
					int yminus1 = calcIndex(i, j-1);
					adjacencies.add(yminus1);
				}
				if (i+1 < TOTAL_ROWS) {
					int xplus1 = calcIndex(i+1, j);
					adjacencies.add(xplus1);
				}
				if (j+1 < TOTAL_COLS) {
					int yplus1 = calcIndex(i, j+1);
					adjacencies.add(yplus1);
				}
				listOfAdjacencies.add(adjacencies);
			}
		}
	}

	public LinkedList<Integer> getAdjList(int index){
		listOfAdjacencies = new ArrayList<LinkedList<Integer>>();
		calcAdjacencies();
		return listOfAdjacencies.get(index);
	}

	public void setVisitedTrue(int index) {
		visited[index] = true;
	}

	public void setVisitedFalse(int index) {
		visited[index] = false;
	}

	public void startTargets(int index, int steps){
		Arrays.fill(visited, false);
		targets = new HashSet<Integer>();
		visited[index] = true;
		calcTargets(index, steps);
	}

	public void calcTargets(int thisCell, int steps){	
		ArrayList<LinkedList<Integer>> tempAdjacencies = listOfAdjacencies;
		LinkedList<Integer> adjacentCells = tempAdjacencies.get(thisCell);
		LinkedList<Integer> adjacentCellsTemp = new LinkedList<Integer>();


		for(Integer i: adjacentCells){
			//System.out.println(i);
			if(!visited[i]){
				adjacentCellsTemp.add(i);
			} 
		}

		for (Integer i: adjacentCellsTemp) {
			//System.out.println(i);
			setVisitedTrue(i);
			if (steps == 1) {
				targets.add(i);
			} else {
				calcTargets(i, steps - 1);
			}

			setVisitedFalse(i);
		}
	}

	public Set getTargets(){
		//System.out.println(targets);
		return targets;
	}


	public int calcIndex(int row, int col){
		if(row > TOTAL_ROWS || row < 0 || col > TOTAL_COLS || col < 0){
			System.out.println("The row or column is out of bounds.");
		}

		return row*TOTAL_COLS + col;
	}

	public void loadConfigFiles(String layoutFile, String legendFile){
		loadRoomConfigFiles(legendFile);
		loadBoardConfigFiles(layoutFile);
	}

	public void loadRoomConfigFiles(String legendFile){
		try {
			fileIn = new FileReader(legendFile);
			scan = new Scanner(fileIn);
			while(scan.hasNext()){
				String c = scan.nextLine();
				System.out.println(c);
				char character = c.charAt(0);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadBoardConfigFiles(String layoutFile){

	}

	//getters 
	public RoomCell getRoomCellAt(int row, int col){
		return new RoomCell();
	}

	public BoardCell getCells(int index) {
		//return cells.get(index);
		return null;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}



}