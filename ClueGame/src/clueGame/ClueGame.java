package clueGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ClueGame {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Player> players;
	private HumanPlayer humanPlayer;
	private int turn;
	
	private String playerSolution = "Professor Plum", weaponSolution = "Wrench", roomSolution = "Kitchen";
	
	public ClueGame(int numberOfPlayers, Board board){
		
	}

	public void start() {
		initializePlayers();
		initializeCards();
		deal();
	}

	public Player getHuman() {
		return humanPlayer;
	}
	
	public void deal() {
		int numberOfPlayers = players.size(); 
		Player player = players.get(0); 
		for(Card c : cards) { 
			player.addCard(c);
			if(numberOfPlayers == players.size()) { 
				player = humanPlayer; 
				numberOfPlayers = 0; 
			} 
			else { 
				player = (Player)players.get(numberOfPlayers); 
				ComputerPlayer comp = (ComputerPlayer) player; 
				comp.updateSeenCards(c); 
				numberOfPlayers++; 
			} 
		}
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
		return players;
	}

	public void initializePlayers() {
		humanPlayer = new HumanPlayer(SetUp.people[0], SetUp.colors[0], this); 
		players = new ArrayList<Player>(); 
		for(int i = 0; i < 5; i++) {
			players.add(new ComputerPlayer(SetUp.people[i], SetUp.colors[i], this));
		}
	}
	
	public void initializeCards() {
		ArrayList<String> people = new ArrayList<String>(Arrays.asList(SetUp.people));
		ArrayList<String> weapons = new ArrayList<String>(Arrays.asList(SetUp.weapons)); 
		ArrayList<String> rooms = new ArrayList<String>(Arrays.asList(SetUp.weapons)); 
		createCards(people, Card.CardType.PERSON); 
		createCards(weapons, Card.CardType.WEAPON); 
		createCards(rooms, Card.CardType.ROOM);
	}
	
	public void createCards(ArrayList<String> names, Card.CardType type) {
		for(String s: names) {
			Card c = new Card(s, type);
			cards.add(c);
		}
	}
}
