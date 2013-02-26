package clueGame;

public abstract class BoardCell {
	//variables
	private int row, col;
	
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
	
	//public abstract void draw();
	

}
