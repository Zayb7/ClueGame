package clueGame;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {

	private JTextField whoseTurnText;
	
	public MainPanel(){
		
		//Label and text box for whoseTurn
		JLabel whoseTurnLabel = new JLabel("Whose turn?");
		whoseTurnText = new JTextField(5);
		setLayout(new GridLayout(0,1));
		add(whoseTurnLabel, BorderLayout.NORTH);
		add(whoseTurnText, BorderLayout.SOUTH);
		
	}
}
