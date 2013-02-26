
public class RoomCell extends BoardCell {
	
	//enumerated type door direction
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	
	//variables
	private DoorDirection doorDirection;
	char roomInitial;
	
	//methods
	@Override
	public boolean isRoom(){
		return true;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public char getRoomInitial() {
		return roomInitial;
	}
	
	
	
	//@Override
	//public void draw(){};
	
}