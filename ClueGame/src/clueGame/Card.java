package clueGame;

public class Card {

	private String cardName;
	private CardType cardType;
	
	public enum CardType {PERSON, ROOM, WEAPON};
	
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}

	public String getCardName() {
		return cardName;
	}
	
	@Override
	public boolean equals(Object comp) { 
		boolean result = false;
	    if (comp instanceof Card) {
	        Card that = (Card) comp;
	        result = cardName.equals(that.cardName) && cardType == that.cardType;
	    }
	    return result;
	}
}
