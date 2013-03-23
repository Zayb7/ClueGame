package clueGame;

import java.awt.Color;
import java.util.ArrayList;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, Color color, ClueGame cg) {
		super(name, color, cg);
		cards = new ArrayList<Card>();
	}
}
