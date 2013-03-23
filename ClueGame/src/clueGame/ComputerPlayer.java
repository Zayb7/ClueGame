package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player {

	private Card personGuess, weaponGuess, roomGuess;
	private ArrayList<Card> seenCards = new ArrayList<Card>();
	private String lastRoom;

	public ComputerPlayer(String name, Color color, ClueGame cg) {
		super();
		cards = new ArrayList<Card>();
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		Random random = new Random();
		int select = random.nextInt(targets.size()); 
		int index = 0; 
		BoardCell result = null;
		for(BoardCell cell : targets) { 
			BoardCell location = cell; 
			if(location.isRoom() && !location.getRoomName().equals(lastRoom)) {
				lastRoom = location.getRoomName();
				return location; 
			}
			if(index == select) 
				result = location; 
			index++; 
		}   
		return result;
	}

	public void updateSeenCards(Card card) {
		seenCards.add(card);
	}
	
	public ArrayList<Card> getSeenCards() {
		return seenCards;
	}

	public void makeSuggestion(ArrayList<Card> myCards) {
		Card roomGuess = new Card(getLocation().getRoomName(), Card.CardType.ROOM); 
		Card personGuess = pickGuess(SetUp.people, Card.CardType.PERSON); 
		Card weaponGuess = pickGuess(SetUp.weapons, Card.CardType.WEAPON);
		setGuess(personGuess, weaponGuess, roomGuess); 
	}
	
	public Card pickGuess(String cards[], Card.CardType cardType) { 
		ArrayList<Card> unseenCards = new ArrayList<Card>(); 
		Random random = new Random();
		int j = cards.length; 
		for(int i = 0; i < j; i++) 
		{ 
			String card = cards[i]; 
			Card cardPicked = new Card(card, cardType); 
			if(!seenCards.contains(cardPicked)) 
				unseenCards.add(cardPicked); 
		}   
		if(unseenCards.size() == 0) 
			return null; 
		else 
			return (Card)unseenCards.get(random.nextInt(unseenCards.size())); 
	}

	public Card getPersonGuess() {
		return personGuess;
	}
	
	public Card getWeaponGuess() {
		return weaponGuess;
	}
	
	public Card getRoomGuess() {
		return roomGuess;
	}
	
	public void setGuess(Card person, Card weapon, Card room) {
		this.personGuess = person;
		this.weaponGuess = weapon;
		this.roomGuess = room;
	}

}
