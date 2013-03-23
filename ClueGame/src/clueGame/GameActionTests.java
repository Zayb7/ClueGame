package clueGame;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GameActionTests {
	
	private ClueGame control;
	private Board board;
	
	//player cards
    private Card mustardCard;
    private Card whiteCard;
    private Card peacockCard;
    private Card scarletCard;
    private Card greenCard;
    private Card plumCard;
    
    //Room cards
    private Card diningRoomCard;
    private Card studyCard;
    private Card libraryCard;
    private Card ballroomCard;
    private Card kitchenCard;
    private Card hallCard;
    private Card conservatoryCard;
    
    //Weapon cards
    private Card candlestickCard;
    private Card revolverCard;
    private Card wrenchCard;
    private Card knifeCard;
    private Card pipeCard;
    private Card ropeCard;
    

    @Before
    public void setUp() throws FileNotFoundException, BadConfigFormatException
    {
    	board = new Board("ConfigLayout.csv", "ConfigRooms.txt");
        control = new ClueGame(6, board);
        
        //initialize all of the Person Cards
        whiteCard = new Card("Mrs. White", Card.CardType.PERSON);
        mustardCard = new Card("Colonel Mustard", Card.CardType.PERSON);
        plumCard = new Card("Professor Plum", Card.CardType.PERSON);
        peacockCard = new Card("Mrs. Peacock", Card.CardType.PERSON);
        greenCard = new Card("Mr. Green", Card.CardType.PERSON);
        scarletCard = new Card("Miss Scarlet", Card.CardType.PERSON);
        
        //initialize all of the Room Cards
        diningRoomCard = new Card("Dining Room", Card.CardType.ROOM);
        conservatoryCard = new Card("Conservatory", Card.CardType.ROOM);
        libraryCard = new Card("Library", Card.CardType.ROOM);
        ballroomCard = new Card("Ballroom", Card.CardType.ROOM);
        kitchenCard = new Card("Kitchen", Card.CardType.ROOM);
        hallCard = new Card("Hall", Card.CardType.ROOM);
        studyCard = new Card("Study", Card.CardType.ROOM);
        
        //initialize all of the Weapon Cards
        candlestickCard = new Card("Candlestick", Card.CardType.WEAPON);
        revolverCard = new Card("Revolver", Card.CardType.WEAPON);
        wrenchCard = new Card("Wrench", Card.CardType.WEAPON);
        knifeCard = new Card("Knife", Card.CardType.WEAPON);
        pipeCard = new Card("Lead Pipe", Card.CardType.WEAPON);
        ropeCard = new Card("Rope", Card.CardType.WEAPON);
    }
	
    @Test
	public void testAccusation()
    {
    	//tests the correct accusation
        Assert.assertTrue(control.checkAccusation(plumCard.getCardName(), wrenchCard.getCardName(), kitchenCard.getCardName()));
        //tests an accusation with wrong weapon
        Assert.assertFalse(control.checkAccusation(plumCard.getCardName(), revolverCard.getCardName(), kitchenCard.getCardName()));
        //tests an accusation with the wrong player
        Assert.assertFalse(control.checkAccusation(scarletCard.getCardName(), wrenchCard.getCardName(), kitchenCard.getCardName()));
        //tests an accusation with wrong weapon and wrong room
        Assert.assertFalse(control.checkAccusation(plumCard.getCardName(), revolverCard.getCardName(), ballroomCard.getCardName()));
    }

    @Test
    public void testSuggestion()
    {
        ComputerPlayer player = new ComputerPlayer("Miss Scarlet", Color.magenta, control);
        //gives the computer player 2 of each type of card
        player.addCard(peacockCard);
        player.addCard(plumCard);
       
        player.addCard(conservatoryCard);
        player.addCard(ballroomCard);
        
        player.addCard(knifeCard);
        player.addCard(candlestickCard);
        
        //tests revealing a player card
        Assert.assertEquals(plumCard, player.disproveSuggestion(plumCard, studyCard, wrenchCard));
        //tests revealing a room card
        Assert.assertEquals(conservatoryCard, player.disproveSuggestion(whiteCard, conservatoryCard, wrenchCard));
        //tests revealing a weapon card
        Assert.assertEquals(knifeCard, player.disproveSuggestion(greenCard, studyCard, knifeCard));
        //tests revealing none of the cards
        Assert.assertEquals(null, player.disproveSuggestion(greenCard, studyCard, wrenchCard));
    }
    
    @Test
    public void testMultipleMatchesSuggestion()
    {
        ComputerPlayer player = new ComputerPlayer("Miss Scarlet", Color.magenta, control);
        //gives the computer player 2 of each type of card
        player.addCard(peacockCard);
        player.addCard(plumCard);
       
        player.addCard(conservatoryCard);
        player.addCard(ballroomCard);
        
        player.addCard(knifeCard);
        player.addCard(candlestickCard);
        
        int numRoom = 0;
        int numWeapon = 0;
        int numPerson = 0;
        for(int i = 0; i < 30; i++)
        {
            Card result = player.disproveSuggestion(plumCard, conservatoryCard, knifeCard);
            //Check that these cards are part of the suggestion
            if(result.getCardName().equals("Professor Plum"))
                numPerson++;
            else if(result.getCardName().equals("Conservatory"))
                numRoom++;
            else if(result.getCardName().equals("Knife"))
                numWeapon++;
            else
                Assert.fail();
        }

        //Checks to make sure that the card was returned multiple times
        Assert.assertTrue(numRoom > 0);
        Assert.assertTrue(numPerson > 0);
        Assert.assertTrue(numWeapon > 0);
    }

    @Test
    public void testAllPlayersQueriedSuggestion()
    {
        ArrayList<Player> players = new ArrayList<Player>();
        ComputerPlayer player = new ComputerPlayer("Miss Scarlet", Color.magenta, control);
        player.addCard(peacockCard);
        player.addCard(knifeCard);
        player.addCard(revolverCard);
        player.addCard(libraryCard);
        players.add(player);
        
        player = new ComputerPlayer("Mr. Green", Color.green, control);
        player.addCard(plumCard);
        player.addCard(wrenchCard);
        player.addCard(pipeCard);
        player.addCard(ballroomCard);
        players.add(player);
        
        player = new ComputerPlayer("Mrs. Peacock", Color.blue, control);
        player.addCard(mustardCard);
        player.addCard(candlestickCard);
        player.addCard(kitchenCard);
        player.addCard(hallCard);
        players.add(player);
        
        control.setPlayers(players);
        //Checks a suggestion to make sure that no players can disprove this
        Assert.assertEquals(null, control.handleSuggestion(scarletCard, diningRoomCard, ropeCard, player));
        Assert.assertEquals(null, control.handleSuggestion(scarletCard, studyCard, ropeCard, player));
        //Checks a suggestion to make sure only a human could disprove it and returns the appropriate card
        Assert.assertEquals(libraryCard, control.handleSuggestion(scarletCard, libraryCard, ropeCard, player));
        Assert.assertEquals(peacockCard, control.handleSuggestion(peacockCard, diningRoomCard, ropeCard, player));
        Assert.assertEquals(revolverCard, control.handleSuggestion(scarletCard, diningRoomCard, revolverCard, player));
        //Checks to see if the person making the suggestion is the only one to disprove it, and will return null if true
        Assert.assertEquals(null, control.handleSuggestion(mustardCard, hallCard, candlestickCard, player));
        
        int numPlum = 0, numLib = 0, numCandle = 0;
        for(int i = 0; i < 50; i++)
        {
            Card returnCard = control.handleSuggestion(plumCard, libraryCard, candlestickCard, player);
            if(returnCard.equals(plumCard))
                numPlum++;
            else if(returnCard.equals(libraryCard))
                numLib++;
            else if(returnCard.equals(candlestickCard))
                numCandle++;
        }

        Assert.assertEquals(50, numPlum + numLib + numCandle);
        Assert.assertTrue(numPlum > 1);
        Assert.assertTrue(numLib > 1);
        Assert.assertTrue(numCandle == 0);
    }

    @Test
    public void testSelectTargetRoom()
    {
        ComputerPlayer compPlayer = new ComputerPlayer("Miss Scarlet", Color.magenta, control);
        //Computer Player set location at U4
        compPlayer.setLocation(board.getCells(board.calcIndex(3, 21)));
        board.calcAdjacencies();
        board.startTargets(board.calcIndex(3, 21), 3);
        Assert.assertEquals(board.getCells(board.calcIndex(1, 20)), compPlayer.pickLocation(board.getTargets()));
    }

    @Test
    public void testTargetRandomSelection()
    {
    	ComputerPlayer player = new ComputerPlayer("Miss Scarlet", Color.magenta, control);
		// Pick a location with no rooms in target, just three targets
    	board.calcAdjacencies();
		board.startTargets(board.calcIndex(14, 0), 4);
		int loc1 = 0;
		int loc2 = 0;
		// Run the test 100 times
		for (int i=0; i < 100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCells(board.calcIndex(12, 1)))
				loc1++;
			else if (selected == board.getCells(board.calcIndex(15, 0)))
				loc2++;
		}
		// Ensure we have 100 total selections (fail should also ensure)
		Assert.assertEquals(100, loc1 + loc2);
		// Ensure each target was selected more than once
		Assert.assertTrue(loc1 > 10);
		Assert.assertTrue(loc2 > 10);
    }

    @Test
    public void testMakeSuggestion()
    {
        ComputerPlayer compPlayer = new ComputerPlayer("Miss Scarlet", Color.magenta, control);
        //Cell J7
        compPlayer.setLocation(board.getCells(board.calcIndex(11, 19)));
        compPlayer.updateSeenCards(mustardCard);
        compPlayer.updateSeenCards(peacockCard);
        compPlayer.updateSeenCards(scarletCard);
        compPlayer.updateSeenCards(plumCard);
        compPlayer.updateSeenCards(whiteCard);
        
        compPlayer.updateSeenCards(candlestickCard);
        compPlayer.updateSeenCards(knifeCard);
        compPlayer.updateSeenCards(pipeCard);
        compPlayer.updateSeenCards(ropeCard);
        compPlayer.updateSeenCards(wrenchCard);
        compPlayer.makeSuggestion(compPlayer.getSeenCards());
        //testing actual suggestion that was made
        Assert.assertEquals(ballroomCard, compPlayer.getRoomGuess());
        Assert.assertEquals(greenCard, compPlayer.getPersonGuess());
        Assert.assertEquals(revolverCard, compPlayer.getWeaponGuess());
        
    }

    @Test
    public void testAllPlayersQueriedMakingSuggestion()
    {
        ComputerPlayer compPlayer = new ComputerPlayer("Miss Scarlet", Color.magenta, control);
        //Cell V20
        compPlayer.setLocation(board.getCells(board.calcIndex(1, 14)));
        compPlayer.updateSeenCards(mustardCard);
        compPlayer.updateSeenCards(peacockCard);
        compPlayer.updateSeenCards(whiteCard);
        compPlayer.updateSeenCards(greenCard);
        ArrayList<Card> seenCards = compPlayer.getSeenCards();
        compPlayer.updateSeenCards(candlestickCard);
        compPlayer.updateSeenCards(knifeCard);
        compPlayer.updateSeenCards(pipeCard);
        compPlayer.updateSeenCards(ropeCard);
        compPlayer.updateSeenCards(revolverCard);
        compPlayer.makeSuggestion(compPlayer.getMyCards());
        Assert.assertEquals(conservatoryCard, compPlayer.getRoomGuess());
        int numScarlet = 0;
        int numPlum = 0;
        //50 iterations for checking multiple matches
        for(int i = 0; i < 50; i++)
        {
            compPlayer.makeSuggestion(compPlayer.getSeenCards());
            Card suggestedCard = compPlayer.getPersonGuess();
            if(suggestedCard.equals(scarletCard))
                numScarlet++;
            else if(suggestedCard.equals(plumCard))
                numPlum++;
            Assert.assertEquals(wrenchCard, compPlayer.getWeaponGuess());
        }

        Assert.assertEquals(50, numPlum + numScarlet);
        Assert.assertTrue(numPlum > 0);
        Assert.assertTrue(numScarlet > 0);
    }
}
