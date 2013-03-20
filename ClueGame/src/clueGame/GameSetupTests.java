package clueGame;

import clueGame.*;
import java.awt.Color;
import java.io.PrintStream;
import java.util.*;
import org.junit.Assert;
import org.junit.BeforeClass;

public class GameSetupTests
{
	
	ClueGame control;
	Board board;
    Card mustardCard;
    Card whiteCard;
    Card diningRoomCard;
    Card studyCard;
    Card candlestickCard;
    Card revolverCard;
    Card peacockCard;
    Card libraryCard;
    Card wrenchCard;
    Card knifeCard;
    Card greenCard;
    Card plumCard;
    Card ballroomCard;
    Card kitchenCard;
    Card hallCard;
    Card pipeCard;
    Card scarletCard;
    Card ropeCard;
    Card conservatoryCard;

    public GameSetupTests()
    {
    }

    @BeforeClass
    public void setUp()
    {
    	board = new Board();
        control = new ClueGame(123, 6, board);
        mustardCard = new Card("Colonel Mustard", Card.CardType.PERSON);
        whiteCard = new Card("Mrs. White", Card.CardType.PERSON);
        peacockCard = new Card("Mrs. Peacock", Card.CardType.PERSON);
        greenCard = new Card("Mr. Green", Card.CardType.PERSON);
        plumCard = new Card("Professor Plum", Card.CardType.PERSON);
        scarletCard = new Card("Miss Scarlet", Card.CardType.PERSON);
        diningRoomCard = new Card("Dining Room", Card.CardType.ROOM);
        studyCard = new Card("Study", Card.CardType.ROOM);
        ballroomCard = new Card("Ballroom", Card.CardType.ROOM);
        kitchenCard = new Card("Kitchen", Card.CardType.ROOM);
        hallCard = new Card("Hall", Card.CardType.ROOM);
        libraryCard = new Card("Library", Card.CardType.ROOM);
        conservatoryCard = new Card("Conservatory", Card.CardType.ROOM);
        candlestickCard = new Card("Candlestick", Card.CardType.WEAPON);
        revolverCard = new Card("Revolver", Card.CardType.WEAPON);
        wrenchCard = new Card("Wrench", Card.CardType.WEAPON);
        knifeCard = new Card("Knife", Card.CardType.WEAPON);
        pipeCard = new Card("Lead Pipe", Card.CardType.WEAPON);
        ropeCard = new Card("Rope", Card.CardType.WEAPON);
    }

    public void testDeal()
    {
        ArrayList players = control.getPlayers();
        int totCards = 0;
        Set allCards = new HashSet();
        for(Iterator iterator = players.iterator(); iterator.hasNext();)
        {
            Player player = (Player)iterator.next();
            totCards += player.getMyCards().size();
            Card card;
            for(Iterator iterator3 = player.getMyCards().iterator(); iterator3.hasNext(); allCards.add(card))
                card = (Card)iterator3.next();

        }

        totCards += control.getHuman().getMyCards().size();
        Card card;
        for(Iterator iterator1 = control.getHuman().getMyCards().iterator(); iterator1.hasNext(); allCards.add(card))
            card = (Card)iterator1.next();

        Assert.assertEquals(totCards, 18L);
        Assert.assertEquals(allCards.size(), 18L);
        int numCards = control.getHuman().getMyCards().size();
        Player player;
        for(Iterator iterator2 = players.iterator(); iterator2.hasNext(); Assert.assertTrue(player.getMyCards().size() == numCards || player.getMyCards().size() == numCards - 1 || player.getMyCards().size() == numCards + 1))
            player = (Player)iterator2.next();

    }

    
}