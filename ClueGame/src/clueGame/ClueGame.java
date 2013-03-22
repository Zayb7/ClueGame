package clueGame;

import java.util.ArrayList;

public class ClueGame {
	
	private ArrayList<Card> cards;
	private ArrayList<ComputerPlayer> computers;
	private Player human;
	private int turn;
	
	private String playerSolution = "Professor Plum", weaponSolution = "Wrench", roomSolution = "Kitchen";
	
	public ClueGame(int I_DONT_KNOW, int numberOfPlayers, Board board){
		
	}

	public ArrayList<Player> getPlayers() {
		return null;
	}

	public Player getHuman() {
		return null;
	}
	
	public void Deal() {
		
	}
	
	public boolean checkAccusation(String player, String weapon, String room) {
		if (playerSolution.equals(player) && weaponSolution.equals(weapon) && roomSolution.equals(room)) {
			return true;
		}
		return false;
	}

	public Card handleSuggestion(String person, String room, String weapon, Player accusingPerson) {
		return null;
	}

	public void setPlayers(ArrayList<Player> players) {
		
	}

}
