package clueGame;

import clueGame.*;
import java.awt.Color;
import java.io.PrintStream;
import java.util.*;
import org.junit.Assert;

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

    public void setUp()
        throws Exception
    {
    	board = new Board();
        control = new ClueGame(123, 6, board);
        mustardCard = new Card("Colonel Mustard", clueGame.Card.CardType.PERSON);
        whiteCard = new Card("Mrs. White", clueGame.Card.CardType.PERSON);
        peacockCard = new Card("Mrs. Peacock", clueGame.Card.CardType.PERSON);
        greenCard = new Card("Mr. Green", clueGame.Card.CardType.PERSON);
        plumCard = new Card("Professor Plum", clueGame.Card.CardType.PERSON);
        scarletCard = new Card("Miss Scarlet", clueGame.Card.CardType.PERSON);
        diningRoomCard = new Card("Dining Room", clueGame.Card.CardType.ROOM);
        studyCard = new Card("Study", clueGame.Card.CardType.ROOM);
        ballroomCard = new Card("Ballroom", clueGame.Card.CardType.ROOM);
        kitchenCard = new Card("Kitchen", clueGame.Card.CardType.ROOM);
        hallCard = new Card("Hall", clueGame.Card.CardType.ROOM);
        libraryCard = new Card("Library", clueGame.Card.CardType.ROOM);
        conservatoryCard = new Card("Conservatory", clueGame.Card.CardType.ROOM);
        candlestickCard = new Card("Candlestick", clueGame.Card.CardType.WEAPON);
        revolverCard = new Card("Revolver", clueGame.Card.CardType.WEAPON);
        wrenchCard = new Card("Wrench", clueGame.Card.CardType.WEAPON);
        knifeCard = new Card("Knife", clueGame.Card.CardType.WEAPON);
        pipeCard = new Card("Lead Pipe", clueGame.Card.CardType.WEAPON);
        ropeCard = new Card("Rope", clueGame.Card.CardType.WEAPON);
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

    public void testAccusation()
    {
        Assert.assertTrue(control.checkAccusation(mustardCard, candlestickCard, diningRoomCard));
        Assert.assertFalse(control.checkAccusation(mustardCard, revolverCard, diningRoomCard));
        Assert.assertFalse(control.checkAccusation(whiteCard, candlestickCard, diningRoomCard));
        Assert.assertFalse(control.checkAccusation(mustardCard, candlestickCard, studyCard));
    }

    public void testSuggestion()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.addCard(peacockCard);
        player.addCard(mustardCard);
        player.addCard(knifeCard);
        player.addCard(candlestickCard);
        player.addCard(libraryCard);
        player.addCard(ballroomCard);
        Assert.assertEquals(peacockCard, player.disproveSuggestion(peacockCard, studyCard, wrenchCard));
        Assert.assertEquals(libraryCard, player.disproveSuggestion(greenCard, libraryCard, wrenchCard));
        Assert.assertEquals(knifeCard, player.disproveSuggestion(greenCard, studyCard, knifeCard));
        Assert.assertEquals(null, player.disproveSuggestion(greenCard, studyCard, wrenchCard));
    }

    public void testSuggestion2()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.addCard(peacockCard);
        player.addCard(knifeCard);
        player.addCard(revolverCard);
        player.addCard(libraryCard);
        int numRoom = 0;
        int numWeapon = 0;
        int numPerson = 0;
        for(int i = 0; i < 50; i++)
        {
            Card result = player.disproveSuggestion(peacockCard, libraryCard, knifeCard);
            if(result.getCardName().equals("Mrs. Peacock"))
                numPerson++;
            else
            if(result.getCardName().equals("Library"))
                numRoom++;
            else
            if(result.getCardName().equals("Knife"))
                numWeapon++;
            else
                Assert.fail();
        }

        Assert.assertTrue(numRoom > 0);
        Assert.assertTrue(numPerson > 0);
        Assert.assertTrue(numWeapon > 0);
    }

    public void testSuggestion3()
    {
        ArrayList players = new ArrayList();
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.addCard(peacockCard);
        player.addCard(knifeCard);
        player.addCard(revolverCard);
        player.addCard(libraryCard);
        players.add(player);
        player = new ComputerPlayer("Mr. Green", board.getBoardPiece(1), Color.green, control);
        player.addCard(plumCard);
        player.addCard(wrenchCard);
        player.addCard(candlestickCard);
        player.addCard(ballroomCard);
        players.add(player);
        player = new ComputerPlayer("Mrs. Peacock", board.getBoardPiece(1), Color.blue, control);
        player.addCard(mustardCard);
        player.addCard(pipeCard);
        player.addCard(kitchenCard);
        player.addCard(hallCard);
        players.add(player);
        control.setPlayers(players);
        Assert.assertEquals(null, control.testSuggestion(scarletCard, diningRoomCard, ropeCard, player));
        Assert.assertEquals(null, control.testSuggestion(scarletCard, studyCard, ropeCard, player));
        Assert.assertEquals(libraryCard, control.testSuggestion(scarletCard, libraryCard, ropeCard, player));
        Assert.assertEquals(peacockCard, control.testSuggestion(peacockCard, diningRoomCard, ropeCard, player));
        Assert.assertEquals(wrenchCard, control.testSuggestion(scarletCard, diningRoomCard, wrenchCard, player));
        Assert.assertEquals(null, control.testSuggestion(scarletCard, diningRoomCard, pipeCard, player));
        int plum = 0;
        int library = 0;
        int pipe = 0;
        for(int i = 0; i < 100; i++)
        {
            Card returnCard = control.testSuggestion(plumCard, libraryCard, pipeCard, player);
            if(returnCard.equals(plumCard))
                plum++;
            else
            if(returnCard.equals(libraryCard))
                library++;
            else
            if(returnCard.equals(pipeCard))
                pipe++;
        }

        Assert.assertEquals(100L, plum + library + pipe);
        Assert.assertTrue(plum > 1);
        Assert.assertTrue(library > 1);
        Assert.assertTrue(pipe == 0);
    }

    public void testUpdate()
    {
        ArrayList players = control.getPlayers();
        ComputerPlayer player;
        for(Iterator iterator = players.iterator(); iterator.hasNext(); player.updateSeen(ballroomCard))
            player = (ComputerPlayer)iterator.next();

        ArrayList seen;
        for(Iterator iterator1 = players.iterator(); iterator1.hasNext(); Assert.assertTrue(seen.contains(ballroomCard)))
        {
            ComputerPlayer player = (ComputerPlayer)iterator1.next();
            seen = player.getSeenCards();
        }

    }

    public void testSuggestionx2()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.addCard(peacockCard);
        player.addCard(mustardCard);
        player.addCard(knifeCard);
        player.addCard(candlestickCard);
        player.addCard(libraryCard);
        player.addCard(ballroomCard);
        Assert.assertEquals(peacockCard, player.disproveSuggestion(peacockCard, studyCard, wrenchCard));
        Assert.assertEquals(libraryCard, player.disproveSuggestion(greenCard, libraryCard, wrenchCard));
        Assert.assertEquals(knifeCard, player.disproveSuggestion(greenCard, studyCard, knifeCard));
        Assert.assertEquals(null, player.disproveSuggestion(greenCard, studyCard, wrenchCard));
    }

    public void testSuggestionx()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.addCard(peacockCard);
        player.addCard(knifeCard);
        player.addCard(revolverCard);
        player.addCard(libraryCard);
        Assert.assertEquals(knifeCard, player.disproveSuggestion(greenCard, studyCard, knifeCard));
        Assert.assertEquals(revolverCard, player.disproveSuggestion(greenCard, studyCard, revolverCard));
        Assert.assertEquals(null, player.disproveSuggestion(greenCard, studyCard, wrenchCard));
    }

    public void testSelectTargetRoom()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.setLocation(board.getBoardPiece(96));
        board.calcTargets(96, 3);
        for(int i = 0; i < 100; i++)
            Assert.assertEquals(board.getBoardPiece(95), player.pickLocation(board.getTargets()));

    }

    public void testSelectTarget2()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.setLocation(board.getBoardPiece(5));
        board.calcTargets(5, 2);
        int tot29 = 0;
        int tot51 = 0;
        for(int i = 0; i < 100; i++)
        {
            BoardPiece selected = player.pickLocation(board.getTargets());
            if(selected == board.getBoardPiece(29))
                tot29++;
            else
            if(selected == board.getBoardPiece(51))
                tot51++;
        }

        Assert.assertEquals(100L, tot29 + tot51);
        Assert.assertTrue(tot29 > 1);
        Assert.assertTrue(tot51 > 1);
    }

    public void testMakeSuggestion()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.setLocation(board.getBoardPiece(95));
        player.updateSeen(mustardCard);
        player.updateSeen(peacockCard);
        player.updateSeen(scarletCard);
        player.updateSeen(plumCard);
        player.updateSeen(greenCard);
        player.updateSeen(candlestickCard);
        player.updateSeen(knifeCard);
        player.updateSeen(pipeCard);
        player.updateSeen(ropeCard);
        player.updateSeen(revolverCard);
        player.createSuggestion(board);
        Assert.assertEquals(conservatoryCard, player.getRoomGuess());
        Assert.assertEquals(whiteCard, player.getPersonGuess());
        Assert.assertEquals(wrenchCard, player.getWeaponGuess());
    }

    public void testMakeSuggestion2()
    {
        ComputerPlayer player = new ComputerPlayer("Mrs. White", board.getBoardPiece(1), Color.white, control);
        player.setLocation(board.getBoardPiece(95));
        player.updateSeen(mustardCard);
        player.updateSeen(peacockCard);
        player.updateSeen(plumCard);
        player.updateSeen(greenCard);
        ArrayList seenList = player.getSeenCards();
        System.out.println((new StringBuilder("seen ")).append(seenList.size()).toString());
        player.updateSeen(candlestickCard);
        player.updateSeen(knifeCard);
        player.updateSeen(pipeCard);
        player.updateSeen(ropeCard);
        player.updateSeen(revolverCard);
        player.createSuggestion(board);
        Assert.assertEquals(conservatoryCard, player.getRoomGuess());
        int totScarlet = 0;
        int totWhite = 0;
        for(int i = 0; i < 100; i++)
        {
            player.createSuggestion(board);
            Card pCard = player.getPersonGuess();
            if(pCard.equals(scarletCard))
                totScarlet++;
            else
            if(pCard.equals(whiteCard))
                totWhite++;
            pCard = player.getWeaponGuess();
            Assert.assertEquals(wrenchCard, player.getWeaponGuess());
        }

        Assert.assertEquals(100L, totScarlet + totWhite);
        Assert.assertTrue(totScarlet > 0);
        Assert.assertTrue(totWhite > 0);
    }

    
}