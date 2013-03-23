package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class ClueGame {
	
	private ArrayList<Card> cards;
	private ArrayList<Player> players;
	private HumanPlayer human;
	private int turn;
	
	private String playerSolution = "Professor Plum", weaponSolution = "Wrench", roomSolution = "Kitchen";
	
	public ClueGame(int I_DONT_KNOW, int numberOfPlayers, Board board){
		
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

	public Card handleSuggestion(Card person, Card room, Card weapon, Player accusingPerson) {
		Random random = new Random();
		int nextPlayer = random.nextInt(players.size());
		Card result = null; 
		for(int playersAsked = 0; playersAsked < players.size(); playersAsked++) { 
			Player player = (Player)players.get(0); 
			if(nextPlayer == players.size()) { 
				nextPlayer = 0; 
			} 
			else { 
				player = (Player)players.get(nextPlayer); 
				nextPlayer++; 
			} 
			if(player != accusingPerson) { 
				result = player.disproveSuggestion(person, room, weapon); 
				if(result != null) { 
					for(Player p : players) 
						p.updateSeenCards(result);
					return result; 
				} 
			} 
		}   
		return null;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public ArrayList<Player> getPlayers() {
		return null;
	}

}
