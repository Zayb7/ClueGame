package clueGame;

import java.io.FileNotFoundException;
import java.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameSetupTests
{
	
	private ClueGame control;
	private Board board;

    @Before
    public void setUp() throws FileNotFoundException, BadConfigFormatException
    {
    	board = new Board("ConfigLayout.csv", "ConfigRooms.txt");
        control = new ClueGame(6, board);
        control.start();
    }

    @Test
    public void testDeal()
    {
        ArrayList<Player> players = control.getPlayers();
        int totalNumCards = 0;
        ArrayList<Card> allCards = new ArrayList<Card>();
        ArrayList<Card> numberOfHumanCards = new ArrayList<Card>();
        for(Player p: players) {
            totalNumCards += p.getMyCards().size();
            for (Card card: p.getMyCards()) {
            	allCards.add(card);
            }    	
        }

        totalNumCards += control.getHuman().getMyCards().size();
        for(Card card: control.getHuman().getMyCards()){
        	numberOfHumanCards.add(card);
        }

        //Checks to make sure that there are 19 cards for all players
        Assert.assertEquals(18, totalNumCards);
        //Checks to make sure that human has 3 cards
        Assert.assertEquals(3, numberOfHumanCards.size());
        int totalHumanCards = control.getHuman().getMyCards().size();

        for(Player player: players)
        	//Checking that the all players have three or four cards
        	Assert.assertTrue(player.getMyCards().size() == totalHumanCards || player.getMyCards().size() == totalHumanCards + 1 || player.getMyCards().size() == totalHumanCards - 1);
    }

    
}