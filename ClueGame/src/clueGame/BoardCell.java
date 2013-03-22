package clueGame;

public abstract class BoardCell {
	//variables
	protected int row, col;
	protected boolean isWalkway = false;
	protected boolean isDoor = false, isRoom = false;
	protected String roomName;
	
	//methods
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	//public abstract void draw();
	

}
