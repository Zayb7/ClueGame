package clueGame;

import java.awt.*;

import javax.swing.*;

public class ButtonPanel extends JPanel {

	public ButtonPanel(){
		
		JPanel buttonPanel = new JPanel();
		//Two Buttons, one for next player and other for making an accusation
		JButton nextPlayer = new JButton("Next Player");
		JButton makeAnAccusation = new JButton("Make An Accusation");
		add(makeAnAccusation, BorderLayout.EAST);
		add(nextPlayer, BorderLayout.CENTER);
	}
	

	
}
