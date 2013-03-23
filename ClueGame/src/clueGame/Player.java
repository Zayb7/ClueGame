package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	protected String name;
	protected ArrayList<Card> cards;
	protected BoardCell location;
	protected Color color;
	protected ClueGame cg;

	public Player(String name, Color color, ClueGame cg) {
		this.name = name;
		this.color = color;
		this.cg = cg;
	}
	
	public Card disproveSuggestion(Card person, Card room, Card weapon){
		ArrayList<Card> results = new ArrayList<Card>(); 
		if(cards.contains(person)) 
			results.add(person); 
		if(cards.contains(weapon)) 
			results.add(weapon); 
		if(cards.contains(room)) 
			results.add(room); 
		Card theClue = null; 
		if(results.size() > 0) { 
			Random rand = new Random(); 
			int select = rand.nextInt(results.size()); 
			theClue = (Card)results.get(select); 
		} 
		return theClue;
	}
	
	public ArrayList<Card> getMyCards() {
		return cards;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void setLocation(BoardCell location) {
		this.location = location;
	}
	
	public BoardCell getLocation() {
		return location;
	}

	public void updateSeenCards(Card result) {
		
	}
	
}
