package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player {

	private String guess;
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

	public void makeSuggestion(ArrayList<Card> myCards) {
		Card roomGuess = new Card(getLocation()., Card.CardType.ROOM); 
		Card personGuess = pickOne(GameInfo.strPeople, Card.CardType.PERSON); 
		Card weaponGuess = pickOne(GameInfo.strWeapons, Card.CardType.WEAPON);
		gc.setGuess((new StringBuilder(string.valueOf(personGuess.getCardName()))).append(" ").append(roomGuess.getCardName()).append(" ").append(weaponGuess.getCardName()).toString()); 
		Card response = gc.testSuggestion(personGuess, roomGuess, weaponGuess, this); 
		if(response == null) 
		{ 
			boolean won = gc.checkAccusation(personGuess, weaponGuess, roomGuess); 
			if(won) joptionpane.showMessageDialog(null, (new StringBuilder("The computer just won, answer is ")).append(personGuess.getCardName()).append(" ").append(weaponGuess.getCardName()).append(" ").append(roomGuess.getCardName()).toString()); 
			else joptionpane.showMessageDialog(null, (new StringBuilder("The computer made an incorrect guess of ")).append(personGuess.getCardName()).append(" ").append(weaponGuess.getCardName()).append(" ").append(roomGuess.getCardName()).toString()); 
		}
	}

	public Card getGuess() {
		return null;
	}
	
	public void setGuess(String guess) {
		this.guess = guess;
	}

}
