package clueGame;

import java.awt.*;

import javax.swing.*;

public class MainPanel extends JPanel {

	private JTextField whoseTurnText;
	
	public MainPanel(){
		
		JPanel whoseTurnPanel = new JPanel();
		//Label and text box for whoseTurn
		JLabel whoseTurnLabel = new JLabel("            Whose turn?");
		setLayout(new GridLayout(0,1));
		whoseTurnPanel.add(whoseTurnLabel, BorderLayout.NORTH);
		JTextField whoseTurnText = new JTextField();
		whoseTurnPanel.add(whoseTurnText, BorderLayout.SOUTH);
		add(whoseTurnPanel, BorderLayout.WEST);
		
		
		
		//button panel
//		ButtonPanel buttons = new ButtonPanel();
//		add(buttons, BorderLayout.CENTER);
		//Other panels inside
//		InsidePanel diePanel = new InsidePanel();
//		InsidePanel guessPanel = new InsidePanel();
//		InsidePanel guessResultPanel = new InsidePanel();

		
	}
}
