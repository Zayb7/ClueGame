package clueGame;

import java.awt.*;

import javax.swing.*;

public class ButtonPanel extends JPanel {

	public ButtonPanel(){
		
		//Two Buttons, one for next player and other for making an accusation
		JButton nextPlayer = new JButton("Next Player");
		nextPlayer.setPreferredSize(new Dimension(175,125));
		JButton makeAnAccusation = new JButton("Make An Accusation");
		makeAnAccusation.setPreferredSize(new Dimension(275,125));
		add(makeAnAccusation, BorderLayout.EAST);
		add(nextPlayer, BorderLayout.CENTER);
		
		
	}
	

	
}
