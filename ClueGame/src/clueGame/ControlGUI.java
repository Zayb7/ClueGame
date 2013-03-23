package clueGame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JFrame {

	public ControlGUI(){
		int width = 750, height = 325;
		
		JFrame f = new JFrame();
		setSize(new Dimension(width, height));
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = f.getContentPane();
	    Font font = new Font("Serif", Font.BOLD, 20);
	    UIManager.put("Button.font", font);
	    font = new Font("Serif", Font.ITALIC, 18);
	    UIManager.put("Label.font", font);
		
	    //Border Stuff
	    font = new Font("Serif", Font.PLAIN, 20);
	    Border lineBorder = BorderFactory.createLineBorder(Color.BLUE, 1, false);
	    Border dieName = BorderFactory.createTitledBorder(lineBorder, "Die", TitledBorder.CENTER, TitledBorder.TOP, font);
	    Border resultName = BorderFactory.createTitledBorder(lineBorder, "Guess Result", TitledBorder.CENTER, TitledBorder.TOP, font);
	    Border guessName = BorderFactory.createTitledBorder(lineBorder, "Guess", TitledBorder.CENTER, TitledBorder.TOP, font);
	    
	    
		//Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(width/2, height/2));
		//Label and text box for Main Panel
		JPanel whoseTurnPanel = new JPanel();
		whoseTurnPanel.setLayout(new GridLayout(2,0));
		JLabel whoseTurnLabel = new JLabel("Whose turn?", JLabel.CENTER);
		whoseTurnLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		whoseTurnPanel.add(whoseTurnLabel);
		JTextField whoseTurnText = new JTextField(15);
		whoseTurnPanel.add(whoseTurnText);
		mainPanel.add(whoseTurnPanel);
		add(mainPanel, BorderLayout.CENTER);
		
		
		//Buttons
		//Two Buttons, one for next player and other for making an accusation
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout());
		//buttons
		JButton makeAnAccusation = new JButton("Make An Accusation");
		makeAnAccusation.setForeground(Color.red);
		makeAnAccusation.setPreferredSize(new Dimension(275,125));
		JButton nextPlayer = new JButton("Next Player");
		buttonPanel.setPreferredSize(new Dimension(175,125));
		nextPlayer.setForeground(Color.GREEN);
		
		//adding to panel
		buttonPanel.add(makeAnAccusation);
		buttonPanel.add(nextPlayer);
		
		add(buttonPanel, BorderLayout.NORTH);
		
		
		//Three panels for bottom half
		JPanel bottomHalf = new JPanel();
		//Die Label and Text Field
		JPanel diePanel = new JPanel();
		diePanel.setBorder(dieName);		
		//diePanel.setBorder(line);
		JLabel dieLabel = new JLabel("Roll");
		diePanel.add(dieLabel);
		JTextField dieText = new JTextField(13);
		diePanel.add(dieText, BorderLayout.WEST);
		
		
		//Guess Panel Label and Text Field
		JPanel guessPanel = new JPanel();
		guessPanel.setBorder(guessName);
		guessPanel.setName("Guess");
		JLabel guessLabel = new JLabel("Guess");
		guessPanel.add(guessLabel, BorderLayout.CENTER);
		JTextField guessText = new JTextField(13);
		guessPanel.add(guessText, BorderLayout.SOUTH);
		
		
		//Result Panel Label and Text Field
		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(resultName);
		resultPanel.setName("Guess Result");
		JLabel resultLabel = new JLabel("Response");
		resultPanel.add(resultLabel);
		JTextField resultText = new JTextField(13);
		resultPanel.add(resultText, BorderLayout.EAST);
	
		
		//adding panels
		bottomHalf.setSize(new Dimension(width, height/2 -10));
		bottomHalf.add(diePanel, BorderLayout.WEST);
		bottomHalf.add(guessPanel, BorderLayout.CENTER);
		bottomHalf.add(resultPanel, BorderLayout.EAST);
		
		add(bottomHalf, BorderLayout.SOUTH);
		
			
		
	}
	
	public void paintComponent(Graphics g)
    {
        g.setColor( Color.BLUE );
        g.fillRect(0, 0, getSize().width, getSize().height);
        paintComponent(g);
    }
	public static void main(String args[]){
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}
}
