import java.util.*;


public class Board {

	//variables
	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private Map<Character, String> rooms;
	private int numRows, numColumns;
	
	
	//methods
	public void loadConfigFiles(){
		
	}
	
	public int calcIndex(int row, int col){
		return 0;
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
