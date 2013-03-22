package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player {

	private Card personGuess, weaponGuess, roomGuess;
	private ArrayList<Card> seenCards = new ArrayList<Card>();

	public ComputerPlayer(String name, Color color, ClueGame cg) {
		super();
		cards = new ArrayList<Card>();
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets){
		int index = 0;
		return null;
	}

	public void updateSeenCards(Card card) {
		seenCards.add(card);
	}
	
	public ArrayList<Card> getSeenCards() {
		return seenCards;
	}

	public void makeSuggestion(ArrayList<Card> myCards) {
		Card roomGuess = new Card(getLocation().getRoomName(), Card.CardType.ROOM); 
		Card personGuess = pickOne(GameInfo.strPeople, Card.CardType.PERSON); 
		Card weaponGuess = pickOne(GameInfo.strWeapons, Card.CardType.WEAPON);
		setGuess(personGuess, weaponGuess, roomGuess); 
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
