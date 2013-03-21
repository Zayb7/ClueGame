package clueGame;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {

	public MainPanel(){
		JLabel whoseTurn = new JLabel("Whose turn?");
		setLayout(new GridLayout(0,1));
		add(whoseTurn);
	}
}
